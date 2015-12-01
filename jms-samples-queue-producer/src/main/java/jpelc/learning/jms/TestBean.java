package jpelc.learning.jms;

import jpelc.learning.jms.queue.BookingQueueProducer;
import jpelc.learning.jms.queue.Priority;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.UUID;
import java.util.logging.Logger;

@Startup
@Singleton
public class TestBean {

    private static final Logger logger = Logger.getLogger(TestBean.class.getName());

    @Inject
    private BookingQueueProducer bookingQueueProducer;

    @PostConstruct
    public void init() {
        logger.info("I am alive!");
    }

    @Schedule(hour = "*", minute = "*", second = "*/3", persistent = false)
    public void sendMessage() {
        Priority priority = System.currentTimeMillis() % 2 == 0 ? Priority.HIGH : Priority.LOW;
        String msg = "New booking, ID = " + UUID.randomUUID().toString() + "[" + priority + "]";
        logger.info("Sending msg: " + msg);
        bookingQueueProducer.sendMessage(msg, priority);
    }

}
