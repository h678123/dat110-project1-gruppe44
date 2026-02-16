package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		// TODO - START
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format
		if(payload==null) {
			payload = new byte[127];
			payload[0] = rpcid;
			return rpcmsg; // TODO er dette en bug? returnerer null?
		}
		if(payload.length>126) {
			throw new IllegalArgumentException("Longer than 127 bytes");
		}
		else {
			rpcmsg = new byte[127];
		for(int i = 0; i<payload.length;i++) {
			rpcmsg[i+1] = payload[i];
		}
		
		rpcmsg[0]=rpcid;
		}
		
		
		// TODO - END
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = null;
		
		// TODO - START
		
		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
		byte rpcid;
		rpcid = rpcmsg[0];
		
		payload = new byte[rpcmsg.length-1];
		
		if(rpcmsg.length!=1) {
			for(int i = 0; i<payload.length; i++) {
				payload[i]=rpcmsg[i+1];
			}
		}
		
		// TODO - END
		
		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		encoded = str.getBytes(StandardCharsets.UTF_8);
		
		
		
		// TODO - END
		
		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = null; 
		
		// TODO - START 
		int i = 0;
		
		while(i<data.length&&data[i] !=0) {
			i++;
		}
		
		decoded = new String(data, 0, i, StandardCharsets.UTF_8);
		
		// TODO - END
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		encoded = new byte[0];
				
		// TODO - END
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		// TODO
		
		if(data.length==0) {
			throw new IllegalArgumentException("Dataen motatt er tom");
		}
		
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	// integer to byte array representation
	public static byte[] marshallInteger(int x) {

		byte[] encoded = new byte[4];

		// TODO - START
		// flytter første 8 bytes 24 bits til høyre
		// andre set med bytes 16 osv..
		// dette er for å sammenligne med 11111111 og få satt de inn i arrayen
		encoded[0] = (byte) ((x >> 24) & 0xFF);
		encoded[1] = (byte) ((x >> 16) & 0xFF);
		encoded[2] = (byte) ((x >> 8) & 0xFF);
		encoded[3] = (byte) (x & 0xFF);

		// TODO - END

		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;
		
		// TODO - START 

		decoded |= (data[0] & 0xFF) << 24;
		decoded |= (data[1] & 0xFF) << 16;
		decoded |= (data[2] & 0xFF) << 8;
		decoded |= (data[3] & 0xFF);
		// TODO - END
		
		return decoded;
		
	}
}
