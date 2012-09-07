package net.ertele.objects;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class ReadObject {

	static final String DEFAULT_TITLE = "NO TITLE";
	private int id;
	private String fullUrl;
	private String title;
	private int archive;
	private String rootUrl;
	private String date;
	
	public ReadObject() {
		
	}
	public ReadObject(String url, String title) throws UnsupportedEncodingException {
		if(!isNull(title) ){
				setTitle(title);
		}else {
			setTitle(DEFAULT_TITLE);
		}
			
		if(!isNull(url) ){
			setFullUrl(URLDecoder.decode(url, "UTF-8"));
			setRootUrl(URI.create(url).getHost());
		}
		setArchive(0);
	}
	
	public ReadObject(HttpServletRequest req) {
		
		if(!isNull(req.getParameter("isArchive")) ){
			setArchive(Integer.parseInt(req.getParameter("isArchive")));
		}else {
			setArchive(0);
		}
		
		if(!isNull(req.getParameter("title")) ){
			setTitle(req.getParameter("title"));
		}else {
			setTitle(DEFAULT_TITLE);
		}
			
		if(!isNull(req.getParameter("url")) ){
			setFullUrl(req.getParameter("url"));
			
			if(isNull(req.getParameter("rootUrl")) ){
				setRootUrl(URI.create(req.getParameter("url")).getHost());
			}
		}
		
		if(!isNull(req.getParameter("date")) ){
			setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(req.getParameter("date")));
		}else {
			setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
	}
	
	private boolean isNull(String n) {
		return (n == null || "".equals(n))?true:false;
	}
	
	
	public String getTitle() {
		return title;
	}
	public int getArchive() {
		return archive;
	}
	public String getRootUrl() {
		return rootUrl;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setArchive(int archive) {
		this.archive = archive;
	}
	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	
}
