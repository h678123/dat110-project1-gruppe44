package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {

	// the up to 127 bytes of data (payload) that a message can hold
	private byte[] data;

	// construction a Message with the data provided
	public Message(byte[] data) {
		
		// TODO - START
			// hvis data er null, eller er større enn 127 får man error
		if (data == null || data.length > Byte.MAX_VALUE) {
				throw new IllegalArgumentException("data cannot be null or longer than 127 bytes");
		}

		this.data = data;

		// TODO - END
	}

	public byte[] getData() {
		return this.data; 
	}

}
