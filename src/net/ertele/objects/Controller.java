package net.ertele.objects;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class Controller {
	
	public static final String ALERT_NULL_EMAIL = "Lütfen emailinizi giriniz";
	public static final String ALERT_NULL_NAME = "Lütfen adınızı giriniz";
	public static final String ALERT_NULL_PASS = "Lütfen şifrenizi giriniz";
	
	public static final String ALERT_WRONG_EMAIL = "Lütfen geçerli bir email giriniz, örn: fti@gmail.com";
	public static final String ALERT_WRONG_EMAILPASS = "Email/Şifre uyumsuzluğu";
	
	public static final String ALERT_HASMEMBER_EMAIL = "Bu email zaten kullanımda";
	
	public static List<String> regUserContr(HttpServletRequest req){
		List<String> errors = new ArrayList<String>();
		
		if (Utility.isNull(req.getParameter("n"))){
			errors.add(ALERT_NULL_NAME);
		}
		if (Utility.isNull(req.getParameter("p"))){
			errors.add(ALERT_NULL_PASS);
		}
		if (Utility.isNull(req.getParameter("e"))){
			errors.add(ALERT_NULL_EMAIL);
		}else if (!Utility.isEmailValid(req.getParameter("e"))){
			errors.add(ALERT_WRONG_EMAIL);
		}
		
		return errors;
	}
	public static List<String> regUserContr(UserObject uo){
		List<String> errors = new ArrayList<String>();
		
		if (Utility.isNull(uo.getN())){
			errors.add(ALERT_NULL_NAME);
		}
		if (Utility.isNull(uo.getP())){
			errors.add(ALERT_NULL_PASS);
		}
		if (Utility.isNull(uo.getE())){
			errors.add(ALERT_NULL_EMAIL);
		}else if (!Utility.isEmailValid(uo.getE())){
			errors.add(ALERT_WRONG_EMAIL);
		}
		
		return errors;
	}
	public static List<String> loginContr(HttpServletRequest req){
		List<String> errors = new ArrayList<String>();
		
		if (Utility.isNull(req.getParameter("p"))){
			errors.add(ALERT_NULL_PASS);
		}
		if (Utility.isNull(req.getParameter("e"))){
			errors.add(ALERT_NULL_EMAIL);
		}else if (!Utility.isEmailValid(req.getParameter("e"))) {
			errors.add(ALERT_WRONG_EMAIL);
		}
		
		return errors;
	}
	public static List<String> loginContr(UserObject uo){
		List<String> errors = new ArrayList<String>();
		
		if (Utility.isNull(uo.getP())){
			errors.add(ALERT_NULL_PASS);
		}
		if (Utility.isNull(uo.getE())){
			errors.add(ALERT_NULL_EMAIL);
		}else if (!Utility.isEmailValid(uo.getE())) {
			errors.add(ALERT_WRONG_EMAIL);
			
		}
		return errors;
	}
	
	public static String decodeURIComponent(String encodedURI) {
		  char actualChar;
		 
		  StringBuffer buffer = new StringBuffer();
		 
		  int bytePattern, sumb = 0;
		 
		  for (int i = 0, more = -1; i < encodedURI.length(); i++) {
		   actualChar = encodedURI.charAt(i);
		 
		   switch (actualChar) {
		    case '%': {
		     actualChar = encodedURI.charAt(++i);
		     int hb = (Character.isDigit(actualChar) ? actualChar - '0'
		       : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
		     actualChar = encodedURI.charAt(++i);
		     int lb = (Character.isDigit(actualChar) ? actualChar - '0'
		       : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
		     bytePattern = (hb << 4) | lb;
		     break;
		    }
		    case '+': {
		     bytePattern = ' ';
		     break;
		    }
		    default: {
		     bytePattern = actualChar;
		    }
		   }
		 
		   if ((bytePattern & 0xc0) == 0x80) { // 10xxxxxx
		    sumb = (sumb << 6) | (bytePattern & 0x3f);
		    if (--more == 0)
		     buffer.append((char) sumb);
		   } else if ((bytePattern & 0x80) == 0x00) { // 0xxxxxxx
		    buffer.append((char) bytePattern);
		   } else if ((bytePattern & 0xe0) == 0xc0) { // 110xxxxx
		    sumb = bytePattern & 0x1f;
		    more = 1;
		   } else if ((bytePattern & 0xf0) == 0xe0) { // 1110xxxx
		    sumb = bytePattern & 0x0f;
		    more = 2;
		   } else if ((bytePattern & 0xf8) == 0xf0) { // 11110xxx
		    sumb = bytePattern & 0x07;
		    more = 3;
		   } else if ((bytePattern & 0xfc) == 0xf8) { // 111110xx
		    sumb = bytePattern & 0x03;
		    more = 4;
		   } else { // 1111110x
		    sumb = bytePattern & 0x01;
		    more = 5;
		   }
		  }
		  return buffer.toString();
		 }
}
