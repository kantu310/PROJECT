package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import parts.MovieTheaterParts;
import parts.MovieTicketParts;
import table.MovieTheater;
import table.MovieTicket;

public class MovieAddController{

	Stage st = new Stage();
	public  int evalCnt = 0;
	public  int popCnt = 0;
	public  FileInputStream fis = null;
	public  File selectedFile = null;
	public  InputStream is = null;
	byte[] r = null;
	public static ObservableList<MovieTheater> movieThList = FXCollections.observableArrayList();
	public static ObservableList<MovieTicket> movieTicList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane paneMovieDetail;

    @FXML
    private ImageView addImageMovie;

    @FXML
    private HBox addhBoxMovieEval;

    @FXML
    private HBox regHBoxMovieEval;

    @FXML
    private HBox addhBoxMoviePop;

    @FXML
    private HBox regHBoxMoviePop;

    @FXML
    private TextField addMovieTitle;

    @FXML
    private DatePicker addMovieDate;

    @FXML
    private ComboBox<String> addMovieTheater;

    @FXML
    private ComboBox<String> addMovieTicket;

    @FXML
    private TextField addMovieSeat;

    @FXML
    private TextField addMovieTime;

    @FXML
    private ImageView regImageMovie;

    @FXML
    private ImageView upEval;

    @FXML
    private ImageView dwEval;

    @FXML
    private ImageView upPop;

    @FXML
    private ImageView dwPop;

    @FXML
    private Button btnReg;

    @FXML
    private Button btnFileCho;

    @FXML
    private Button closeAdd;


	@FXML
	private void initialize() throws SQLException {
		movieThList = MovieTheaterParts.getMovieTheater();
		movieTicList = MovieTicketParts.getMovieTicket();
		for (MovieTheater th : movieThList) {
			String thName = th.movie_theater_name;
			addMovieTheater.getItems().addAll(thName);
		}
	}

