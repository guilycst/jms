package br.guilycst.jms.topic;

import br.guilycst.jms.SetupHelper;
import br.guilycst.jms.model.Pedido;
import br.guilycst.jms.model.PedidoFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXB;
import java.io.StringWriter;
import java.util.Optional;

public class TopicBroadcastProducerTest {

    public static void main(String[] args) throws Exception {
        Connection connection = SetupHelper.getConnection();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = SetupHelper.lookup("loja");

        MessageProducer producer = session.createProducer(topic);

        Pedido pedido = new PedidoFactory().geraPedidoComValores(); // Canonical Data Model
        StringWriter writer = new StringWriter();
        JAXB.marshal(pedido, writer);
        String xml = writer.toString();

        Message textMessage = session.createTextMessage(xml);

        producer.send(textMessage);

        connection.close();
    }
}
