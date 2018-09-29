package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * This class will connect to ActiveMQ and consume a destination named fila.financeiro
 * 
 */
public class TestProducer {

	public static void main(String[] args) throws NamingException, JMSException {
		System.out.println("Inside TestProducer...");
		// create a context. it will read from jndi.properties
		InitialContext context = new InitialContext();

		// create a connection factory and a connection to the mom
		ConnectionFactory cf = (ConnectionFactory)context.lookup("ConnectionFactory");
		
		// create a connection
		System.out.println("Creating connection...");
		Connection con = cf.createConnection();
		
		// start the connection to the mom
		con.start();
		
		// create a session. it handles ack and transaction
		System.out.println("Creating session...");
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// destination is where the messages are. the jndi name is defined in the jndi.properties
		Destination destQ = (Destination) context.lookup("financeiro");
		
		// producer to SEND the message to the queue
		MessageProducer producer = session.createProducer(destQ);
		
		for (int i = 0; i < 100; i++) {
			// create a message
			Message msg = session.createTextMessage("<msg><id>"+i+"</id></msg>");

			//send the message
			System.out.println("Sending message..."+i);
			producer.send(msg);			
		}
		
		// close the open objects
		session.close();
		con.close();    
		context.close();
		System.out.println("End of TestProducer...");
	}

}
