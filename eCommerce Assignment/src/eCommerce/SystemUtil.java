package eCommerce;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SystemUtil {

	private static final Random RANDOM = new SecureRandom();
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;

	public static byte[] hash(char[] password) {

		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);

		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("California State University, Los Angeles");
			return skf.generateSecret(spec).getEncoded();

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException("Error while hashing a password: " + e.getMessage(), e);
		} finally {
			spec.clearPassword();
		}
	}

	public static boolean isExpectedPassword(char[] password, byte[] expectedHash) {

		byte[] pwdHash = hash(password);
		Arrays.fill(password, Character.MIN_VALUE);

		if (pwdHash.length != expectedHash.length) {
			return false;
		}

		for (int i = 0; i < pwdHash.length; i++) {
			if (pwdHash[i] != expectedHash[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static String getUUID() {
		UUID uniqueId = UUID.randomUUID();
		return uniqueId.toString().replaceAll("\\-", "").toUpperCase();
	}

	public static String getCookie(HttpServletRequest request, String name) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static boolean validation(String name) {

		if (name == null || name.trim().length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean validation(char[] name) {

		if (name == null || name.length == 0) {
			return false;
		} else {
			return true;
		}
	}

}
