package net.ertele.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.ertele.objects.Controller;
import net.ertele.objects.ReadObject;
import net.ertele.objects.UserObject;

public class ReadListDAO extends GeneralDAO {

	public synchronized int insertReading(Connection conn, UserObject uo, ReadObject ro) throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		
		int rowcount = 0;
		try {
			
			sql = "INSERT INTO readinglist (title,fullUrl,rootUrl,isarchive,uid) values( ?,?,?,?,?) ;";
			
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, ro.getTitle());
			stmt.setString(2, ro.getFullUrl());
			stmt.setString(3, ro.getRootUrl());
			stmt.setInt(4, ro.getArchive());
			stmt.setInt(5, uo.getId());

			rowcount = stmt.executeUpdate();
			if (rowcount != 1) {
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
		}

		return rowcount;
	}
	
	 
	 public List<ReadObject> loadAll(Connection conn, UserObject uo, int startlimit) throws SQLException {

         String sql = "SELECT r.id,r.fullUrl,r.title,r.isarchive,r.rootUrl,r.idate FROM readinglist as r where r.uid="+uo.getId()+" order by r.idate desc limit "+startlimit+",10 ;";
         List<ReadObject> searchResults = listQuery(conn, conn.prepareStatement(sql));

         return searchResults;
	 }
	 protected List<ReadObject> listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

         ArrayList<ReadObject> searchResults = new ArrayList<ReadObject>();
         ResultSet result = null;

         try {
             result = stmt.executeQuery();

             while (result.next()) {
                  ReadObject temp = new ReadObject();

                  temp.setId(result.getInt("id")); 
                  temp.setFullUrl(result.getString("fullUrl")); 
                  temp.setTitle(Controller.decodeURIComponent(result.getString("title")));
                  temp.setArchive(result.getInt("isarchive"));
                  temp.setRootUrl(result.getString("rootUrl"));
                  temp.setDate(new SimpleDateFormat("dd-MM-yy kk:mm").format(result.getDate("idate"))); 

                  searchResults.add(temp);
             }

         } finally {
             if (result != null)
                 result.close();
             if (stmt != null)
                 stmt.close();
         }

         return searchResults;
   }

	
}