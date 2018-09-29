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
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.spedroza.jms.model.FundTransfer;

/*
 * This class will connect to ActiveMQ and consume a destination named fila.financeiro
 * 
 */
public class TestConsumerQ {

	public static void main(String[] args) throws NamingException, JMSException {

		// create a context. it will read from jndi.properties
		InitialContext context = new InitialContext();

		// create a connection
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");

		// create a connection
		Connection con = cf.createConnection();

		// start the connection to the mom
		con.start();

		// create a session
		// Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE); // in
		// this case it handles ack and transaction automatically
		Session session = con.createSession(true, Session.SESSION_TRANSACTED); // in this case transaction is done explicit

		// destination is the queue or topic. its name is defined in the jndi.properties
		System.out.println("Lookup for queue...");
		Destination fila = (Destination) context.lookup("financeiro");

		// consumer to get the message from the queue
		MessageConsumer consumer = session.createConsumer(fila);

		// set a queue listener
		consumer.setMessageListener(new MessageListener() {

			// on message received, this method will execute
			@Override
			public void onMessage(Message message) {

				// cast from message to text and print
				// TextMessage txtmsg = (TextMessage) message;

				// parse xml to object
				/*
				 * try { TextMessage txtmsg = (TextMessage) message; Unmarshaller unmarshaller =
				 * JAXBContext.newInstance(FundTransfer.class).createUnmarshaller();
				 * StringReader reader = new StringReader(txtmsg.getText()); FundTransfer ft =
				 * (FundTransfer) unmarshaller.unmarshal(reader);
				 * System.out.println("Received xml: "+txtmsg.getText());
				 * System.out.println("Received obj: "+ft.toString()); session.commit(); } catch
				 * (JMSException | JAXBException e) { e.printStackTrace(); }
				 */

				// parse ObjectMessage to a model object
				ObjectMessage objMsg = (ObjectMessage) message;
				try {
					FundTransfer ft = (FundTransfer) objMsg.getObject();

					// compare if the amount is greater than 0
					System.out.println("Checking amount...");
					if (ft.getAmount() != null && ft.getAmount().compareTo(BigDecimal.ZERO) == 1) {
						session.commit();
						System.out.println("Msg received: " + ft.toString());
					} else {
						System.out.println("Msg received: " + ft.toString());
						System.out.println("Amount is not bigger than ZERO");
						session.rollback();
					}

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
