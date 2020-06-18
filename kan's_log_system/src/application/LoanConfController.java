package application;

import java.util.Optional;
import java.util.regex.Pattern;

import data.ConstantData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import parts.DebtParts;
import table.Debt;

public class LoanConfController extends LoanController {
	Alert dialog = new Alert(AlertType.NONE , "登録しますか？" , ButtonType.YES , ButtonType.NO);
	Alert dialog2 = new Alert(AlertType.NONE, "登録が完了しました。", ButtonType.OK);


    @FXML
    private TextField newDebt;

    @FXML
    private TextField newFixedmoney;

    @FXML
    private Button btnNewDebt;

    @FXML
    private Button btnNewBalance;

    @FXML
    private Text currentDebt;

    @FXML
    private Text currentFixedMoney;




    @FXML
	private void initialize() {

    	currentDebt.setText(String.valueOf(ConstantData.getCurrentDebt()));
    	currentFixedMoney.setText(String.valueOf(ConstantData.getCurrentFixedMoney()));

    }

    @FXML
    void onBtnNewBalance(ActionEvent event) {

       	//前ゼロトリム
    		String a = newDebt.getText();
    		newDebt.setText(a.replaceFirst("^0.+", "0"));

    		dialog.setTitle("確認");
    		Optional<ButtonType> diaRs = dialog.showAndWait();
    		if(diaRs.get() == ButtonType.YES) {
    			DebtParts.upNewFixedMoney(newFixedmoney.getText());
    			dialog2.setTitle("完了");
    			dialog2.showAndWait();
    			currentFixedMoney.setText(String.valueOf(ConstantData.getCurrentFixedMoney()));
    		}else {
    			dialog.close();
    		}

    }

    @FXML
    void onBtnNewDebt(ActionEvent event) {

    	//前ゼロトリム
		String a = newDebt.getText();
		newDebt.setText(a.replaceFirst("^0.+", "0"));

		dialog.setTitle("確認");
		Optional<ButtonType> diaRs = dialog.showAndWait();
		if(diaRs.get() == ButtonType.YES) {
			DebtParts.upNewDebt(newDebt.getText());
			DebtParts.upDebt();
	    	ObservableList<Debt> ui= DebtParts.getDebt();
	    	String bb =  String.valueOf(ui.get(0).debt_balance);
	    	LoanController.aaa.set(bb);

			dialog2.setTitle("完了");
			dialog2.showAndWait();
			currentDebt.setText(String.valueOf(ConstantData.getCurrentDebt()));
		}else {
			dialog.close();
		}

    }

    @FXML
    void onKeyPrsConfDbt(KeyEvent event) {
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
		newDebt.setTextFormatter(lowerFormatter);
    }

    @FXML
    void onKeyPrsConfFixedMoney(KeyEvent event) {
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
		newFixedmoney.setTextFormatter(lowerFormatter);
    }

    @FXML
    void onBtnClose(ActionEvent event) {

    	((Node) event.getSource()).getScene().getWindow().hide();


    }


}
