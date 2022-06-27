package com.example.application.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypt {

	public static String md5(String mensagem)  {
        return crypt(mensagem, "MD5");
    }
    
    private static String crypt(String mensagem, String algoritmo) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algoritmo);
			md.update(mensagem.getBytes());
			
			String digest = bytesToHex(md.digest());
			
			return digest;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	private static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();

		for (byte b: bytes) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}
}
