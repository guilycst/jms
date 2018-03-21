package br.guilycst.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Enumeration;
import java.util.Scanner;

public class BrowserTest {

    public static void main(String[] args) throws Exception {
        /*JMS 1.1*/
        /* JNDI - Java Naming and Directory Interface - BEGIN*/
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination queue = (Destination) context.lookup("financeiro");

        QueueBrowser browser = session.createBrowser((Queue) queue);

        Enumeration enumeration = browser.getEnumeration();
        while (enumeration.hasMoreElements()){
            System.out.println(((TextMessage)enumeration.nextElement()).getText());
        }

        connection.close();
        context.close();
    }
}
