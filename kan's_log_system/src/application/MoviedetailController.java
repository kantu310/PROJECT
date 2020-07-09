package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import data.ConstantData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import table.Movie;

public class MoviedetailController extends MovieController{

    @FXML
    private AnchorPane paneMovieDetail;

    @FXML
    private ImageView imgMovie;

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
    private HBox hBoxMovieEval;

    @FXML
    private HBox hBoxMoviePop;


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
    			for(int i = 0; i < movie.movie_evaluation; i++) {
    				Image img = new Image("application/image/star_48px.png");
    				ImageView evImg = new ImageView(img);
    				evImg.setFitWidth(30);
    				evImg.setFitHeight(30);
    				hBoxMovieEval.getChildren().add(evImg);
    			}

    			//ポップコーン度表示
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




}
