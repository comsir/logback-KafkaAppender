import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class KafkaAppender extends AppenderBase<ILoggingEvent> {
    private String topic;
    private Producer<byte[],byte[]> producer;
    private String brokerList;
    private byte[] Ip;
    private Encoder encoder;




    public Encoder getEncoder() {
        return encoder;
    }

    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }



    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(String brokerList) {
        this.brokerList = brokerList;
    }

    @Override
    public void start() {
        super.start();
        Properties props = new Properties();
        props.put("metadata.broker.list", this.brokerList);
        ProducerConfig config = new ProducerConfig(props);
        this.producer = new Producer<byte[], byte[]>(config);
        try {
            Ip = InetAddress.getLocalHost().getHostAddress().getBytes();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        super.stop();
        this.producer.close();
    }
    @Override
    protected void append(ILoggingEvent event) {
        byte[] payload = encoder.encode(event);
        KeyedMessage<byte[], byte[]> data = new KeyedMessage<byte[], byte[]>(this.topic, Ip, payload);
        this.producer.send(data);
    }
}
