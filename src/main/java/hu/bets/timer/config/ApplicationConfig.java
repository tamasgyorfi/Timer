package hu.bets.timer.config;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import hu.bets.common.util.EnvironmentVarResolver;
import hu.bets.timer.MessageSender;
import hu.bets.timer.TimeTickProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PubNub pubNub() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setPublishKey(EnvironmentVarResolver.getEnvVar("PUBNUB_PUBLISH_KEY"));
        pnConfiguration.setSubscribeKey(EnvironmentVarResolver.getEnvVar("PUBNUB_SUBSCRIBE_KEY"));
        pnConfiguration.setSecure(false);

        return new PubNub(pnConfiguration);
    }

    @Bean
    public MessageSender messageSender(PubNub pubNub) {
        return new MessageSender(pubNub);
    }

    @Bean
    public TimeTickProvider timeTickProvider(MessageSender messageSender) {
        return new TimeTickProvider(messageSender);
    }
}
