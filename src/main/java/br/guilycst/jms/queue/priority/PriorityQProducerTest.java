package br.guilycst.jms.queue.priority;

import br.guilycst.jms.SetupHelper;

import javax.jms.*;

public class PriorityQProducerTest {

    public static void main(String[] args) throws Exception {
        Connection connection = SetupHelper.getConnection();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination queue = SetupHelper.lookup("priority");
        MessageProducer producer = session.createProducer(queue);

        TextMessage textMessage = session.createTextMessage("Last");
        producer.send(textMessage, DeliveryMode.NON_PERSISTENT, 0, 50000);

        textMessage = session.createTextMessage("First");
        producer.send(textMessage, DeliveryMode.NON_PERSISTENT, 9, 50000);

        textMessage = session.createTextMessage("Second");
        producer.send(textMessage, DeliveryMode.NON_PERSISTENT, 3, 50000);


        connection.close();
    }
}
