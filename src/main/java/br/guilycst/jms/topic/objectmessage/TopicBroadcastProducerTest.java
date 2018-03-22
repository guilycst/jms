package br.guilycst.jms.topic.objectmessage;

import br.guilycst.jms.SetupHelper;
import br.guilycst.jms.model.Pedido;
import br.guilycst.jms.model.PedidoFactory;

import javax.jms.*;
import javax.xml.bind.JAXB;
import java.io.StringWriter;

public class TopicBroadcastProducerTest {

    public static void main(String[] args) throws Exception {
        Connection connection = SetupHelper.getConnection();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = SetupHelper.lookup("loja");

        MessageProducer producer = session.createProducer(topic);

        Pedido pedido = new PedidoFactory().geraPedidoComValores();

        Message textMessage = session.createObjectMessage(pedido);

        producer.send(textMessage);

        connection.close();
    }
}
