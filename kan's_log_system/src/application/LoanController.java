package application;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

import function.RepaymentBalanceFunction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import parts.DebtParts;
import parts.RepaymentBalanceParts;
import table.Debt;
import table.Repayment_balance;

public class LoanController {

	ObservableList<String> loanYearList = FXCollections.observableArrayList("2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032");
	ObservableList<String> loanMonthList = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
	ObservableList<Repayment_balance> rs = RepaymentBalanceParts.getRepaymentBalance();
	ObservableList<Debt>  rs2 = DebtParts.getDebt();

	@FXML
	private Text debt_balance;

	@FXML
	private Text lst_balance;

	@FXML
	private ChoiceBox<String> loan_year;

	@FXML
	private ChoiceBox<String> loan_month;

	@FXML
	private Button btn_repayment;

	@FXML
	private Button delete;

	@FXML
	private TableView<Repayment_balance> repayment_balance;

	@FXML
	private TableColumn<Repayment_balance, String> loan_date;

	@FXML
	private TableColumn<Repayment_balance, Long> repaid_amount;

	@FXML
	private TextField money;

	@FXML
	private RadioButton radiopay;

	@FXML
	private ToggleGroup group;

	@FXML
	private RadioButton radiodepo;

    @FXML
    private Button fixed_money;

	@FXML
	private TableColumn<Repayment_balance, Long> deposit_amount;

	@FXML
	private TableColumn<Repayment_balance, Long> balance;

	@FXML
	private void initialize() {

		//日付プルダウン
		loan_year.setItems(loanYearList);
		loan_month.setItems(loanMonthList);
		//返済残高
		debt_balance.setText(String.valueOf(rs2.get(0).debt_balance));
		//口座残高
		lst_balance.setText(String.valueOf(rs.get(rs.size()-1).balance));
		//テーブルビュー
		loan_date.setCellValueFactory(new PropertyValueFactory<>("loan_date"));
		repaid_amount.setCellValueFactory(new PropertyValueFactory<>("repaid_amount"));
		deposit_amount.setCellValueFactory(new PropertyValueFactory<>("deposit_amount"));
		balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
		repayment_balance.setItems(rs);
	}

