package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class LoanController {

	ObservableList<String> loanYearList = FXCollections.observableArrayList("2020");
	ObservableList<String> loanMonthList = FXCollections.observableArrayList("a");

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