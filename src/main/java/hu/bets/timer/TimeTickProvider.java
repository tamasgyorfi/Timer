package hu.bets.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeTickProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTickProvider.class);

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private MessageSender messageSender;

    public TimeTickProvider(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void startTimeTickProvider() {
        executor.scheduleAtFixedRate(() -> {
            String time = ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_DATE_TIME);
            LOGGER.info("Sending time tick: {}", time);
            messageSender.sendMessage(time);
            LOGGER.info("Message successfully sent.");
        }, 0, 5, TimeUnit.MINUTES);
    }
}
