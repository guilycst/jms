package br.guilycst.jms.topic.selector;

import br.guilycst.jms.SetupHelper;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Optional;

public class TopicBroadcastProducerTest {

    public static void main(String[] args) throws Exception {
        Connection connection = SetupHelper.getConnection();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination topic = SetupHelper.lookup("loja");

        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("I'm not an ebook");
        textMessage.setBooleanProperty("ebook", false);
        producer.send(textMessage);

        textMessage = session.createTextMessage("I'm an ebook");
        textMessage.setBooleanProperty("ebook", true);
        producer.send(textMessage);

        textMessage = session.createTextMessage("I'm an ebook");
        //textMessage.setBooleanProperty("ebook", null);
        producer.send(textMessage);

        connection.close();
    }
}
