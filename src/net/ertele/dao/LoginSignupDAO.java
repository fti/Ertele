package net.ertele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.ertele.objects.UserObject;

import com.mysql.jdbc.Statement;

public class LoginSignupDAO extends GeneralDAO {

	public synchronized boolean insertUser(Connection conn, UserObject uo) throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;

		int rowcount = 0;
		boolean isScucces = true;
		try {
			sql = "INSERT INTO user ( email, name, pass ) values (?,?,?);";
			
//			sql = "INSERT INTO user ( email, username, pass ) "
//               + "select ?, ?, ? from user where not exists(select * from user as a where a.email=? or a.username=?) ";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, uo.getE());
			stmt.setString(2, uo.getN());
			stmt.setString(3, uo.getP());
			rowcount = stmt.executeUpdate();
			
			if (rowcount != 1) {
				isScucces = false;
				throw new SQLException("PrimaryKey Error when updating DB!");
			}
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.first()) {
				uo.setId(rs.getInt(1));
			}

		}catch (Exception e) {
			isScucces=false;
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return isScucces;
	}

}