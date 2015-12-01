package jpelc.learning.jms.queue;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

@MessageDriven(name = "HighPriorityBookingQueueReceiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = BookingQueueDefinition.BOOKING_QUEUE),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "priority = 'HIGH'")

})
public class HighPriorityBookingQueueReceiver implements MessageListener {

    private static final Logger logger = Logger.getLogger(HighPriorityBookingQueueReceiver.class.getName());

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
