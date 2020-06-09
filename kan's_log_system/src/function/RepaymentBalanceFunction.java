package function;

import javafx.collections.ObservableList;
import table.Repayment_balance;

/**
 * @author sato
 *
 */
public class RepaymentBalanceFunction {

	public static String  rs;
	public static boolean rsCheck = false;


	/**
	 * 入力した日付がすでに返済管理テーブルに存在するかチェックする機能
	 * @param rs1 返済管理テーブル情報の取得リスト
	 * @param year 年
	 * @param month 月
	 * @return チェック結果
	 */
	public static boolean dateDuplicationCheck(ObservableList<Repayment_balance> rs1,String year,String month){

		rs = year + "-" + month;

		for(Repayment_balance s:rs1) {

			if(s.loan_date.equalsIgnoreCase(rs)) {
				System.out.println(s.loan_date);
				rsCheck = true;
			}else {
				System.out.println("一致しないよ");
			}
		}

		return rsCheck;
	}

}


