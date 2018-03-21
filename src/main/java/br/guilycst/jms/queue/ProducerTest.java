package br.guilycst.jms.queue;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class ProducerTest {

    public static void main(String[] args) throws Exception {
        /*JMS 1.1*/
        /* JNDI - Java Naming and Directory Interface - BEGIN*/
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination queue = (Destination) context.lookup("financeiro");
        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 1000; i++) {
            TextMessage textMessage = session.createTextMessage("#" + i);
            producer.send(textMessage);
        }

        connection.close();
        context.close();
    }
}
