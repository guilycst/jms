package br.guilycst.jms.queue.dlq;

import br.guilycst.jms.SetupHelper;

import javax.jms.*;
import java.util.Enumeration;

public class BrowserDLQTest {

    public static void main(String[] args) throws Exception {
        Connection connection = SetupHelper.getConnection();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination queue = SetupHelper.lookup("dlq");

        QueueBrowser browser = session.createBrowser((Queue) queue);

        Enumeration enumeration = browser.getEnumeration();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }

        connection.close();
    }
}
