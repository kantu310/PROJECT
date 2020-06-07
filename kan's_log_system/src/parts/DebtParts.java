package parts;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.ConstantData;

/**
 * @author sato
 *【借金テーブル情報取得パーツ】
 *借金テーブルから情報を取得するパーツ
 */
public class DebtParts {

	public static Long rs;

	//ログインしているユーザＩＤをもとに、借金額（初期金額）を取得するメソッド
	public static long getDebt(){

		String sql = "SELECT * FROM kan_system.debt where  USER_ID = \""+ ConstantData.getLoginUserID()+"\"";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {
				rs = num.getLong(ConstantData.DEBT_BALANCE);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return rs;
	}


}
