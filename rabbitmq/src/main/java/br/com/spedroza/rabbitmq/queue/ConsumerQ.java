package br.com.spedroza.rabbitmq.queue;

import java.util.Calendar;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ConsumerQ {

	// queue name
	private final static String QUEUE_NAME = "test.incoming";

	public static void main(String[] argv) throws Exception {

		// create a connection to the server
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

		// queue declare - it will create it in case it does not exists
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    
	    System.out.println("Waiting for messages. To exit press CTRL+C");
		
	    //deliver call back
	    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");
	        System.out.println(Calendar.getInstance().getTime()+ " - Received - " + message);
	    };
	    
	    // basic consumer using autoAck
	    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
	}
	
}
