package br.guilycst.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.InputStream;
import java.util.Optional;

public class SetupHelper {

    static {

        try {
            InputStream config = SetupHelper.class.getClassLoader().getResourceAsStream("config.properties");
            System.getProperties().load(config);
            SetupHelper.context = new InitialContext();

            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    try {
                        context.close();
                        System.out.println("Context closed!");
                    } catch (NamingException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static  InitialContext context;

    public static Connection getConnection() throws Exception {
        return getConnection(Optional.empty());
    }

    public static Connection getConnection(Optional<String> clientID) throws Exception {
        /*JMS 1.1*/
        /* JNDI - Java Naming and Directory Interface - BEGIN*/
        ConnectionFactory factory = lookup("ConnectionFactory");
        Connection connection = factory.createConnection("admin","senha");
        clientID.ifPresent(id -> {
            try {
                connection.setClientID(id);
            } catch (JMSException e) {
                throw  new RuntimeException(e);
            }
        });
        connection.start();
        return connection;
    }

    public static <T> T lookup(String name) throws NamingException {
        return (T) context.lookup(name);
    }
}
