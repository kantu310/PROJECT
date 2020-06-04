package parts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.ConstantData;

/**
 * @author sato
 *【返済テーブル情報取得パーツ】
 *返済テーブルから情報を取得するパーツ
 */
public class RepaymentBalanceParts {

	public static ArrayList<Object> rs = new ArrayList<Object>();

	//ログインしているユーザＩＤをもとに、日付、返済金、入金、残高を取得するメソッド
	public static ArrayList<Object>getRepaymentBalance() {

		String sql = "SELECT LOAN_DATE,REPAID_AMOUNT,DEPOSIT_AMOUNT,DEPOSIT_AMOUNT,BALANCE FROM kan_system.repayment_balance where  USER_ID = \""+ ConstantData.getLoginUserID()+"\"";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {
				rs.add(num.getDate(ConstantData.LOAN_DATE));
				rs.add(num.getLong(ConstantData.REPAID_AMOUNT));
				rs.add(num.getLong(ConstantData.DEPOSIT_AMOUNT));
				rs.add(num.getLong(ConstantData.BALANCE));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return rs;
	}

}
