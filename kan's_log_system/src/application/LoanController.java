package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class LoanController {

	ObservableList<String> loanYearList = FXCollections.observableArrayList("2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032");
	ObservableList<String> loanMonthList = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");

	@FXML
	private ChoiceBox<String> loan_year;

	@FXML
	private ChoiceBox<String> loan_month;

	@FXML
	private void initialize() {
		loan_year.setItems(loanYearList);
		loan_month.setItems(loanMonthList);
	}
}