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
	public static boolean rsCheck;


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
				break;
			}else {
				System.out.println("一致しないよ");
				rsCheck = false;
			}
		}
		return rsCheck;
	}


	/**
	 * 口座残高を再計算して、計算結果をリストで返す機能
	 * @param obList
	 * @return　口座残高再計算結果リスト
	 */
	public static ArrayList<Repayment_balance> balanceReCal(ObservableList<Repayment_balance> obList) {

		System.out.println(obList);

		ArrayList<Repayment_balance> calList = new ArrayList<>(obList);

		Repayment_balance a = new Repayment_balance(obList.get(0).loan_date, obList.get(0).repaid_amount, obList.get(0).deposit_amount, obList.get(0).deposit_amount - obList.get(0).repaid_amount);

		calList.set(0,a);

		for(int i = 1; i < obList.size(); i++) {

			Repayment_balance b = new Repayment_balance(calList.get(i).loan_date, calList.get(i).repaid_amount, calList.get(i).deposit_amount, calList.get(i-1).balance + calList.get(i).deposit_amount - calList.get(i).repaid_amount);
			calList.set(i, b);
		}

		return calList;

	}

}


