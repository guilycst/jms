package br.guilycst.jms.topic;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class TopicBroadcastConsumerTest {

    public static void main(String[] args) throws Exception {
        /*JMS 1.1*/
        /* JNDI - Java Naming and Directory Interface - BEGIN*/
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.setClientID("estoque");
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = (Topic) context.lookup("loja");
        MessageConsumer consumer = session.createDurableSubscriber(topic, "subscriber"); // a durable subscriber recieves messages sent when the consumer was offline

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
        context.close();
    }
}
