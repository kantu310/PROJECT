package table;

public class Repayment_balance {

	//public int REPAYMENT_BALANCE_ID;
	//public String USER_ID;
	public String loan_date;
	public long repaid_amount;
	public long deposit_amount;
	public long balance;


	public Repayment_balance(String loan_date, long repaid_amount, long deposit_amount, long balance) {
		super();
		this.loan_date = loan_date;
		this.repaid_amount = repaid_amount;
		this.deposit_amount = deposit_amount;
		this.balance = balance;
	}


	public String getLoan_date() {
		return loan_date;
	}


	public void setLoan_date(String loan_date) {
		this.loan_date = loan_date;
	}


	public long getRepaid_amount() {
		return repaid_amount;
	}


	public void setRepaid_amount(long repaid_amount) {
		this.repaid_amount = repaid_amount;
	}


	public long getDeposit_amount() {
		return deposit_amount;
	}


	public void setDeposit_amount(long deposit_amount) {
		this.deposit_amount = deposit_amount;
	}


	public long getBalance() {
		return balance;
	}


	public void setBalance(long balance) {
		this.balance = balance;
	}


}
