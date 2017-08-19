package hu.bets.timer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MessageSender {

    private Producer<String, String> producer;

    public MessageSender(Producer<String, String> producer) {
        this.producer = producer;
    }

    public void sendMessage(String time) {
        producer.send(new ProducerRecord<String, String>("61o6-timer", time));
    }
}
