package net.ertele.objects;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

	public static String hash(String password) {
		String hashword = null;

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);
		} catch (NoSuchAlgorithmException nsae) {
			// do something here
		}
		return hashword;
	}


	public static boolean isNull(String s) {
		return s == null || "".equals(s);
	}

	public static boolean isEmailValid(String s) {

		Matcher m = Pattern.compile(".+@.+\\.[a-z]+").matcher(s);

		if (m.matches())
			return true;

		return false;
	}
}
