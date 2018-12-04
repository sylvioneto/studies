package br.com.spedroza.rabbitmq.queue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import br.com.spedroza.test.builder.TestMessage;

public class ProducerQ {

	private final static String QUEUE_NAME = "test.incoming";

	public static void main(String[] argv) throws Exception {

		// create a connection to the server
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

			// declare will create the queue in case it does not exists
			System.out.println("Queue declare...");
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);

			// send messages
			System.out.println("Sending message.s..");

			// send 10 text plan messages
			for (int i = 0; i < 10; i++) {
				String message = "Message no " + i;
				channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
				System.out.println("Message Sent " + message);
			}

			// send 5 json messages
			for (int i = 0; i < 5; i++) {
				String message = new TestMessage().getJsonMessage().toString();
				channel.basicPublish("", QUEUE_NAME,
						new AMQP.BasicProperties.Builder()
			               .contentType("application/json")
			               .deliveryMode(2)
			               .priority(1)
			               .messageId(String.valueOf(i))
			               .build(),
			               message.getBytes());
				System.out.println("Message Sent " + message);
			}

		} catch (Exception e) {
			System.out.println("ProducerQ exception: " + e.getMessage());
		}
	}
}
