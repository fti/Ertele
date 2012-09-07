package net.ertele;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ertele.dao.DaoCon;
import net.ertele.dao.ReadListDAO;
import net.ertele.objects.ReadObject;
import net.ertele.objects.UserObject;
import net.ertele.objects.Utility;

@WebServlet("/readlist")
public class ReadListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			ReadListDAO rld = new ReadListDAO();
			Connection con = DaoCon.getConnection();

			if (!Utility.isNull(req.getParameter("from")) && "chrome".equals(req.getParameter("from"))) {
				try {
					UserObject uo = new UserObject(req.getParameter("u"), req.getParameter("p"), true);
					rld.getUser(con, uo);
					List<ReadObject> rl = null;
					rl = rld.loadAll(con, uo,100);
					req.setAttribute("readlist", rl);
					req.getRequestDispatcher("/readlistchrome.jsp").forward(req, resp);
					return;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			HttpSession session = req.getSession();
			if (session.getAttribute("user") == null) {
				resp.sendRedirect(req.getContextPath()+"/");
				return;
			}
			UserObject uo = (UserObject) session.getAttribute("user");
			if (uo.getN() == null) {
				return;
			}
			
			int limit = 0;
			if (!Utility.isNull(req.getParameter("page"))) {
				limit = (Integer.parseInt(req.getParameter("page"))-1)*10;
			}
			
			List<ReadObject> rl = null;
			try {
				rl = rld.loadAll(con, uo,limit);
				req.setAttribute("readlist", rl);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/readlist.jsp").forward(req, resp);
			
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
