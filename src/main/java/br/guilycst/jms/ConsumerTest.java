package br.guilycst.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class ConsumerTest {

    public static void main(String[] args) throws Exception {
        /*JMS 1.1*/
        /* JNDI - Java Naming and Directory Interface - BEGIN*/
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("financeiro");
        MessageConsumer consumer = session.createConsumer(fila);

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
