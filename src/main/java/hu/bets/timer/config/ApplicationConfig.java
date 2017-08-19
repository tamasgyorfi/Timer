package hu.bets.timer.config;

import hu.bets.common.util.EnvironmentVarResolver;
import hu.bets.timer.MessageSender;
import hu.bets.timer.TimeTickProvider;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class ApplicationConfig {

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", EnvironmentVarResolver.getEnvVar("CLOUDKARAFKA_BROKERS"));
        properties.put("request.required.acks", "1");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        return properties;
    }

    @Bean
    public Producer<String, String> producer(Properties properties) {
        return new KafkaProducer<String, String>(properties);
    }

    @Bean
    public MessageSender messageSender(Producer<String, String> producer) {
        return new MessageSender(producer);
    }

    @Bean
    public TimeTickProvider timeTickProvider(MessageSender messageSender) {
        return new TimeTickProvider(messageSender);
    }
}
