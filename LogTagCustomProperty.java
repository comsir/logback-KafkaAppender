import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LogTagCustomProperty extends ClassicConverter {
    private String tag;
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        if (tag == null) {
            String property = System.getProperty("log.file");
            tag = property.split("userlogs/")[1];
        }
        return tag;
    }
}