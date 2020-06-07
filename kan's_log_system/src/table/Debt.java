package table;

public class Debt {

	//public String debt_id
	//public String user_id
	public long debt;
	public long debt_balance;


	public Debt(long debt, long debt_balance) {
		super();
		this.debt = debt;
		this.debt_balance = debt_balance;
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
}
