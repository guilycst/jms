package br.guilycst.jms.queue.priority;

import br.guilycst.jms.SetupHelper;

import javax.jms.*;
import java.util.Scanner;

public class PriorityQConsumerTest {

    public static void main(String[] args) throws Exception {
        Connection connection = SetupHelper.getConnection();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination queue = SetupHelper.lookup("priority");
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MessageListener() { // observer
            public void onMessage(Message message) {
                TextMessage textMsg = (TextMessage) message;
                try {
                    System.out.println("Msg: "+ textMsg.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        new Scanner(System.in).nextLine(); // Just to block the execution

        connection.close();
    }
}
