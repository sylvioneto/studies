package jms;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * This class will connect to ActiveMQ and consume a destination named fila.financeiro
 * 
 */
public class TestQueueBrowser {

	public static void main(String[] args) throws NamingException, JMSException {
		
		// create a context. it will read from jndi.properties
		InitialContext context = new InitialContext();

		// create a connection factory and a connection to the mom
		ConnectionFactory cf = (ConnectionFactory)context.lookup("ConnectionFactory");
		
		// create a connection
		Connection con = cf.createConnection();
		
		// start the connection to the mom
		con.start();
		
		// create a session. it handles ack and transaction
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// destination is where the messages are. the jndi name is defined in the jndi.properties
		Destination destQ = (Destination) context.lookup("financeiro");
		
		// queue browser will look at the messages without removing them from the queue
		QueueBrowser browser = session.createBrowser((Queue) destQ);
		
		Enumeration msgs = browser.getEnumeration();
		while (msgs.hasMoreElements()) { 
		    TextMessage msg = (TextMessage) msgs.nextElement(); 
		    System.out.println("Message: " + msg.getText()); 
		}
		
		// close the open objects
		session.close();
		con.close();    
		context.close();
	}

}
