package br.guilycst.jms.queue;

import br.guilycst.jms.SetupHelper;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Enumeration;
import java.util.Scanner;

public class BrowserTest {

    public static void main(String[] args) throws Exception {
        Connection connection = SetupHelper.getConnection();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination queue = SetupHelper.lookup("financeiro");

        QueueBrowser browser = session.createBrowser((Queue) queue);

        Enumeration enumeration = browser.getEnumeration();
        while (enumeration.hasMoreElements()){
            System.out.println(((TextMessage)enumeration.nextElement()).getText());
        }

        connection.close();
    }
}
