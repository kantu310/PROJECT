package parts;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import table.Repayment_balance;

/**
 * @author sato
 *【返済テーブル情報取得パーツ】
 *返済テーブルから情報を取得するパーツ
 */
public class RepaymentBalanceParts {

	public static ObservableList<Repayment_balance> obList = FXCollections.observableArrayList();
	public static long lstBalance;

	//ログインしているユーザＩＤをもとに、日付、返済金、入金、残高を取得するメソッド
	public static ObservableList<Repayment_balance> getRepaymentBalance() {

		String sql = "SELECT * FROM kan_system.repayment_balance where  USER_ID = \""+ ConstantData.getLoginUserID()+"\" ORDER BY  LOAN_DATE ASC";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {

				obList.add(new Repayment_balance(num.getString(ConstantData.LOAN_DATE),num.getLong(ConstantData.REPAID_AMOUNT),num.getLong(ConstantData.DEPOSIT_AMOUNT),num.getLong(ConstantData.BALANCE)));

			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return obList;
	}

	//最新の口座残高を取得するメソッド
	public static long getlastBalance() {

		String sql = "SELECT BALANCE FROM kan_system.repayment_balance where LOAN_DATE = (SELECT MAX(LOAN_DATE) FROM kan_system.repayment_balance) AND  USER_ID = \""+ ConstantData.getLoginUserID()+"\"";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {
				lstBalance = num.getLong(ConstantData.BALANCE);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return lstBalance;
	}


	public static void setRepayment(String year, String month, String money) {

		String date = year + "-" + month;

		//String sql = "INSERT INTO kan_system.repayment_balance (USER_ID, LOAN_DATE, REPAID_AMOUNT) VALUES (\""+ConstantData.getLoginUserID()+"\",\""+ date +"\","+ money +")";

		String sql = "INSERT INTO kan_system.repayment_balance (USER_ID, LOAN_DATE, REPAID_AMOUNT) VALUES (\""+ConstantData.getLoginUserID()+"\",\""+ date +"\",\""+ money+"\")";
		int num = 0;

		try {
			num = SqlConnectionParts.sqlCreate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		}

		System.out.println(num);
	}


}
