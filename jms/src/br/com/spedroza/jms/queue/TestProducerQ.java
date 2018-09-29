package br.com.spedroza.jms.queue;

import java.io.StringWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXB;

import br.com.spedroza.jms.model.FundTransfer;
import br.com.spedroza.jms.model.FundTransferFactory;

/*
 * This class will connect to ActiveMQ and consume a destination named fila.financeiro
 * 
 */
public class TestProducerQ {

	public static void main(String[] args) throws NamingException, JMSException {
		System.out.println("Inside TestProducerQ...");
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
		// create a context. it will read from jndi.properties
		InitialContext context = new InitialContext();

		// create a connection
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");

		// create a connection
		System.out.println("Creating connection...");
		Connection con = cf.createConnection();

		// start the connection to the mom
		con.start();

		// create a session. it handles ack and transaction
		System.out.println("Creating session...");
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// destination is the queue or topic. its name is defined in the jndi.properties
		System.out.println("Lookup for QUEUE...");
		Destination destQ = (Destination) context.lookup("financeiro");

		// producer to SEND the message to the queue
		MessageProducer producer = session.createProducer(destQ);

		// posting xml messages
		/*
		 * for (int i = 0; i < 10; i++) { // parse object to xml StringWriter writer =
		 * new StringWriter(); JAXB.marshal(new
		 * FundTransferFactory().getMockFundTransfer(), writer); String xml =
		 * writer.toString();
		 * 
		 * // create a message Message msg = session.createTextMessage(xml);
		 * 
		 * //send the message System.out.println("Sending message... \n"+xml);
		 * producer.send(msg); }
		 */

		// Posting objects messages

		FundTransfer ft = new FundTransferFactory().getMockFundTransfer();
		Message msg = session.createObjectMessage(ft);
		msg.setBooleanProperty("ebook", false);
		System.out.println("Sending message..." + ft.toString());
		producer.send(msg);
		
		/*
		 *  it is possible to define other attributes to the message
		 *  DeliveryMode.NON_PERSISTENT activeMQ will not save in its internal db
		 *  0 to 9 = priority. the AMQ will dequeue the highest priority number first
		 *  5000 = 5 secs is the time to live for the example below. After that message is deleted
		 *  
		 *  producer.send(msg, DeliveryMode.NON_PERSISTENT, 0, 5000);
		 */
		

		// close the open objects
		session.close();
		con.close();
		context.close();
		System.out.println("End of TestProducerQ...");
	}

}
