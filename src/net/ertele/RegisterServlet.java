package net.ertele;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ertele.dao.DaoCon;
import net.ertele.dao.LoginSignupDAO;
import net.ertele.objects.Controller;
import net.ertele.objects.UserObject;
import net.ertele.objects.Utility;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/register.jsp");
		
		if (Utility.isNull(req.getParameter("t")) && !"register".equals(req.getParameter("t"))) {
			dispatcher.forward(req, resp);
			return;
		}
		
		List<String> errors = Controller.regUserContr(req);
		
		if(!errors.isEmpty()){
			req.setAttribute("errors", errors);
			dispatcher.forward(req, resp);
			return;
		}
		
		Connection conn = DaoCon.getConnection();
		LoginSignupDAO dao = new LoginSignupDAO();
		UserObject user = new UserObject( req);
		
		try {
			boolean isit = dao.insertUser(conn, user);
			if(!isit){
				errors.add(Controller.ALERT_HASMEMBER_EMAIL);
				req.setAttribute("errors", errors);
				dispatcher.forward(req, resp);
				return;
			}
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doPost(req, resp);
	}

}
