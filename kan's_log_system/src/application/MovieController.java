package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import parts.MovieParts;
import table.Movie;

public class MovieController extends Controller {

	public static ObservableList<Movie> movieList = FXCollections.observableArrayList();


    @FXML
    private AnchorPane loanpane;

    @FXML
    private ScrollPane movieScrPane;

    @FXML
    private FlowPane movieFlowPane;

    @FXML
    private Text movie_year;



    @FXML
	private void initialize() throws SQLException {
    	movieImageDisplay();
    }

    @FXML
    public void movieImageDisplay() throws SQLException {

    	byte[] r = null;

    	movieList = MovieParts.getMovie();



    	for (Movie movie : movieList) {
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
				} catch (IOException e) {
			    	  e.printStackTrace();
			    	  r = null;
				}
		}



    }

}
