package br.com.spedroza.jms.topic;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * This class will connect to ActiveMQ and consume a destination named fila.financeiro
 * 
 */
public class TestConsumerTTwo {

	public static void main(String[] args) throws NamingException, JMSException {
		System.out.println("Inside TestConsumerTTwo...");
		// create a context. it will read from jndi.properties
		InitialContext context = new InitialContext();

		// create a connection
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");

		// create a connection
		Connection con = cf.createConnection();
		con.setClientID("consumerTwo");
		
		// start the connection to the mom
		con.start();

		// create a session. it handles ack and transaction
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// destination is the queue or topic. its name is defined in the jndi.properties
		System.out.println("Lookup for topic...");
		Topic destT = (Topic) context.lookup("loja");

		// consumer to get the message from the queue
		MessageConsumer consumer = session.createDurableSubscriber(destT, "assinatura");

		// set a queue listener
		consumer.setMessageListener(new MessageListener() {

			// on message received, this method will execute
			@Override
			public void onMessage(Message message) {
				// cast from message to text and print
				TextMessage txtmsg = (TextMessage) message;
				try {
					System.out.println("Msg received: " + txtmsg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
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
