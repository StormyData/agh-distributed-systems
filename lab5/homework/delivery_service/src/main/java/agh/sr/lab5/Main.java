package agh.sr.lab5;

import com.rabbitmq.client.*;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ArgumentParser argumentParser = ArgumentParsers.newFor("DeliveryService")
                .addHelp(true)
                .build()
                .defaultHelp(true);
        argumentParser.addArgument("--name")
                .dest("name")
                .help("the name of the delivery service")
                .required(true);
        argumentParser.addArgument("--exchange")
                .dest("exchange")
                .setDefault("exchange")
                .help("the name of the exchange")
                .required(false);
        argumentParser.addArgument("--address")
                .dest("address")
                .setDefault("localhost")
                .help("the connection string");
        argumentParser.addArgument("--service")
                .dest("services")
                .help("the service to provide, can be specified more than once")
                .choices("people", "cargo", "satelite")
                .action(Arguments.append())
                .required(true);
        Namespace ns;
        try {
            ns = argumentParser.parseArgs(args);
        } catch (ArgumentParserException ex) {
            argumentParser.handleError(ex);
            return;
        }

        List<String> services = ns.get("services");
        String delivery_service_name = ns.getString("name");
        String exchange = ns.getString("exchange");
        String address = ns.getString("address");

        LaunchPad launchPad = new LaunchPad();


        ConnectionFactory factory = new ConnectionFactory();
        var connection = factory.newConnection(Address.parseAddresses(address));
        {
            for (String service : services) {
                register_service(connection, service, launchPad, delivery_service_name, exchange);
            }
            register_main(delivery_service_name, exchange, connection);
        }
    }

    private static void register_main(String delivery_service_name, String exchange, Connection connection) throws IOException, TimeoutException {
        var channel = connection.createChannel();
        String main_queue_name = "%s-ds-main".formatted(delivery_service_name);
        channel.queueDeclare(main_queue_name, true, true, false, null);
        channel.queueBind(main_queue_name, exchange, "everyone.*.*");
        channel.queueBind(main_queue_name, exchange, "delivery_services.*.*");
        var consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                String[] words = envelope.getRoutingKey().split("\\.");
                String msg_type = words[0];
                String from = words[1];
                String id = words[2];

                System.out.printf("Received message \"%s\" from %s with id %s", message, from, id);
                channel.basicAck(envelope.getDeliveryTag(), false);

            }
        };
        channel.basicConsume(main_queue_name, false, consumer);
    }

    private static void register_service(Connection connection, String service, LaunchPad launchPad, String delivery_service_name, String exchange) throws IOException {
        var channel = connection.createChannel();
        channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);
        channel.basicQos(1);
        var queueName = "%s-service".formatted(service);

        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchange, "service.%s.*.*".formatted(service));

        var consumer = new DefaultConsumer(channel) {

            /**
             * Called when a <code><b>basic.deliver</b></code> is received for this consumer.
             *
             * @param consumerTag the <i>consumer tag</i> associated with the consumer
             * @param envelope    packaging data for the message
             * @param properties  content header data for the message
             * @param body        the message body (opaque, client-specific byte array)
             * @throws IOException if the consumer encounters an I/O error while processing the message
             * @see Envelope
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);

                String[] words = envelope.getRoutingKey().split("\\.");
                String agency = words[2];
                String id = words[3];

                String status;
                try {
                    status = launchPad.deliverAThing(service, msg, agency, id);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                String routing_key = "agency.%s.%s.%s".formatted(agency, delivery_service_name, id);
                System.out.printf("sending status (%s) to %s\n", status, routing_key);
                channel.basicPublish(exchange, routing_key, null, status.getBytes(StandardCharsets.UTF_8));
                channel.basicAck(envelope.getDeliveryTag(), false);

            }
        };

        channel.basicConsume(queueName, false, consumer);

    }
}