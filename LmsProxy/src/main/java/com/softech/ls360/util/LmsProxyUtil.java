package com.softech.ls360.util;

import java.io.IOException;
import java.util.Base64;

public class LmsProxyUtil {

	public static String decodeBase64(String encoded) throws IOException {
	  	 byte[] encodedBytes = encoded.getBytes();
	   	 byte[] decodeBase64 = Base64.getDecoder().decode(encodedBytes);
	   	 return new String(decodeBase64);
	}
	
}
