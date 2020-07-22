package application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainmenuController extends Main{
	Stage stage;
	Scene scene;

    @FXML
    private Pane pane_top;

    @FXML
    private Pane pane_loan;

    @FXML
    private Pane pane_movie;

    @FXML
    private Pane pane_book;

    @FXML
    private Pane pane_account;

    @FXML
    private Button btn_top;

    @FXML
    private Button btn_book;

    @FXML
    private Button btn_loan;

    @FXML
    private Button btn_movie;

    @FXML
    private Button btn_account;

    @FXML
	private void initialize() throws IOException {

		AnchorPane pane = FXMLLoader.load(getClass().getResource("Top.fxml"));
		pane_top.getChildren().setAll(pane);

    }

    @FXML
	public void onButtonLoan(Event event) throws Exception {

		AnchorPane pane = FXMLLoader.load(getClass().getResource("Loan.fxml"));
		pane_top.getChildren().setAll(pane);
	}

    @FXML
    void onButtonMovie(Event event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("Movie.fxml"));
		pane_top.getChildren().setAll(pane);

    }

    @FXML
    void onButtonTop(Event event) throws IOException {

		AnchorPane pane = FXMLLoader.load(getClass().getResource("Top.fxml"));
		pane_top.getChildren().setAll(pane);

    }

    @FXML
    void onButtonBook(Event event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("Book.fxml"));
		pane_top.getChildren().setAll(pane);

    }


}

