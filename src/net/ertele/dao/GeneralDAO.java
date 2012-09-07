package net.ertele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.ertele.objects.UserObject;

public class GeneralDAO {

	public boolean getUser(Connection con,UserObject uo) throws SQLException {

		String sql = "SELECT id, name  FROM user WHERE (email = ? AND pass = ?) ";
		PreparedStatement stmt = null;

		boolean isit = false;
		ResultSet result = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, uo.getE());
			stmt.setString(2, uo.getP());

			result = stmt.executeQuery();
			if (result.first()) {
                uo.setId(result.getInt("id")); 
                uo.setN(result.getString("name"));
                isit = true;
           } else {
				// System.out.println("UserBean Object Not Found!");
				isit = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
		return isit;

	}
}
