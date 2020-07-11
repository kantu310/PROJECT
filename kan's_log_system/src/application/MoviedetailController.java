package application;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import data.ConstantData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import table.Movie;

public class MoviedetailController extends MovieController{

	public static int evalCnt;
	public static int popCnt;
	public static int evalRet;
	public static int popRet;


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
    private ImageView dePop;

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
    private ComboBox<?> regMobieTheater;

    @FXML
    private ComboBox<?> regMovieTicket;

    @FXML
    private Button btnEditMode;

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

    			//編集モードのノードにも値を設定
    	        regMovieTitle.setText(txtMovieTitle.getText());
    	        regMovieSeat.setText(txtMovieSeat.getText());
    	        String regMoTime = txtMovieTime.getText();
    	        regMovieTime.setText(regMoTime.replace("分", ""));//"分"トリム
    	        regImageMovie.setImage(imgMovie.getImage());

    			//評価表示
    	        evalRet = movie.movie_evaluation;//評価値を保持
    			for(int i = 0; i < movie.movie_evaluation; i++) {
    				Image img = new Image("application/image/star_48px.png");
    				ImageView evImg = new ImageView(img);
    				evImg.setFitWidth(30);
    				evImg.setFitHeight(30);
    				hBoxMovieEval.getChildren().add(evImg);
    			}

    			//ポップコーン度表示
    			popRet = movie.movie_popcorn;//評価値を保持
    			for(int i = 0; i < movie.movie_popcorn; i++) {
    				Image img = new Image("application/image/popcorn_48px.png");
    				ImageView evImg = new ImageView(img);

    				evImg.setFitWidth(30);
    				evImg.setFitHeight(30);
    				hBoxMoviePop.getChildren().add(evImg);
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
    void onBtnEditMode(ActionEvent event) {

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
        regMobieTheater.setVisible(true);
        regMovieTicket.setVisible(true);
        regImageMovie.setVisible(true);
        btnFileCho.setVisible(true);
        btnReg.setVisible(true);
        btnBackEditMode.setVisible(true);
        upEval.setVisible(true);
        dwEval.setVisible(true);
        upPop.setVisible(true);
        dePop.setVisible(true);
    }

    @FXML
    void onBtnFileCho(ActionEvent event) {

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

        regMovieTitle.setVisible(false);
        regMovieSeat.setVisible(false);
        regMovieTime.setVisible(false);
        regMovieDate.setVisible(false);
        regMobieTheater.setVisible(false);
        regMovieTicket.setVisible(false);
        regHBoxMoviePop.setVisible(false);
        regHBoxMovieEval.setVisible(false);
        btnFileCho.setVisible(false);
        btnReg.setVisible(false);
        btnBackEditMode.setVisible(false);
        upEval.setVisible(false);
        dwEval.setVisible(false);
        upPop.setVisible(false);
        dePop.setVisible(false);


    }


}