	    @FXML
		void onBtnFileCho(ActionEvent event) {
	    	FileChooser fc = new FileChooser();
	    	fc.setTitle("ファイルを開く");
	    	fc.getExtensionFilters().addAll(new  ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
	    	selectedFile = fc.showOpenDialog(st);
	    	try {
				fis = new FileInputStream(selectedFile);
				Image img = new Image(fis);
				addImageMovie.setImage(img);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	    @FXML
	    void onUpEval(MouseEvent event) {
	    	if(!(evalCnt == 5)) {
	    		evalCnt = evalCnt + 1;
	    		dwEval.setVisible(true);
				Image img = new Image("application/image/star_48px.png");
				ImageView evImg = new ImageView(img);
				evImg.setFitWidth(30);
				evImg.setFitHeight(30);
				addhBoxMovieEval.getChildren().add(evImg);
				System.out.println(evalCnt);
	    	}
	    	if(evalCnt == 5) {
	    		upEval.setVisible(false);
				System.out.println(evalCnt);
	    	}
	    }

	    @FXML
	    void onDwEval(MouseEvent event) {
	    	if(!(evalCnt == 0)) {
	    		evalCnt = evalCnt - 1;
	    		upEval.setVisible(true);
				addhBoxMovieEval.getChildren().remove(evalCnt);
				System.out.println(evalCnt);
	    	}
	    	if(evalCnt == 0) {
	    		dwEval.setVisible(false);
	    	}
	    }

	    @FXML
	    void onUpPop(MouseEvent event) {
	    	if(!(popCnt == 5)) {
	    		popCnt = popCnt + 1;
	    		dwPop.setVisible(true);
				Image img = new Image("application/image/popcorn_48px.png");
				ImageView evImg = new ImageView(img);
				evImg.setFitWidth(30);
				evImg.setFitHeight(30);
				addhBoxMoviePop.getChildren().add(evImg);
	    	}
	    	if(popCnt == 5) {
	    		upPop.setVisible(false);
	    	}
	    }

	    @FXML
	    void onDwPop(MouseEvent event) {
	    	if(!(popCnt == 0)) {
	    		popCnt = popCnt - 1;
	    		upPop.setVisible(true);
				addhBoxMoviePop.getChildren().remove(popCnt);
	    	}
	    	if(popCnt == 0) {
	    		dwPop.setVisible(false);
	    	}
	    }

	    @FXML
	    void onSelectedMovieTheater(ActionEvent event) {
	    	//チケットコンボボックスリセット
	    	addMovieTicket.getItems().clear();
	    	//共通のチケットを設定
	    	for (MovieTicket a : movieTicList) {
	    		if(a.movie_theater_type_id.equals("common")) {
	    			String Name = a.movie_ticket;
	    			addMovieTicket.getItems().add(Name);
	    		}
	    	}
	    	//映画館コンボボックスで設定した映画館の種類と一致するチケットを設定
	    	for (MovieTheater aa: movieThList) {
	    		if(addMovieTheater.getValue().equals(aa.movie_theater_name)) {
	    			String theaterId = aa.movie_theater_type_id;
	    			for (MovieTicket bb : movieTicList) {
	    				if(theaterId.equals(bb.movie_theater_type_id)) {
	    					String ticName = bb.movie_ticket;
	    					addMovieTicket.getItems().addAll(ticName);
	    				}
	    			}
	    		}
	    	}
	    }
	    @FXML
	    void onBtnReg(ActionEvent event) {
	    	Alert dialog = new Alert(AlertType.NONE,"登録しますか？",ButtonType.YES,ButtonType.NO);
	    	dialog.setTitle("確認");
			Alert dialog2 = new Alert(AlertType.NONE, "入力されていない項目があります", ButtonType.OK);
			dialog2.setTitle("エラー");
	    	Alert dialog3 = new Alert(AlertType.NONE,"登録しました。",ButtonType.OK);
	    	dialog3.setTitle("完了");

			if(addMovieTitle.getText() == null ||
					addMovieDate.getValue() == null ||
					addMovieTheater.getValue() == null ||
					addMovieTicket.getValue() == null ||
					addMovieSeat.getText() == null ||
					addMovieTime.getText() == null) {
				dialog2.showAndWait();
			}else {
				Optional<ButtonType >diaRs =dialog.showAndWait();
				if(diaRs.get() == ButtonType.YES) {
					Connection conn =null;
					try {
						conn = DriverManager.getConnection(ConstantData.MYSQL_URL, ConstantData.MYSQL_USER,
								ConstantData.MYSQL_PASSWORD);

						String sql = "insert into  kan_system.movie (movie_date, movie_title, movie_image, movie_evaluation, movie_popcorn, movie_seat, movie_time, movie_theater_id, movie_ticket_id,user_id) values (?,?,?,?,?,?,?,?,?,?)";


						PreparedStatement stmt = conn.prepareStatement(sql);

						stmt.setString(1,addMovieDate.getValue().toString());
						stmt.setString(2, addMovieTitle.getText());
						if(!(fis == null)) {
							fis = new FileInputStream(selectedFile);
							stmt.setBinaryStream(3, (InputStream)fis,(int)selectedFile.length());
						}else {
							stmt.setBytes(3, null);
						}
						stmt.setInt(4, evalCnt);
						stmt.setInt(5, popCnt);
						stmt.setString(6, addMovieSeat.getText());
						stmt.setInt(7, Integer.valueOf(addMovieTime.getText()));
						for (MovieTheater th : movieThList) {
							if(th.movie_theater_name.equals(addMovieTheater.getValue())) {
								stmt.setString(8, th.movie_theater_id);
							}
						}
						for(MovieTicket tic: movieTicList) {
							if(tic.movie_ticket.equals(addMovieTicket.getValue())) {
								stmt.setString(9, tic.movie_ticket_id);
							}
						}
						stmt.setString(10, ConstantData.loginUserID);

						int r = stmt.executeUpdate();
						dialog3.showAndWait();
				    	((Node) event.getSource()).getScene().getWindow().hide();


						} catch (SQLException e) {
						// TODO 自動生成された catch ブロック
						Alert dialog4 = new Alert(AlertType.NONE, "画像サイズが登録上限を超えています", ButtonType.OK);
						dialog4.setTitle("エラー");
						dialog4.showAndWait();
						e.printStackTrace();
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}else if(diaRs.get() == ButtonType.NO){
					dialog.close();
				}
			}
	    }

	    @FXML
	    void onCloseAdd(ActionEvent event) {
	    	((Node) event.getSource()).getScene().getWindow().hide();
	    }
	    @FXML
	    void onKeyTime(KeyEvent event) {
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
			addMovieTime.setTextFormatter(lowerFormatter);

	    }






}
