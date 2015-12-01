package jms.queue;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

@MessageDriven(name = "BookingQueueReceiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = BookingQueueDefinition.BOOKING_QUEUE),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BookingQueueReceiver implements MessageListener {

    private static final Logger logger = Logger.getLogger(BookingQueueReceiver.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            final String msg = message.getBody(String.class);
            logger.info("Received msg: " + msg);
        } catch (JMSException e) {
            logger.severe(e.toString());
        }
    }
}
