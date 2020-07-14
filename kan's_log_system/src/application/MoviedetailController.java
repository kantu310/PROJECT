package application;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import parts.MovieTheaterParts;
import parts.MovieTicketParts;
import table.Movie;
import table.MovieTheater;
import table.MovieTicket;

public class MoviedetailController extends MovieController{

	public static int evalCnt;
	public static int popCnt;
	public static Blob regImage;
	public static FileInputStream fis;
	public static File selectedFile;
	public static ObservableList<MovieTheater> movieThList = FXCollections.observableArrayList();
	public static ObservableList<MovieTicket> movieTicList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane paneMovieDetail;

    @FXML
    private Text txtMovieTitle;

    @FXML
    private Text txtMovieDate;

    @FXML
    private Text txtMovieTheater;

    @FXML
    private Text txtMovieTicket;

    @FXML
    private Text txtMovieSeat;

    @FXML
    private Text txtMovieTime;

    @FXML
    private ImageView btnBackMovie;

    @FXML
    private ImageView btnBackEditMode;

    @FXML
    private ImageView regImageMovie;

    @FXML
    private ImageView imgMovie;

    @FXML
    private ImageView upEval;

    @FXML
    private ImageView dwEval;

    @FXML
    private ImageView upPop;

    @FXML
    private ImageView dwPop;

    @FXML
    private HBox hBoxMovieEval;

    @FXML
    private HBox regHBoxMovieEval;

    @FXML
    private HBox hBoxMoviePop;

    @FXML
    private HBox regHBoxMoviePop;

    @FXML
    private TextField regMovieTitle;

    @FXML
    private TextField regMovieSeat;

    @FXML
    private TextField regMovieTime;

    @FXML
    private DatePicker regMovieDate;

    @FXML
    private ComboBox<String> regMovieTheater;

    @FXML
    private ComboBox<String> regMovieTicket;

    @FXML
    private ImageView btnEditMode;

    @FXML
    private Button btnFileCho;

    @FXML
    private Button btnReg;




    @FXML
	private void initialize() throws SQLException{

    	for (Movie movie : movieList) {
    		if(movie.movie_id == ConstantData.getMovie_id()) {
    			//イメージを表示
    			byte[] r = null;
    			InputStream is = movie.movie_image.getBinaryStream();
    			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    			byte[] bs = new byte [1024];
    			int size = 0;
    			try {
    				while( ( size = is.read( bs ) ) != -1 ){
    					baos.write( bs, 0, size );
    				}
    				r = baos.toByteArray();  //. byte[] 型に変換してデータを取得
    				Image img = new Image(new ByteArrayInputStream(r));
    				imgMovie.setImage(img);
    			} catch (IOException e) {
    				e.printStackTrace();
    				r = null;
    			}
    			txtMovieTitle.setText(movie.movie_title);//タイトル表示
    			txtMovieDate.setText(String.valueOf(movie.movie_date));//鑑賞日付表示
    			txtMovieTheater.setText(movie.movie_theater_name);//鑑賞劇場表示
    			txtMovieTicket.setText(movie.movie_ticket);//鑑賞チケット表示
    			txtMovieSeat.setText(movie.movie_seat);//鑑賞シート表示
    			txtMovieTime.setText(String.valueOf(movie.movie_time + "分"));//上映時間表示

    			//評価表示
    	        evalCnt = movie.movie_evaluation;//評価値を保持
    			for(int i = 0; i < movie.movie_evaluation; i++) {
    				Image img = new Image("application/image/star_48px.png");
    				ImageView evImg = new ImageView(img);
    				evImg.setFitWidth(30);
    				evImg.setFitHeight(30);
    				hBoxMovieEval.getChildren().add(evImg);
    			}

    			//ポップコーン度表示
    			popCnt = movie.movie_popcorn;//評価値を保持
    			for(int i = 0; i < movie.movie_popcorn; i++) {
    				Image img = new Image("application/image/popcorn_48px.png");
    				ImageView evImg = new ImageView(img);

    				evImg.setFitWidth(30);
    				evImg.setFitHeight(30);
    				hBoxMoviePop.getChildren().add(evImg);
    			}

    			movieThList = MovieTheaterParts.getMovieTheater();
    			movieTicList = MovieTicketParts.getMovieTicket();
    			for (MovieTheater th : movieThList) {
					String thName = th.movie_theater_name;
	    			regMovieTheater.getItems().addAll(thName);
				}
    		}
    	}
    }


