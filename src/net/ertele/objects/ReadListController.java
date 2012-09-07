package net.ertele.objects;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class ReadListController {
	

	public static List<String> readlistContr(HttpServletRequest req) {
		List<String> errors = new ArrayList<String>();
		
		if (Utility.isNull(req.getParameter("e"))){
			errors.add("email cannot be null");
		}
		if (Utility.isNull(req.getParameter("p"))){
			errors.add("password cannot be null");
		}
		
		if (Utility.isNull(req.getParameter("fullUrl"))){
			errors.add("url cannot be null");
		}
//		try {
//			JSONObject jsonObject;
//			jsonObject = new JSONObject(req.getParameter("new"));
//			String title = UserController.decodeURIComponent(jsonObject.getJSONObject("0").getString("url"));
//			String url = UserController.decodeURIComponent(jsonObject.getJSONObject("0").getString("title"));
//			if (Utility.isNull(title) || Utility.isNull(url)) {
//				errors.add("Url ve/ya ba�l�k bo� olamaz");
//			}
//		} catch (JSONException e) {
//			errors.add("Eklerken hata olu�tu");
//			e.printStackTrace();
//		}
						
		return errors;
		
	}
	
	public static List<String> loadReadListController(HttpServletRequest req){
		List<String> errors = new ArrayList<String>();
		if (Utility.isNull(req.getParameter("e"))){
			errors.add("email cannot be null");
		}
		if (Utility.isNull(req.getParameter("p"))){
			errors.add("password cannot be null");
		}
		
		return errors;
		
	}
}
