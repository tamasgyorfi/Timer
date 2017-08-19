package hu.bets.timer;

import hu.bets.timer.config.ApplicationConfig;
import hu.bets.timer.config.WebConfig;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Starter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);

    private static ApplicationContext context = new AnnotationConfigApplicationContext(
            ApplicationConfig.class,
            WebConfig.class);

    public static void main(String[] args) {
        Starter starter = new Starter();

        starter.startServer(context.getBean(Server.class));
        context.getBean(TimeTickProvider.class).startTimeTickProvider();
        while (true) {

        }
    }

    private void startServer(Server server) {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            LOGGER.error("Unable to start the embedded server.", e);
        }
    }

}
