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

	//ログインしているユーザＩＤをもとに、日付、返済金、入金、残高を取得するメソッド
	public static ObservableList<Repayment_balance> getRepaymentBalance() {

		String sql = "SELECT * FROM kan_system.repayment_balance where  USER_ID = \""+ ConstantData.getLoginUserID()+"\"";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {

				obList.add(new Repayment_balance(num.getDate(ConstantData.LOAN_DATE),num.getLong(ConstantData.REPAID_AMOUNT),num.getLong(ConstantData.DEPOSIT_AMOUNT),num.getLong(ConstantData.BALANCE)));

			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		System.out.println(obList);
		return obList;
	}

}
