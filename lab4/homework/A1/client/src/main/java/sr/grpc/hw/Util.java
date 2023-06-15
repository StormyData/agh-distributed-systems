package sr.grpc.hw;


import java.util.Optional;
import java.util.stream.Stream;

public class Util {
    public static Optional<String> getParameter(Stream<String> args, String paramPrefix) {
        return args
                .filter(arg -> arg.startsWith(paramPrefix))
                .findFirst()
                .map(arg -> arg.substring(paramPrefix.length()));
    }

    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
