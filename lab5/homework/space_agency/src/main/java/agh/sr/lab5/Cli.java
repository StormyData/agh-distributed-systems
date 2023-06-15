package agh.sr.lab5;

import com.rabbitmq.client.Channel;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Cli {
    private final ArgumentParser argumentParser;
    private final String agency_name;
    private int id = 0;
    private final String exchange;

    public Cli(String agencyName, String exchange) {
        agency_name = agencyName;
        this.exchange = exchange;
        argumentParser = ArgumentParsers.newFor("")
                .addHelp(true)
                .build()
                .defaultHelp(true);
        argumentParser.addArgument("type")
                .required(true)
                .help("the type of service to issue")
                .choices("satelite", "cargo", "people");
        argumentParser.addArgument("data")
                .required(false);

    }

    public boolean do_command_loop(String line, Channel channel) throws IOException {
        if(line.strip().equals("exit"))
            return false;
        Namespace ns;
        try {
            ns = argumentParser.parseArgs(line.split("\\s"));
        } catch (ArgumentParserException e) {
            argumentParser.handleError(e);
            return true;
        }
        String type = ns.getString("type");
        String data = ns.getString("data");
        if(data == null) {
            data = "";
        }
        String routing_key = "service.%s.%s.%d".formatted(type, agency_name, id++);
        System.out.printf("sending request for transport of %s to topic %s\n", data, routing_key);
        channel.basicPublish(exchange, routing_key, null, data.getBytes(StandardCharsets.UTF_8));

        return true;
    }
}
