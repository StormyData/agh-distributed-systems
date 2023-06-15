package agh.sr.lab5;

import com.rabbitmq.client.*;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ArgumentParser argumentParser = ArgumentParsers.newFor("SpaceAgency")
                .addHelp(true)
                .build()
                .defaultHelp(true);
        argumentParser.addArgument("--name")
                .dest("name")
                .help("the name of the agency")
                .required(true);
        argumentParser.addArgument("--exchange")
                .dest("exchange")
                .setDefault("exchange")
                .help("the name of the exchange")
                .required(false);
        argumentParser.addArgument("--address")
                .dest("address")
                .setDefault("127.0.0.1")
                .help("the connection string");
        Namespace ns;
        try {
            ns = argumentParser.parseArgs(args);
        } catch (ArgumentParserException ex) {
            argumentParser.handleError(ex);
            return;
        }

        var agency_name = ns.getString("name");
        var exchange = ns.getString("exchange");
        var address_string = ns.getString("address");
        run(agency_name, exchange, address_string);
    }

    private static void run(String agency_name, String exchange, String address_string) throws IOException, TimeoutException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ConnectionFactory factory = new ConnectionFactory();
        Cli cli = new Cli(agency_name, exchange);
        try(var connection = factory.newConnection(Address.parseAddresses(address_string))) {
            try(var channel = connection.createChannel()) {
                channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);

                var queueName = "%s-sa-main".formatted(agency_name);
                channel.queueDeclare(queueName, true, true, false, null);
                channel.queueBind(queueName, exchange, "agency.%s.*.*".formatted(agency_name));
                channel.queueBind(queueName, exchange, "agencies.*.*");
                channel.queueBind(queueName, exchange, "everyone.*.*");

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
                        String message = new String(body, StandardCharsets.UTF_8);
                        String[] words = envelope.getRoutingKey().split("\\.");
                        String msg_type = words[0];
                        if(msg_type.equals("agency"))
                        {
                            String from = words[2];
                            String id = words[3];
                            System.out.printf("received message: \"%s\" from: %s id: %s%n", message, from, id);

                        } else {
                            String from = words[1];
                            String id = words[2];
                            System.out.printf("received message: \"%s\" from: %s id: %s%n", message, from, id);
                        }
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                };
                channel.basicConsume(queueName, false, consumer);
                String line;
                do {
                    line = reader.readLine();
                } while (cli.do_command_loop(line, channel));
            }
        }
    }
}