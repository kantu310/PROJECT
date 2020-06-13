package table;

public class Debt {

	//public String debt_id
	//public String user_id
	public long debt;
	public long debt_balance;
	public long fixed_money;


	public Debt(long debt, long debt_balance, long fixed_money) {
		super();
		this.debt = debt;
		this.debt_balance = debt_balance;
		this.fixed_money = fixed_money;
	}


	public long getDebt() {
		return debt;
	}


	public void setDebt(long debt) {
		this.debt = debt;
	}


	public long getDebt_balance() {
		return debt_balance;
	}


	public void setDebt_balance(long debt_balance) {
		this.debt_balance = debt_balance;
	}


	public long getFixed_money() {
		return fixed_money;
	}


	public void setFixed_money(long fixed_money) {
		this.fixed_money = fixed_money;
	}



}
