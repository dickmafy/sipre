package pe.mil.ejercito.sipr.commons;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encripta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String HASH_MD5;
	public static final String HASH_MD2;
	public static final String HASH_SHA1;
	public static final String HASH_SHA1PRNG;

	static {
		HASH_MD5 = "MD5";
		HASH_MD2 = "MD2";
		HASH_SHA1 = "SHA-1";
		HASH_SHA1PRNG = "SHA1PRNG";
	}

	public Encripta() {
		super();
	}

	private static String toHexadecimal(byte[] diggest) {
		String h = "";
		for (byte paso : diggest) {
			int b = paso & 0xff;
			if (Integer.toHexString(b).length() == 1) {
				h += "0";
			}
			h += Integer.toHexString(b);
		}
		return h;
	}

	public static String encripta(String message, String algoritmo) {
		byte[] digest = null;
		byte[] buffer = message.getBytes();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algoritmo);
			messageDigest.reset();
			messageDigest.update(buffer);
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println("Error creando Digest");
		}
		return toHexadecimal(digest);
	}

	public SecretKeySpec generaKeyStatic() throws NoSuchAlgorithmException {
		Security.addProvider(new sun.security.provider.Sun());
		SecureRandom sr = SecureRandom.getInstance(HASH_SHA1PRNG);
		sr.setSeed("000000ECBA1127878".getBytes());
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(128, sr);
		SecretKey key = kg.generateKey();
		byte[] raw = key.getEncoded();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		return skeySpec;
	}

	public SecretKeySpec generaKey() throws NoSuchAlgorithmException {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		return skeySpec;
	}

	public byte[] encriptar(SecretKeySpec clave, String cadena) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, clave);
		byte[] encrypted = cipher.doFinal(cadena.getBytes());
		return encrypted;
	}

	public String desencriptar(SecretKeySpec clave, byte[] password) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, clave);
		byte[] original = cipher.doFinal(password);
		String originalString = new String(original, "UTF8");
		return originalString;
	}

	public static String asHex(byte[] buf) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	public static byte[] hexToBytes(String hex) {
		return hexToBytes(hex.toCharArray());
	}

	public static byte[] hexToBytes(char[] hex) {
		int length = hex.length / 2;
		byte[] raw = new byte[length];
		for (int i = 0; i < length; i++) {
			int high = Character.digit(hex[i * 2], 16);
			int low = Character.digit(hex[i * 2 + 1], 16);
			int value = (high << 4) | low;
			if (value > 127)
				value -= 256;
			raw[i] = (byte) value;
		}
		return raw;
	}
}
