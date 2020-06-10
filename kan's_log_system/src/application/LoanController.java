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
import table.Repayment_balance;

public class LoanController {

	ObservableList<String> loanYearList = FXCollections.observableArrayList("2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032");
	ObservableList<String> loanMonthList = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
	ObservableList<Repayment_balance> rs = RepaymentBalanceParts.getRepaymentBalance();
	public long  rs2 = DebtParts.getDebt();

    @FXML
    private Text debt_balance;

    @FXML
    private Text lst_balance;

	@FXML
	private ChoiceBox<String> loan_year;

	@FXML
	private ChoiceBox<String> loan_month;

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
	private TableColumn<Repayment_balance, Long> deposit_amount;

	@FXML
	private TableColumn<Repayment_balance, Long> balance;

	@FXML
	private void initialize() {

		//日付プルダウン
		loan_year.setItems(loanYearList);
		loan_month.setItems(loanMonthList);

		//返済残高
		debt_balance.setText(String.valueOf(rs2));

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
    	money.setText(a.replaceFirst("^0+", ""));

    	//アラートダイアログ
    	Alert dialog = new Alert(AlertType.NONE , "登録しますか？" , ButtonType.YES , ButtonType.NO);
    	//返済or入金の分岐
    	if(radiopay.isSelected()) {
    		chk = RepaymentBalanceFunction.dateDuplicationCheck(rs, loan_year.getValue(), loan_month.getValue());
    		if(chk == true) {
    			Alert dialog2 = new Alert(AlertType.NONE , "すでにデータがあります更新しますか？" , ButtonType.YES , ButtonType.NO);
    			Optional<ButtonType> diaRs2 = dialog2.showAndWait();
    			if(diaRs2.get() == ButtonType.YES) {
    			//「yes」
    			sqlType = "UPDATE";
    			RepaymentBalanceParts.setRepayment(loan_year.getValue(), loan_month.getValue(), money.getText(), sqlType);
    			calList = RepaymentBalanceFunction.balanceReCal(RepaymentBalanceParts.getRepaymentBalance());
    			RepaymentBalanceParts.upNewBalance(calList);
    			rs.clear();
    			rs = RepaymentBalanceParts.getRepaymentBalance();
    			}else if(diaRs2.get() == ButtonType.NO) {
    				 chk = false;
    				dialog.close();
    			}
    		}else if(chk == false){
    			//ポップアップダイアログ表示
    			Optional<ButtonType> diaRs = dialog.showAndWait();
    			if(diaRs.get() == ButtonType.YES) {
    				sqlType = "INSERT";
    				RepaymentBalanceParts.setRepayment(loan_year.getValue(), loan_month.getValue(), money.getText(),sqlType);
    				calList = RepaymentBalanceFunction.balanceReCal(RepaymentBalanceParts.getRepaymentBalance());
        			RepaymentBalanceParts.upNewBalance(calList);
        			rs.clear();
        			rs = RepaymentBalanceParts.getRepaymentBalance();
    			}else {
    				System.out.println("処理終了");
    				dialog.close();
    			}
    		}
    	}else {
    		System.out.println("入金");
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

}