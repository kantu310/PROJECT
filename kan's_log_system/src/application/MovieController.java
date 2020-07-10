package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import parts.MovieParts;
import table.Movie;

public class MovieController extends MainmenuController {

	public static ObservableList<Movie> movieList = FXCollections.observableArrayList();
	public static List<String> movieYearList = new ArrayList<>();
	public static int yearCnt = 0;
	public static int yearMaxCnt;
	SimpleDateFormat smfyear = new SimpleDateFormat("yyyy");


	@FXML
	private AnchorPane moviepane;

	@FXML
	private ScrollPane movieScrPane;

	@FXML
	private FlowPane movieFlowPane;

	@FXML
	private Text movie_year;

	@FXML
	private ImageView btnMovieYearBk;

	@FXML
	private ImageView btnMovieYearFr;


	@FXML
	private void initialize() throws SQLException {
		movieList = MovieParts.getMovie();//movieテーブルから情報を取得
		//鑑賞日付から年を抽出し、リストを作成
		for (Movie movie : movieList) {
			String year = smfyear.format(movie.movie_date);
			//重複を除いて年をリストに追加
			if(!movieYearList.contains(year)){
				movieYearList.add(year);
			}
		}
		yearMaxCnt = movieYearList.size()-1;//鑑賞年の最大インデックスを格納

		movie_year.setText(String.valueOf(movieYearList.get(yearCnt)));

		//ボタンの初期化
		if(yearCnt != 0) {
			btnMovieYearFr.setVisible(true);
		}
		if(yearCnt == yearMaxCnt) {
			btnMovieYearBk.setVisible(false);
		}
		movieImageDisplay(yearCnt);
	}

	@FXML
	public void movieImageDisplay(int a) throws SQLException {

		byte[] r = null;


		//映画イメージを表示
		for (Movie movie : movieList) {

			String year = smfyear.format(movie.movie_date);

			if(year.equals(String.valueOf(movieYearList.get(a)))) {
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
					ImageView imgv = new ImageView(img);
					imgv.setFitHeight(280);
					imgv.setFitWidth(180);
					movieFlowPane.getChildren().addAll(imgv);
					imgv.setOnMouseClicked(event -> movieDetailDisplay(movie.movie_id));
				} catch (IOException e) {
					e.printStackTrace();
					r = null;
				}
			}
		}
	}


	@FXML
	public void movieDetailDisplay(int a) {
		ConstantData.setMovie_id(a);
		AnchorPane pane;
		try {
			pane = FXMLLoader.load(getClass().getResource("Moviedetail.fxml"));
			moviepane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	@FXML
	void onBtnMovieYearBk(MouseEvent event) {
		btnMovieYearFr.setVisible(true);
		yearCnt = yearCnt +1;
			try {
				movieFlowPane.getChildren().clear();
				movie_year.setText(String.valueOf(movieYearList.get(yearCnt)));
				movieImageDisplay(yearCnt);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			if(yearCnt==yearMaxCnt) {
				btnMovieYearBk.setVisible(false);
			}

	}

	@FXML
	void onBtnMovieYearFr(MouseEvent event) {
		btnMovieYearBk.setVisible(true);
		yearCnt = yearCnt -1;
			try {
				movieFlowPane.getChildren().clear();
				movie_year.setText(String.valueOf(movieYearList.get(yearCnt)));
				movieImageDisplay(yearCnt);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			if(yearCnt==0) {
				btnMovieYearFr.setVisible(false);
			}

	}


}
