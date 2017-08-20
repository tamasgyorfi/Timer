package hu.bets.timer;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);
    private static final String TIME_CHANNEL = "time_channel";

    private PubNub pubNub;

    public MessageSender(PubNub pubNub) {
        this.pubNub = pubNub;
    }

    public void sendMessage(String time) {
        pubNub.publish()
                .message(Arrays.asList("timeTick", time))
                .channel(TIME_CHANNEL)
                .shouldStore(true)
                .usePOST(true)
                .async(new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {
                        if (status.isError()) {
                            LOGGER.info("Error happened while publishing: {}", status.toString());
                        } else {
                            LOGGER.info("Publish worked! timetoken: {}", result.getTimetoken());
                        }
                    }
                });
    }
}
