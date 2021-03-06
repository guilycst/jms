package br.guilycst.jms.topic.selector;

import br.guilycst.jms.SetupHelper;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Optional;
import java.util.Scanner;

public class TopicBroadcastConsumerTest {

    public static void main(String[] args) throws Exception {

        Connection connection = SetupHelper.getConnection(Optional.of("estoque"));
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = SetupHelper.lookup("loja");
        MessageConsumer consumer = session.createDurableSubscriber(topic, "subscriber-selector", "ebook = true AND ebook is not null", true);

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
