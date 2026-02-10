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
		byte messageLength = (byte)message.getData().length; // henter lengden på meldingens data som byte (maks 127)
		segment = new byte[128]; // oppretter ny segment med fast størrelse 128 bytes
		segment[0] = messageLength; // setter første byte i segmentet til å være lengden på meldingen
		System.arraycopy(message.getData(), 0, segment, 1, messageLength); // kopierer meldingsdataene til segmentet fra pos 1
		// TODO - END
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		byte[] newSegment;
		
		// TODO - START
		// decapsulate segment and put received payload data into a message
		int segmentLength = segment[0]; // henter lendgen på meldingen fra første byte i segmentet
		newSegment = new byte[segment[0]]; // oppretter nytt array for å holde meldingsdataene
		System.arraycopy(segment, 1, newSegment, 0, segmentLength); // kopierer meldingsdataene fra segmentet fra pos 1

		message = new Message(newSegment); // nytt message objekt med den dekapulerte dataen


		// TODO - END
		
		return message;
		
	}
	
}
