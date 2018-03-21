package br.guilycst.jms.topic;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicBroadcastProducerTest {

    public static void main(String[] args) throws Exception {
        /*JMS 1.1*/
        /* JNDI - Java Naming and Directory Interface - BEGIN*/
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination topic = (Destination) context.lookup("loja");

        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("topic broadcast test");
        producer.send(textMessage);

        connection.close();
        context.close();
    }
}
