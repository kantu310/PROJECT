package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

import data.ConstantData;
import function.RepaymentBalanceFunction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import parts.DebtParts;
import parts.RepaymentBalanceParts;
import table.Debt;
import table.Repayment_balance;

public class LoanController extends Controller{

	ObservableList<String> loanYearList = FXCollections.observableArrayList("2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032");
	ObservableList<String> loanMonthList = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
	ObservableList<Repayment_balance> rs = RepaymentBalanceParts.getRepaymentBalance();
	ArrayList<Repayment_balance> calList = new ArrayList<>();
	static  ObservableList<Debt>  rs2 = DebtParts.getDebt();
	static SimpleStringProperty aaa = new SimpleStringProperty(String.valueOf(rs2.get(0).debt_balance));


    @FXML
    private AnchorPane loanpane;

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
    private ImageView loanConf;



	@FXML
	private void initialize() {

		//日付プルダウン
		loan_year.setItems(loanYearList);
		loan_month.setItems(loanMonthList);
		//返済残高
		debt_balance.textProperty().bind(aaa);
		//debt_balance.setText(String.valueOf(rs2.get(0).debt_balance));
		//口座残高
		lst_balance.setText(String.valueOf(rs.get(rs.size()-1).balance));
		//テーブルビュー
		loan_date.setCellValueFactory(new PropertyValueFactory<>("loan_date"));
		repaid_amount.setCellValueFactory(new PropertyValueFactory<>("repaid_amount"));
		deposit_amount.setCellValueFactory(new PropertyValueFactory<>("deposit_amount"));
		balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
		repayment_balance.setItems(rs);


		ConstantData.setCurrentDebt(rs2.get(0).debt);
		ConstantData.setCurrentFixedMoney(rs2.get(0).fixed_money);

		fullRepayedJudge();
	}

	@FXML
	void onBtn_reoayment(ActionEvent event) {

		boolean chk = false;
		String sqlType = null;

		//前ゼロをトリム
		String a = money.getText();
		money.setText(a.replaceFirst("^0.+", ""));

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
						lst_balance.setText(String.valueOf(rs.get(rs.size()-1).balance));
						aaa.set(String.valueOf(rs2.get(0).debt_balance));
						fullRepayedJudge();
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
						aaa.set(String.valueOf(rs2.get(0).debt_balance));
						fullRepayedJudge();
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
						fullRepayedJudge();
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
						fullRepayedJudge();
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

		//文字数制限
		int chkLength = 8;
		int counter = money.getText().length();
		if(counter > chkLength) {
			money.setText(money.getText().substring(0, chkLength));
		}

	}

	@FXML
	void onBtn_fixed_money(ActionEvent event) {
		money.setText(String.valueOf(ConstantData.getCurrentFixedMoney()));
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

    @FXML
    void onBtnloanconf(MouseEvent event) throws IOException {

		Parent parent1 = FXMLLoader.load(getClass().getResource("LoanConf.fxml"));
		Stage stage1 = new Stage();
		Scene scene1 = new Scene(parent1);
	 	stage1.initOwner(stage);
		stage1.setScene(scene1);
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.setTitle("設定");
		stage1.showAndWait();

    	//AnchorPane pane = FXMLLoader.load(getClass().getResource("LoanConf.fxml"));
		//loanpane.getChildren().setAll(pane);
    }

    @FXML
    void onBtn_delete_row(ActionEvent event) {

    	Alert dialog = new Alert(AlertType.NONE , "選択したデータを削除しますか？" , ButtonType.YES , ButtonType.NO);
    	dialog.setTitle("確認");

    	if(repayment_balance.getSelectionModel().getSelectedIndex() != -1) {
    		Optional<ButtonType> diaRs = dialog.showAndWait();
    		if(diaRs.get() == ButtonType.YES) {
    			RepaymentBalanceParts.deleteRepaymentBalanceRow(repayment_balance.getSelectionModel().getSelectedItem().loan_date);
    			calList = RepaymentBalanceFunction.balanceReCal(RepaymentBalanceParts.getRepaymentBalance());
    			RepaymentBalanceParts.upNewBalance(calList);
    			rs.clear();
    			rs = RepaymentBalanceParts.getRepaymentBalance();
    			DebtParts.upDebt();
    			rs2.clear();
    			rs2 = DebtParts.getDebt();
    			aaa.set(String.valueOf(rs2.get(0).debt_balance));
    			lst_balance.setText(String.valueOf(rs.get(rs.size()-1).balance));
    			fullRepayedJudge();
    		}else if(diaRs.get() == ButtonType.NO) {
    			dialog.close();
    		}
    	}
    }

    @FXML
    void fullRepayedJudge() {
    	if(Long.valueOf(debt_balance.getText()) <= 0) {
    		aaa.set("完済");
    		System.out.println("完済！");
    	}
    }



}