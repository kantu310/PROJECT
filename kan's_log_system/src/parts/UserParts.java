/**
 *
 */
package parts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import data.ConstantData;

/**
 * @author sato
 *【ユーザ情報取得パーツ】
 *ユーザテーブルからユーザー情報を取得する
 */
public class UserParts {

	//ユーザーテーブルからユーザ情報を取得し、マップに格納するメソッド
	public HashMap<String, String> getUserInformation() throws SQLException {

		HashMap<String, String> rs = new HashMap<String, String>();

		String sql = "SELECT USER_ID,PASSWORD FROM user;";

		ResultSet num = SqlConnectionParts.sqlConnectionQuery(sql);

		while (num.next()) {
			rs.put(num.getString(ConstantData.USER_ID), num.getString(ConstantData.PASSWORD));
		}
		return rs;

	}

}