package hu.bets.timer.config;

import hu.bets.common.config.CommonWebConfig;
import hu.bets.common.config.model.Resources;
import hu.bets.timer.web.TimerResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CommonWebConfig.class)
public class WebConfig {

    @Bean
    public Resources resources(TimerResource timerResource) {
        return new Resources().addResource(timerResource);
    }

    @Bean
    public TimerResource timerResource() {
        return new TimerResource();
    }
}
