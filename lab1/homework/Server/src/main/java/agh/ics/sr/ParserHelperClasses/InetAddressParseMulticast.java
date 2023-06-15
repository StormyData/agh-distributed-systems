package agh.ics.sr.ParserHelperClasses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressParseMulticast {
    private final InetAddress address;

    public InetAddressParseMulticast(String str) throws UnknownHostException {
        address = InetAddress.getByName(str);
        if(!address.isMulticastAddress())
            throw new IllegalArgumentException("the address must be a valid multicast address");
    }

    public InetAddress getAddress() {
        return address;
    }
}
