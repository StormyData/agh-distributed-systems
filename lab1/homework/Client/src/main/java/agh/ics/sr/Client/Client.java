package agh.ics.sr.Client;

import agh.ics.sr.ParserHelperClasses.InetAddressParseUnicast;
import agh.ics.sr.ParserHelperClasses.PortParse;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.stream.Collectors;

public class Client implements AutoCloseable {
    private final ClientControlPlane controlPlane;
    private final ClientDataPlane dataPlane;
    private final ClientInputPlane inputPlane;

    private final ClientMulticastPlane multicastPlane;

    public Client(InetAddress address, int port, NetworkInterface networkInterface) throws IOException {
        controlPlane = new ClientControlPlane(address, port);
        dataPlane = new ClientDataPlane(controlPlane);
        multicastPlane = new ClientMulticastPlane(controlPlane, networkInterface);
        inputPlane = new ClientInputPlane(controlPlane, dataPlane, multicastPlane);
        dataPlane.registerListener(inputPlane);
        multicastPlane.registerListener(inputPlane);
        controlPlane.registerListener(multicastPlane);
    }

    public static void main(String[] args) throws Exception {
        ArgumentParser argumentParser = ArgumentParsers
                .newFor("Client")
                .build()
                .defaultHelp(true)
                .description("lab1 hw chat server");
        argumentParser.addArgument("address")
                .type(InetAddressParseUnicast.class)
                .required(true)
                .help("the address to run this server on");
        argumentParser.addArgument("port")
                .type(PortParse.class)
                .required(true)
                .help("the port to run this server on");
        argumentParser.addArgument("network_interface")
                .type(String.class)
                .required(true)
                .choices(NetworkInterface
                        .networkInterfaces()
                        .map(NetworkInterface::getDisplayName)
                        .collect(Collectors.toList()))
                .help("the network interface to use for multicast");
        Namespace ns = null;
        try {
            ns = argumentParser.parseArgs(args);
        } catch (ArgumentParserException e) {
            argumentParser.handleError(e);
            System.exit(1);
        }
        InetAddress address = ns.<InetAddressParseUnicast>get("address").getAddress();
        NetworkInterface networkInterface = NetworkInterface.getByName(ns.getString("network_interface"));
        int port = ns.<PortParse>get("port").getPort();
        try(Client client = new Client(address, port, networkInterface))
        {
            client.run();
        }
    }
    public void createMulticastPlane() {

    }
    private void run() throws InterruptedException {
        controlPlane.start();
        inputPlane.start();
        dataPlane.start();
        multicastPlane.start();

        controlPlane.join();
        inputPlane.join();
        dataPlane.join();
        multicastPlane.join();
    }

    @Override
    public void close() throws Exception {
        controlPlane.close();
        dataPlane.close();
        multicastPlane.close();
    }
}