	@FXML
	void onBtn_reoayment(ActionEvent event) {

		boolean chk = false;
		String sqlType = null;

		ArrayList<Repayment_balance> calList = new ArrayList<>();

		//前ゼロをトリム
		String a = money.getText();
		money.setText(a.replaceFirst("^0.+", "0"));

		//アラートダイアログ
		Alert dialog = new Alert(AlertType.NONE , "登録しますか？" , ButtonType.YES , ButtonType.NO);
		dialog.setTitle("確認");
		Alert dialog2 = new Alert(AlertType.NONE , "すでにデータがあります更新しますか？" , ButtonType.YES , ButtonType.NO);
		dialog2.setTitle("確認");
		Alert dialog3 = new Alert(AlertType.NONE, "日付または金額が入力されていません", ButtonType.OK);
		dialog3.setTitle("エラー");
		Alert dialog4 = new Alert(AlertType.NONE, "登録が完了しました。", ButtonType.OK);
		dialog4.setTitle("完了");

		if(loan_year.getValue() == null || loan_month.getValue() == null || money.getText().isEmpty())  {
			dialog3.showAndWait();
		}else {
			//返済or入金の分岐
			if(radiopay.isSelected()) {
				chk = RepaymentBalanceFunction.dateDuplicationCheck(rs, loan_year.getValue(), loan_month.getValue());
				if(chk == true) {
					Optional<ButtonType> diaRs2 = dialog2.showAndWait();
					if(diaRs2.get() == ButtonType.YES) {
						sqlType = "UPDATE";
						RepaymentBalanceParts.setRepayment(loan_year.getValue(), loan_month.getValue(), money.getText(), sqlType);
						calList = RepaymentBalanceFunction.balanceReCal(RepaymentBalanceParts.getRepaymentBalance());
						RepaymentBalanceParts.upNewBalance(calList);
						rs.clear();
						rs = RepaymentBalanceParts.getRepaymentBalance();
						DebtParts.upDebt();
						rs2.clear();
						rs2 = DebtParts.getDebt();
						lst_balance.setText(String.valueOf(rs.get(rs.size()-1).balance));//バインド分かんない。
						debt_balance.setText(String.valueOf(rs2.get(0).debt_balance));//バインド分かんない。
						dialog4.showAndWait();
					}else if(diaRs2.get() == ButtonType.NO) {
						chk = false;
						dialog.close();
					}
				}else if(chk == false){
					Optional<ButtonType> diaRs = dialog.showAndWait();
					if(diaRs.get() == ButtonType.YES) {
						sqlType = "INSERT";
						RepaymentBalanceParts.setRepayment(loan_year.getValue(), loan_month.getValue(), money.getText(),sqlType);
						calList = RepaymentBalanceFunction.balanceReCal(RepaymentBalanceParts.getRepaymentBalance());
						RepaymentBalanceParts.upNewBalance(calList);
						rs.clear();
						rs = RepaymentBalanceParts.getRepaymentBalance();
						DebtParts.upDebt();
						rs2.clear();
						rs2 = DebtParts.getDebt();
						lst_balance.setText(String.valueOf(rs.get(rs.size()-1).balance));
						debt_balance.setText(String.valueOf(rs2.get(0).debt_balance));//バインド分かんない。
						dialog4.showAndWait();
					}else {
						System.out.println("処理終了");
						dialog.close();
					}
				}
			}else if(radiodepo.isSelected()){
				chk = RepaymentBalanceFunction.dateDuplicationCheck(rs, loan_year.getValue(), loan_month.getValue());
				if(chk == true) {
					Optional<ButtonType> diaRs2 = dialog2.showAndWait();
					if(diaRs2.get() == ButtonType.YES) {
						sqlType = "UPDATE";
						RepaymentBalanceParts.setDepotis(loan_year.getValue(), loan_month.getValue(), money.getText(), sqlType);
						calList = RepaymentBalanceFunction.balanceReCal(RepaymentBalanceParts.getRepaymentBalance());
						RepaymentBalanceParts.upNewBalance(calList);
						rs.clear();
						rs = RepaymentBalanceParts.getRepaymentBalance();
						lst_balance.setText(String.valueOf(rs.get(rs.size()-1).balance));
						dialog4.showAndWait();
					}else if(diaRs2.get() == ButtonType.NO) {
						chk = false;
						dialog.close();
					}
				}else if(chk == false){
					Optional<ButtonType> diaRs = dialog.showAndWait();
					if(diaRs.get() == ButtonType.YES) {
						sqlType = "INSERT";
						RepaymentBalanceParts.setDepotis(loan_year.getValue(), loan_month.getValue(), money.getText(),sqlType);
						calList = RepaymentBalanceFunction.balanceReCal(RepaymentBalanceParts.getRepaymentBalance());
						RepaymentBalanceParts.upNewBalance(calList);
						rs.clear();
						rs = RepaymentBalanceParts.getRepaymentBalance();
						lst_balance.setText(String.valueOf(rs.get(rs.size()-1).balance));
						dialog4.showAndWait();
					}else {
						System.out.println("処理終了");
						dialog.close();
					}
				}
			}
		}
	}

	@FXML
	void onKyPrsMoney(KeyEvent event) {
		//数字以外の入力を制御
		Pattern notNumberPattern = Pattern.compile("[^0-9]+");
		TextFormatter<String> lowerFormatter = new TextFormatter<>(change -> {
			String newStr = notNumberPattern.matcher(change.getText()).replaceAll("");
			int diffcount = change.getText().length() - newStr.length();
			change.setAnchor(change.getAnchor() - diffcount);
			change.setCaretPosition(change.getCaretPosition() - diffcount);
			change.setText(newStr);
			return change;
		});
		money.setTextFormatter(lowerFormatter);
	}

	@FXML
	void onBtn_fixed_money(ActionEvent event) {
		money.setText(String.valueOf(rs2.get(0).fixed_money));
	}

	@FXML
	void onBtn_delete(ActionEvent event) {
		money.setText("");
	}

    @FXML
    void onAct_radiodepo(ActionEvent event) {

    	fixed_money.setDisable(true);

    }

    @FXML
    void onAct_radiopay(ActionEvent event){

    	fixed_money.setDisable(false);

    }
}