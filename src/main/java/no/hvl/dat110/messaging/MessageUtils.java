package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data;

		// TODO - START
		 // check format so it matches the message object
		if (message == null || message.getData().length > Byte.MAX_VALUE) {
			throw new IllegalArgumentException("Wrong payload");
		}

		// format segment
		segment = new byte[1 + message.getData().length]; // +1 fordi vi har en header på 1 byte
 		data = message.getData(); // henter melding
		segment[0] = (byte) data.length; // segment header

		// payload må starte fra 1.
		// fyller segmentet med data
		for (int i = 1; i < data.length; i++) {
			segment[i] = data[i-1];
		}

		// TODO - END
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		// TODO - START
		// decapsulate segment and put received payload data into a message

		int segmentLength = segment[0]; // read segment length
		byte[] data = new byte[segmentLength]; // create new object to transfer the data to

		// transfer data from the segment to our new created data object
		for (int i = 1; i < segmentLength; i++ ) {
			data[i-1] = segment[i];
		}

		// give the value of our created object
		message = new Message(data);

		
		// TODO - END
		
		return message;
		
	}
	
}
