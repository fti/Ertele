package net.ertele.objects;

import javax.servlet.http.HttpServletRequest;



public class UserObject {

	private String n;
	private String p;
	private String e;
	private int uId;
	
	public UserObject(String n, String p, String e) {
		this.n = n;
		this.p = Utility.hash(p);
		this.e = e;
	}
	
	public UserObject(String e, String p) {
		this(e, p, true);
	}
	
	public UserObject(String e, String p, boolean hashed){
		this.e = e;
		this.p = p;
	}
	
	public UserObject(HttpServletRequest req) {
		if (!Utility.isNull(req.getParameter("e"))) {
			setE(req.getParameter("e"));
		}
		if (!Utility.isNull(req.getParameter("p"))) {
			setP(Utility.hash(req.getParameter("p")));
		}
		if (!Utility.isNull(req.getParameter("n"))) {
			setN(req.getParameter("n"));
		}
	}
	
	public UserObject(HttpServletRequest req, boolean hashed) {
		if (!Utility.isNull(req.getParameter("e"))) {
			setE(req.getParameter("e"));
		}
		if (!Utility.isNull(req.getParameter("p"))) {
			setP(req.getParameter("p"));
		}
		if (!Utility.isNull(req.getParameter("n"))) {
			setN(req.getParameter("n"));
		}
	}
	
	public UserObject() {
		
	}
	public String getP() {
		return p;
	}
	public String getE() {
		return e;
	}
	public void setP(String p) {
		this.p = p;
	}
	public void setE(String e) {
		this.e = e;
	}

	public int getId() {
		return uId;
	}

	public void setId(int id) {
		this.uId = id;
	}

	public String getN() {
		return n;
	}
	
	public void setN(String n) {
		this.n = n;
	}

}
