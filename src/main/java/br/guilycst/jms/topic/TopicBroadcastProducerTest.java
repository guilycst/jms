package br.guilycst.jms.topic;

import br.guilycst.jms.model.Pedido;
import br.guilycst.jms.model.PedidoFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXB;
import java.io.StringWriter;

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

        Pedido pedido = new PedidoFactory().geraPedidoComValores();
        StringWriter writer = new StringWriter();
        JAXB.marshal(pedido, writer);
        String xml = writer.toString();

        Message textMessage = session.createTextMessage(xml);

        producer.send(textMessage);

        connection.close();
        context.close();
    }
}
