package parts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.ConstantData;

/**
 * @author sato
 *【Sql接続パーツ】
 *sqlの接続を行い、結果を返す。
 */
public class SqlConnectionParts {

	//データ取得（SELECT）
	public static ResultSet sqlConnectionQuery(String sql) throws SQLException{

		Connection conn =null;

		conn = DriverManager.getConnection(ConstantData.MYSQL_URL, ConstantData.MYSQL_USER,
				ConstantData.MYSQL_PASSWORD);

		Statement stmt = conn.createStatement();
		ResultSet num = stmt.executeQuery(sql);

		return num;

	}

	//データ追加（INSERT)
	public static int sqlCreate(String sql) throws SQLException {
		Connection conn =null;

		conn = DriverManager.getConnection(ConstantData.MYSQL_URL, ConstantData.MYSQL_USER,
				ConstantData.MYSQL_PASSWORD);

		Statement stmt = conn.createStatement();
		int num = stmt.executeUpdate(sql);

		conn.commit();

		return num;

	}

}
