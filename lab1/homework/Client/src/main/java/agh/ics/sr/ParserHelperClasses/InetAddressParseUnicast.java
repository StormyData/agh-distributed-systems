package agh.ics.sr.ParserHelperClasses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressParseUnicast {
    private final InetAddress address;

    public InetAddressParseUnicast(String str) throws UnknownHostException {
        address = InetAddress.getByName(str);
        if(address.isMulticastAddress())
            throw new IllegalArgumentException("the address cannot be a multicast address");
    }

    public InetAddress getAddress() {
        return address;
    }
}
