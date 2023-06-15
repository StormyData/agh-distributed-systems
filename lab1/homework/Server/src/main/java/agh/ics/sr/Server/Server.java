package agh.ics.sr.Server;


import agh.ics.sr.ParserHelperClasses.InetAddressParseMulticast;
import agh.ics.sr.ParserHelperClasses.PortParse;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class Server implements AutoCloseable{
    private static final Logger logger = Logger.getLogger(Server.class.getCanonicalName());

    private final ServerControlPlane controlPlane;
    private final ServerDataPlane dataPlane;
    public Server(int port, InetSocketAddress multicastAddress) throws IOException {

        dataPlane = new ServerDataPlane(port);
        controlPlane = new ServerControlPlane(port, dataPlane, multicastAddress);
    }

    public static void main(String[] args) throws Exception {
        ArgumentParser argumentParser = ArgumentParsers
                .newFor("Server")
                .build()
                .defaultHelp(true)
                .description("lab1 hw chat server");
        argumentParser.addArgument("port")
                .type(PortParse.class)
                .required(true)
                .help("the port to run this server on");
        argumentParser.addArgument("multicast_address")
                .type(InetAddressParseMulticast.class)
                .required(true)
                .help("the multicast address which to send to the users");
        argumentParser.addArgument("multicast_port")
                .type(PortParse.class)
                .required(true)
                .help("the multicast port which to send to the users");
        Namespace ns = null;
        try {
            ns = argumentParser.parseArgs(args);
        } catch (ArgumentParserException e) {
            argumentParser.handleError(e);
            System.exit(1);
        }
        int port = ns.<PortParse>get("port").getPort();
        InetAddress address = ns.<InetAddressParseMulticast>get("multicast_address").getAddress();
        int multicast_port = ns.<PortParse>get("multicast_port").getPort();
        InetSocketAddress multicastAddress = new InetSocketAddress(address, multicast_port);
        try(Server server = new Server(port, multicastAddress))
        {
            server.run();
        }
    }
    public void run() throws InterruptedException {
        controlPlane.start();
        dataPlane.start();

        controlPlane.join();
        dataPlane.join();
    }

    @Override
    public void close() throws Exception {
        controlPlane.close();
        dataPlane.close();
    }
}