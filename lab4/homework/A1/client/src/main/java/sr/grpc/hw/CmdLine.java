package sr.grpc.hw;

import io.grpc.StatusRuntimeException;

import java.util.Arrays;
import java.util.Map;

public class CmdLine {

    private final Map<String, IServiceCmd> validServices = Map.of(
            "sensors", new SensorControllerCmd(),
            "smartbulb", new SmartBulbCmd());
    private final IServiceCmd defaultService = new DefaultServiceCmd();
    private final GrpcClient client;
    private String targetServer = null;
    private String targetService = null;

    public CmdLine(GrpcClient client) {

        this.client = client;
    }

    private String getTargetServer(String[] args) {
        return Util.getParameter(Arrays.stream(args), "server=")
                .orElse(targetServer);
    }

    private String getTargetService(String[] args) {
        return Util.getParameter(Arrays.stream(args), "service=")
                .filter(validServices::containsKey)
                .orElse(targetService);
    }

    public void run() {
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        while (true) {
            try {
                String[] args = in.readLine().split(" ");
                switch (args[0]) {
                    case "get" -> {
                        String server = getTargetServer(args);
                        String service = getTargetService(args);
                        if (service == null || server == null) {
                            System.out.println("server and valid service required");
                            break;
                        }
                        validServices.getOrDefault(service, defaultService).get(server, args, client);
                    }

                    case "sub" -> {
                        String server = getTargetServer(args);
                        String service = getTargetService(args);
                        if (service == null || server == null) {
                            System.out.println("server and valid service required");
                            break;
                        }
                        validServices.getOrDefault(service, defaultService).sub(server, args, client);
                    }
                    case "unsub" -> {
                        var channelId = Util.getParameter(Arrays.stream(args), "channelId=")
                                .map(Util::parseIntOrNull).orElse(null);

                        if (channelId == null) {
                            System.out.println("channelId is required");
                        } else if (client.cancelSub(channelId)) {
                            System.out.println("unsubscribed");
                        }
                    }
                    case "set" -> {
                        String server = getTargetServer(args);
                        String service = getTargetService(args);
                        if (service == null || server == null) {
                            System.out.println("server and valid service required");
                            break;
                        }
                        validServices.getOrDefault(service, defaultService).set(server, args, client);

                    }
                    case "describe" -> {
                        String server = getTargetServer(args);
                        String service = getTargetService(args);
                        if (service == null || server == null) {
                            System.out.println("server and valid service required");
                            break;
                        }
                        validServices.getOrDefault(service, defaultService).describe(server, args, client);

                    }
                    case "known" -> {
                        for (String server : client.knownServers()) {
                            System.out.printf("Server at %s\n", server);
                        }
                        for (String service : validServices.keySet()) {
                            System.out.printf("Available service: %s\n", service);
                        }
                    }
                    case "use" -> {
                        var server = Util.getParameter(Arrays.stream(args), "server=");
                        var service = Util.getParameter(Arrays.stream(args), "service=")
                                .filter(validServices::containsKey);
                        server.ifPresent(s -> {
                            targetServer = s;
                            System.out.println("default server set");
                        });
                        service.ifPresent(s -> {
                            targetService = s;
                            System.out.println("default service set");
                        });
                        if (service.isEmpty() && server.isEmpty()) {
                            System.out.println("server and/or valid service required");
                        }
                    }
                    case "help", "?" -> {
                        var service = Util.getParameter(Arrays.stream(args), "service=")
                                .filter(validServices::containsKey);
                        if (service.isEmpty()) {
                            System.out.println("""
                                    available service commands:
                                    get <- fetches values exposed by the service
                                    set <- sets a something exposed by a service
                                    describe <- gets general information from service
                                    sub <- subscribes to stream of values produced by a service
                                    help service=<service name> <- service specific help page
                                                                        
                                    available global commands:
                                    unsub channelId=<channelId> <- unsubscribe from value stream
                                        WARNING! due to command line nature of this tool the unsubscribe command
                                        most likely has to be pasted in between the readings from the stream
                                    known <- list previously used servers and available services
                                    use <- sets a default server and/or service
                                    exit <- exit the cmd
                                                                        
                                    all service commands + use command accept a server and service parameters
                                    all parameters are given in the form <parameter name>=<parameter value>
                                    optional parameters are inside []""");
                        } else {
                            validServices.getOrDefault(service.get(), defaultService).help();
                        }
                    }
                    case "exit" -> {
                        return;
                    }

                    default -> System.out.println("?");
                }

            } catch (StatusRuntimeException e) {
                switch (e.getStatus().getCode()) {
                    case UNAVAILABLE ->
                            System.out.printf("the server is currently unavailable: %s\n", e.getStatus().getDescription());
                    case INVALID_ARGUMENT -> System.out.printf(
                            "at least one of the arguments given was invalid: %s\n",
                            e.getStatus().getDescription());
                    case CANCELLED -> System.out.println("the call was cancelled");
                    default -> System.out.printf("unknown error occurred %s\n", e.getStatus().getDescription());
                }
            } catch (Exception e) {
                System.out.printf("unknown error occured %s\n", e.getMessage());
            }
        }
    }
}