    @FXML
    void onMouseBtnBackMovie(MouseEvent event) {
		AnchorPane pane;
		try {
			pane = FXMLLoader.load(getClass().getResource("Movie.fxml"));
			paneMovieDetail.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }


    @FXML
    void onBtnEditMode(MouseEvent event) {

    	//非表示
    	txtMovieTitle.setVisible(false);
    	txtMovieDate.setVisible(false);
    	txtMovieTheater.setVisible(false);
    	txtMovieTicket.setVisible(false);
    	txtMovieSeat.setVisible(false);
    	txtMovieTime.setVisible(false);
    	btnBackMovie.setVisible(false);
    	imgMovie.setVisible(false);
    	btnBackMovie.setVisible(false);
        btnEditMode.setVisible(false);

        //編集モード表示
        regMovieTitle.setVisible(true);
        regMovieSeat.setVisible(true);
        regMovieTime.setVisible(true);
        regMovieDate.setVisible(true);
        regMovieTheater.setVisible(true);
        regMovieTicket.setVisible(true);
        regImageMovie.setVisible(true);
        btnFileCho.setVisible(true);
        btnReg.setVisible(true);
        btnBackEditMode.setVisible(true);
        if(!(evalCnt == 5)) {
        	upEval.setVisible(true);
        }
        if(!(evalCnt ==0)) {
            dwEval.setVisible(true);
        }

        if(!(popCnt == 5)) {
            upPop.setVisible(true);
        }
        if(!(popCnt ==0)) {
            dwPop.setVisible(true);
        }

		//編集モードのノードにも値を設定
        regMovieTitle.clear();
        regMovieSeat.clear();
        regMovieTime.clear();
        regMovieTime.clear();
        regMovieTitle.setText(txtMovieTitle.getText());
        regMovieSeat.setText(txtMovieSeat.getText());
        String regMoTime = txtMovieTime.getText();
        regMovieTime.setText(regMoTime.replace("分", ""));//"分"トリム
        regImageMovie.setImage(imgMovie.getImage());
        LocalDate loDate = LocalDate.parse(txtMovieDate.getText());
        regMovieDate.setValue(loDate);

    }

    @FXML
    void onBtnFileCho(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.setTitle("ファイルを開く");
    	fc.getExtensionFilters().addAll(new  ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    	selectedFile = fc.showOpenDialog(stage);
    	try {
			fis = new FileInputStream(selectedFile);
			Image img = new Image(fis);
			regImageMovie.setImage(img);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onMouseBtnBackEditMode(MouseEvent event) {
    	//編集モードを非表示して、詳細画面を表示する
    	txtMovieTitle.setVisible(true);
    	txtMovieTitle.setVisible(true);
    	txtMovieDate.setVisible(true);
    	txtMovieTheater.setVisible(true);
    	txtMovieTicket.setVisible(true);
    	txtMovieSeat.setVisible(true);
    	txtMovieTime.setVisible(true);
    	btnBackMovie.setVisible(true);
    	hBoxMovieEval.setVisible(true);
    	hBoxMoviePop.setVisible(true);
    	imgMovie.setVisible(true);
    	btnBackMovie.setVisible(true);
        btnEditMode.setVisible(true);
        //編集モード非表示
        regMovieTitle.setVisible(false);
        regMovieSeat.setVisible(false);
        regMovieTime.setVisible(false);
        regMovieDate.setVisible(false);
        regMovieTheater.setVisible(false);
        regMovieTicket.setVisible(false);
        regHBoxMoviePop.setVisible(false);
        regHBoxMovieEval.setVisible(false);
        btnFileCho.setVisible(false);
        btnReg.setVisible(false);
        btnBackEditMode.setVisible(false);
        upEval.setVisible(false);
        dwEval.setVisible(false);
        upPop.setVisible(false);
        dwPop.setVisible(false);
        //リセット
        hBoxMovieEval.getChildren().clear();
        hBoxMoviePop.getChildren().clear();
        regImageMovie.setImage(null);

    	for (Movie movie : movieList) {
    		if(movie.movie_id == ConstantData.getMovie_id()) {
    			//評価再表示
    			for(int i = 0; i < movie.movie_evaluation; i++) {
    				Image img = new Image("application/image/star_48px.png");
    				ImageView evImg = new ImageView(img);
    				evImg.setFitWidth(30);
    				evImg.setFitHeight(30);
    				hBoxMovieEval.getChildren().add(evImg);
    			}
    		     evalCnt = movie.movie_evaluation;//カウンターリセット

    			//ポップコーン度再表示
    			for(int i = 0; i < movie.movie_popcorn; i++) {
    				Image img = new Image("application/image/popcorn_48px.png");
    				ImageView evImg = new ImageView(img);
    				evImg.setFitWidth(30);
    				evImg.setFitHeight(30);
    				hBoxMoviePop.getChildren().add(evImg);
    			}
    			popCnt = movie.movie_popcorn;//カウンターリセット
    		}
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
			hBoxMovieEval.getChildren().add(evImg);
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
			hBoxMovieEval.getChildren().remove(evalCnt);
			System.out.println(evalCnt);
    	}
    	if(evalCnt == 0) {
    		dwEval.setVisible(false);
			System.out.println(evalCnt);
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
			hBoxMoviePop.getChildren().add(evImg);
			System.out.println(popCnt);
    	}
    	if(popCnt == 5) {
    		upPop.setVisible(false);
			System.out.println(popCnt);
    	}
    }

    @FXML
    void onDwPop(MouseEvent event) {
    	if(!(popCnt == 0)) {
    		popCnt = popCnt - 1;
    		upPop.setVisible(true);
			hBoxMoviePop.getChildren().remove(popCnt);
			System.out.println(popCnt);
    	}
    	if(popCnt == 0) {
    		dwPop.setVisible(false);
			System.out.println(popCnt);
    	}
    }

    @FXML
    void onSelectedMovieTheater(ActionEvent event) {
    	//チケットコンボボックスリセット
    	regMovieTicket.getItems().clear();
    	//共通のチケットを設定
    	for (MovieTicket a : movieTicList) {
    		if(a.movie_theater_type_id.equals("common")) {
    			String Name = a.movie_ticket;
    			regMovieTicket.getItems().add(Name);
    		}
    	}
    	//映画館コンボボックスで設定した映画館の種類と一致するチケットを設定
    	for (MovieTheater aa: movieThList) {
    		if(regMovieTheater.getValue().equals(aa.movie_theater_name)) {
    			String theaterId = aa.movie_theater_type_id;
    			for (MovieTicket bb : movieTicList) {
    				if(theaterId.equals(bb.movie_theater_type_id)) {
    					String ticName = bb.movie_ticket;
    					regMovieTicket.getItems().addAll(ticName);
    				}
    			}
    		}
    	}
    }



    @FXML
    void onBtnReg(ActionEvent event) throws FileNotFoundException {
    	Alert dialog = new Alert(AlertType.NONE,"登録しますか？",ButtonType.YES,ButtonType.NO);
    	dialog.setTitle("確認");
		Alert dialog2 = new Alert(AlertType.NONE, "入力されていない項目があります", ButtonType.OK);
		dialog2.setTitle("エラー");


		if(regMovieTitle.getText() == null ||
				regMovieDate.getValue() == null ||
				regMovieTheater.getValue() == null ||
				regMovieTicket.getValue() == null ||
				regMovieSeat.getText() == null ||
				regMovieTime.getText() == null) {
			dialog2.showAndWait();
		}else {
			Optional<ButtonType >diaRs =dialog.showAndWait();
			if(diaRs.get() == ButtonType.YES) {
				Connection conn =null;
				fis = new FileInputStream(selectedFile);
				System.out.println((int)selectedFile.length()+"upup");

				try {
					conn = DriverManager.getConnection(ConstantData.MYSQL_URL, ConstantData.MYSQL_USER,
							ConstantData.MYSQL_PASSWORD);
					String sql = "update kan_system.movie set movie_date = ?, movie_title = ?, movie_image = ?, movie_evaluation =?, movie_popcorn = ?, movie_seat = ?, movie_time = ? where movie_id = ?";
					PreparedStatement stmt = conn.prepareStatement(sql);

					stmt.setString(1,regMovieDate.getValue().toString());
					stmt.setString(2, regMovieTitle.getText());
					stmt.setBinaryStream(3, (InputStream)fis,(int)selectedFile.length());
					stmt.setInt(4, evalCnt);
					stmt.setInt(5, popCnt);
					stmt.setString(6, regMovieSeat.getText());
					stmt.setInt(7, Integer.valueOf(regMovieTime.getText()));
					stmt.setInt(8, ConstantData.getMovie_id());

					int r = stmt.executeUpdate();

				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}


			}else if(diaRs.get() == ButtonType.NO){
				dialog.close();
			}
		}


    }
}
