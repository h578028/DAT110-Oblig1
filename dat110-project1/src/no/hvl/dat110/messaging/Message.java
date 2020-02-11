package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageConfig;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload; // TODO: check for length within boundary
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = null;
		
		/*if (true)
		   throw new UnsupportedOperationException(TODO.method());
		*/
		
		int length = payload.length;
		encoded = new byte[MessageConfig.SEGMENTSIZE];
		
		encoded[0] = (byte)length;
		
		for(int i = 0; i < length; i++) {
			encoded[i+1] = payload[i];
		}
		

		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		//throw new UnsupportedOperationException(TODO.method());
		
		int length = received[0];
		payload = new byte[length];
		
		for(int i = 0; i < length; i++) {
			payload[i] = received[i+1];
		}
		
		
	}
}
