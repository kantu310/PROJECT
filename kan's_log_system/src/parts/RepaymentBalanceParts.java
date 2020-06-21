package parts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


	/**
	 * ログインしているユーザＩＤをもとに、日付、返済金、入金、残高を取得するメソッド
	 * @return 取得結果リスト
	 */
	public static ObservableList<Repayment_balance> getRepaymentBalance() {

		obList.clear();

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

	/**
	 * 返済金登録メソッド
	 * @param year 年
	 * @param month 月
	 * @param money 金額
	 */
	public static void setRepayment(String year, String month, String money, String sqlType) {

		String date = year + "-" + month;
		String sql =null;

		switch (sqlType) {
		case "INSERT":
			sql = "INSERT INTO kan_system.repayment_balance (USER_ID, LOAN_DATE, REPAID_AMOUNT) VALUES (\""+ConstantData.getLoginUserID()+"\",\""+ date +"\",\""+ money+"\")";
			break;
		case "UPDATE":
			sql = "update kan_system.repayment_balance set REPAID_AMOUNT =\"" + money + "\"where LOAN_DATE = \"" + date + "\" AND USER_ID = \"" + ConstantData.getLoginUserID() +"\"";

		default:
			break;
		}

		int num = 0;

		try {
			num = SqlConnectionParts.sqlCreate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		}

		System.out.println(num);
	}

	/**
	 * 入金金登録メソッド
	 * @param year 年
	 * @param month 月
	 * @param money 金額
	 */
	public static void setDepotis(String year, String month, String money, String sqlType) {

		String date = year + "-" + month;
		String sql =null;

		switch (sqlType) {
		case "INSERT":
			sql = "INSERT INTO kan_system.repayment_balance (USER_ID, LOAN_DATE, DEPOSIT_AMOUNT) VALUES (\""+ConstantData.getLoginUserID()+"\",\""+ date +"\",\""+ money+"\")";
			break;
		case "UPDATE":
			sql = "update kan_system.repayment_balance set DEPOSIT_AMOUNT =\"" + money + "\"where LOAN_DATE = \"" + date + "\" AND USER_ID = \"" + ConstantData.getLoginUserID() +"\"";

		default:
			break;
		}

		int num = 0;

		try {
			num = SqlConnectionParts.sqlCreate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		}

	}


	/**
	 * テーブルビューから選択した行を削除するメソッド
	 * @param date  選択した日付
	 */
	public static void deleteRepaymentBalanceRow(String date) {

		String sql  = "DELETE FROM kan_system.repayment_balance where " +ConstantData.getUserId()+" = \""+ConstantData.getLoginUserID()+"\" AND " +ConstantData.getLoanDate()+" = \""+date+"\"";
		System.out.println(sql);
		int num = 0;

		try {
			num = SqlConnectionParts.sqlCreate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		System.out.println(num);

	}

	/**
	 * 口座残高を再更新するメソッド
	 * @param balist 口座残高が再計算された結果が格納されたリスト
	 */
	public static void upNewBalance(ArrayList<Repayment_balance>balist) {

		long a = balist.get(0).balance;
		String d = balist.get(0).loan_date;


		String sql = "update kan_system.repayment_balance set BALANCE =\"" + a + "\" where LOAN_DATE = \"" + d + "\" AND USER_ID = \"" + ConstantData.getLoginUserID() +"\"";
		try {
			SqlConnectionParts.sqlCreate(sql);
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		for(int i = 1; i < balist.size();i++) {

		 sql = "update kan_system.repayment_balance set BALANCE =\"" + balist.get(i).balance + "\" where LOAN_DATE = \"" + balist.get(i).loan_date + "\" AND USER_ID = \"" + ConstantData.getLoginUserID() +"\"";
		 System.out.println(sql);

		try {
			SqlConnectionParts.sqlCreate(sql);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}


}}
