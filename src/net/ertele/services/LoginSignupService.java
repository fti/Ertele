package net.ertele.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ertele.dao.DaoCon;
import net.ertele.dao.LoginSignupDAO;
import net.ertele.objects.Controller;
import net.ertele.objects.UserObject;
import net.ertele.objects.Utility;

import org.json.JSONException;
import org.json.JSONObject;


@WebServlet("/LoginSignupService")
public class LoginSignupService extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserObject uo;
		String sType = "";
		List<String> errors = null;
		
		Connection con = DaoCon.getConnection();
		LoginSignupDAO lsd = new LoginSignupDAO();
		
		if(!Utility.isNull(req.getParameter("type"))){
			sType = req.getParameter("type");
		}
		
		uo=new UserObject(req, true);

		boolean success = false;
		if ("login".equals(sType)) {
			errors = Controller.loginContr(uo);
			if (errors.isEmpty()) {
				try {
					if(lsd.getUser(con, uo)){
						success = true;
					}else {
						errors.add("Invalid username/password");
						success = false;
					}
				} catch (SQLException e) {
					errors.add("An error has occured, try again or contact us");
					success = false;
					//e.printStackTrace();
				}
			}
			
		}else if ("signup".equals(sType)) {
			errors = Controller.regUserContr(req);
			if (errors.isEmpty()) {
				try {
					if (lsd.insertUser(con, uo)) {
						success = true;
					}else{
						success = false;
						errors.add("Invalid username/password");
					}
						
				} catch (SQLException e) {
					success = false;
					e.printStackTrace();
					errors.add("An error has occured, try again or contact us");
				}
			}
		}
		JSONObject jo = new JSONObject();
		JSONObject juo = new JSONObject();
		try {
			juo.put("id", uo.getId());
			juo.put("n", uo.getN());
			juo.put("p", uo.getP());
			juo.put("e", uo.getE());
			
			jo.put("user", juo);
			jo.put("errors", errors);
			jo.put("success", success);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		resp.setContentType("application/json");
		resp.getWriter().print(jo.toString());
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
