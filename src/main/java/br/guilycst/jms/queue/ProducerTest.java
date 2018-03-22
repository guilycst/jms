package br.guilycst.jms.queue;

import br.guilycst.jms.SetupHelper;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class ProducerTest {

    public static void main(String[] args) throws Exception {
        Connection connection = SetupHelper.getConnection();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination queue = SetupHelper.lookup("financeiro");
        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 1000; i++) {
            TextMessage textMessage = session.createTextMessage("#" + i);
            producer.send(textMessage);
        }

        connection.close();
    }
}
