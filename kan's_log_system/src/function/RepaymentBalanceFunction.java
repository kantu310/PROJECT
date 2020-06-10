package function;

import java.util.ArrayList;

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


	public static void balanceReCal(ObservableList<Repayment_balance> obList) {

		ArrayList<Repayment_balance> calList = new ArrayList<>();


		Repayment_balance a = new Repayment_balance(obList.get(0).loan_date, obList.get(0).repaid_amount, obList.get(0).deposit_amount, obList.get(0).deposit_amount - obList.get(0).repaid_amount);

		obList.set(0,a);

		for(int i = 1; i < obList.size(); i++) {

			Repayment_balance b = new Repayment_balance(obList.get(i).loan_date, obList.get(i).repaid_amount, obList.get(i).deposit_amount, obList.get(i-1).balance + obList.get(i).deposit_amount - obList.get(i).repaid_amount);
			obList.set(i, b);
		}

	}

}


