package agh.ics.sr.ParserHelperClasses;

public class PortParse {

    private final int port;
    public PortParse(String string) {
        port = Integer.parseInt(string);
        if(port < 1 || port > 65535)
            throw new IllegalArgumentException("port must be in valid range");
    }

    public int getPort() {
        return port;
    }
}
