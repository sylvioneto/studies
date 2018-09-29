package br.com.spedroza.jms.queue;

import java.math.BigDecimal;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.spedroza.jms.model.FundTransfer;

/*
 * This class will connect to ActiveMQ and consume a destination named fila.financeiro
 * 
 */
public class TestConsumerDLQ {

	public static void main(String[] args) throws NamingException, JMSException {

		// create a context. it will read from jndi.properties
		InitialContext context = new InitialContext();

		// create a connection
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");

		// create a connection
		Connection con = cf.createConnection();

		// start the connection to the mom
		con.start();

		// create a session. it handles ack and transaction
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// destination is the queue or topic. its name is defined in the jndi.properties
		System.out.println("Lookup for QUEUE...");
		Destination fila = (Destination) context.lookup("DLQ");

		// consumer to get the message from the queue
		MessageConsumer consumer = session.createConsumer(fila);

		// set a queue listener
		consumer.setMessageListener(new MessageListener() {

			// on message received, this method will execute
			@Override
			public void onMessage(Message message) {
				System.out.println("Msg received:"+message.toString());
			}
		});
		System.out.println("Connected to ActiveMQ!");

		// use this scanner to stop the runtime here
		System.out.println("Press enter to stop...");
		new Scanner(System.in).nextLine();
		System.out.println("Stopping the Message consumer...");

		// close the open objects
		session.close();
		con.close();
		context.close();
	}

}
