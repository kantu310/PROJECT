/**
 *
 */
package parts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import data.ConstantData;

/**
 * @author sato
 *【ユーザ情報取得パーツ】
 *ユーザテーブルからユーザー情報を取得する
 */
public class GetUserParts {

	//ユーザーテーブルからユーザ情報を取得し、マップに格納するメソッド
	public HashMap<String, String> getUserInformation() throws SQLException {

		HashMap<String, String> rs = new HashMap<String, String>();

		Connection conn = null;
		conn = DriverManager.getConnection(ConstantData.MYSQL_URL, ConstantData.MYSQL_USER,
				ConstantData.MYSQL_PASSWORD);
		Statement stmt = conn.createStatement();
		String sql = "SELECT USER_ID,PASSWORD FROM user;";
		ResultSet num = stmt.executeQuery(sql);
		while (num.next()) {
			rs.put(num.getString(ConstantData.USER_ID), num.getString(ConstantData.PASSWORD));
		}

		return rs;

	}

}