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
import net.ertele.dao.ReadListDAO;
import net.ertele.objects.ReadListController;
import net.ertele.objects.ReadObject;
import net.ertele.objects.UserObject;
import net.ertele.objects.Utility;

import org.json.JSONException;
import org.json.JSONObject;


@WebServlet("/ReadListService")
public class ReadListService extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = "";
		if (Utility.isNull(req.getParameter("type"))) {
			return;
		}
		type = req.getParameter("type");
		if ("addList".equals(type)) {
			addList(req, resp);
		}else if ("getList".equals(type)) {
			getReadingList(req,resp);
		}
		
	}
	
	private void getReadingList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<String> errors = ReadListController.loadReadListController(req);
		List<ReadObject> rlist = null;
		int startlimit = 0;
		if (!Utility.isNull(req.getParameter("page"))) {
			startlimit = (Integer.parseInt(req.getParameter("page"))-1)*10;
		}
		if (errors.isEmpty()) {
			UserObject uo = new UserObject(req.getParameter("e"), req.getParameter("p"), true);
			ReadListDAO rdo = new ReadListDAO();
			Connection con = DaoCon.getConnection();
			try {
				boolean isSuccess = rdo.getUser(con,uo);
				if (!isSuccess) {
					errors.add("Hatali kullanici");
				}else{
					rlist = rdo.loadAll(con, uo, startlimit);
				}
			} catch (SQLException e) {
				errors.add("Beklenmedik hata");
				e.printStackTrace();
			}
		}
		JSONObject jo = createJsonObject(errors, (errors.isEmpty()?true:false));
		try {
			jo.put("rlist", rlist);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		resp.setContentType("application/json");
		resp.getWriter().append(jo.toString()).close();
	}

	private void addList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<String> errors = ReadListController.readlistContr(req);
		
		
		if (errors.isEmpty()) {
			String title = req.getParameter("title");
			String fullUrl = req.getParameter("fullUrl");
			
			UserObject uo = new UserObject(req.getParameter("e"), req.getParameter("p"), true);
			ReadObject ro = new ReadObject(fullUrl, title);
			
			
			Connection conn = DaoCon.getConnection();
			ReadListDAO rld = new ReadListDAO();
			
			try {
				boolean isSuccess = rld.getUser(conn,uo);
				if (isSuccess) {
					rld.insertReading(conn, uo, ro);
				}else{
					errors.add("User is invalid");
				}
			} catch (SQLException e) {
				errors.add("An error has occured");
				//e.printStackTrace();
			}
		}
		
		resp.setContentType("application/json");
		resp.getWriter().append(createJsonObject(errors, (errors.isEmpty()?true:false)).toString()).close();

	}
	
	public static JSONObject createJsonObject(List<String> errors, boolean success){
		JSONObject jo = new JSONObject();
		
		try {
			jo.put("errors", errors);
			jo.put("success", success);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
}
