import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;


public class IpCustomProperty extends ClassicConverter {
    private String Ip;

    @Override
    public String convert(ILoggingEvent event) {
        if (Ip == null) {
            try {
                Ip = InetAddress.getLocalHost().getHostAddress();
            } catch (Exception e) {}
        }
        return Ip;
    }
}

