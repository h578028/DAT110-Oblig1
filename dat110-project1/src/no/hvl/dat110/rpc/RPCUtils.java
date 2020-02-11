package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] strBytes = str.getBytes();
		byte[] encoded = new byte[strBytes.length + 1];
		
		encoded[0] = rpcid;
		
		for(int i = 0; i < strBytes.length; i++) {
			encoded[i+1] = strBytes[i];
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		byte[] strBytes = new byte[data.length - 1];
		
		for(int i = 1; i < data.length; i++) {
			strBytes[i-1] = data[i];
		}
		
		String decoded = new String(strBytes);

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = {rpcid};

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {
		
		// TODO: unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded = new byte[5];
		
		encoded[0] = rpcid;
		
		ByteBuffer buf = ByteBuffer.allocate(4);
		buf.putInt(x);
		
		byte[] intBytes = buf.array();

		for(int i = 0; i < 4; i++) {
			encoded[i+1] = intBytes[i];
		}

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded;

		byte[] intBytes = new byte[4];
		
		for(int i = 0; i < 4; i++) {
			intBytes[i] = data[i+1];
		}
		
		decoded = ByteBuffer.wrap(intBytes).getInt();
		
		return decoded;

	}
}
