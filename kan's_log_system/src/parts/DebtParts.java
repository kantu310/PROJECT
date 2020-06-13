package parts;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import table.Debt;

/**
 * @author sato
 *【借金テーブル情報取得パーツ】
 *借金テーブルから情報を取得するパーツ
 */
public class DebtParts {

	public static ObservableList<Debt> obList = FXCollections.observableArrayList();
	public static Long rs;


	/**
	 * 	ログインしているユーザＩＤをもとに、借金テーブルから情報を取得するメソッド
	 * @return 借金テーブルから取得した情報のリスト
	 */
	public static ObservableList<Debt> getDebt(){

		String sql = "SELECT * FROM kan_system.debt where  USER_ID = \""+ ConstantData.getLoginUserID()+"\"";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {
				obList.add(new Debt(num.getLong(ConstantData.DEBT),num.getLong(ConstantData.DEBT_BALANCE),num.getLong(ConstantData.FIXED_MONEY)));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return obList;
	}

	/**
	 * 借金総額から返済金の合計を差し引いた情報を取得するメソッド
	 *
	 */
	public static void upDebt() {

		String sql  = "update debt set debt_balance = debt - (select sum(REPAID_AMOUNT) from repayment_balance where USER_ID = \""+ConstantData.getLoginUserID()+"\") where user_id = \"" + ConstantData.getLoginUserID() +"\"";

		int num = 0;

		try {
			num = SqlConnectionParts.sqlCreate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		System.out.println(num);
	}
}
