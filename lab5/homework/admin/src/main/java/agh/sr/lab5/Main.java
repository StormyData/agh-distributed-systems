package agh.sr.lab5;

import com.rabbitmq.client.*;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        ArgumentParser argumentParser = ArgumentParsers.newFor("Admin")
                .addHelp(true)
                .build()
                .defaultHelp(true);
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
        String exchange = ns.getString("exchange");
        String address = ns.getString("address");
        run(exchange, address);
    }

    private static void run(String exchange, String address_string) throws IOException, TimeoutException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ConnectionFactory factory = new ConnectionFactory();
        Cli cli = new Cli(exchange);
        try(var connection = factory.newConnection(Address.parseAddresses(address_string))) {
            try(var channel = connection.createChannel()) {
                channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);

                var queueName = channel.queueDeclare().getQueue();
                channel.queueBind(queueName, exchange, "#");

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
                        String[] words = envelope.getRoutingKey().split("\\.");
                        String msg_type = words[0];
                        int offset = (msg_type.equals("service") || msg_type.equals("agency")) ? 1 : 0;
                        String from = words[1 + offset];
                        String id = words[2 + offset];
                        String message = new String(body, StandardCharsets.UTF_8);
                        if(offset == 0) {
                            System.out.printf("received message: \"%s\" type: %s from: %s id: %s\n", message, msg_type, from, id);
                        } else {
                            String to = words[1];
                            System.out.printf("received message: \"%s\" type: %s from: %s id: %s meant for: %s\n", message, msg_type, from, id, to);
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