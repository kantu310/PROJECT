package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import parts.MovieParts;
import table.Movie;

public class MovieController extends Controller {

	public static ObservableList<Movie> movieList = FXCollections.observableArrayList();

	    @FXML
	    private ScrollPane scroll;

    @FXML
	private void initialize() {
    	movieImageDisplay();
    }

    @FXML
    public void movieImageDisplay() {

    	movieList = MovieParts.getMovie();
   		VBox vb = new VBox();

    	for (Movie movie : movieList) {
    		for(int i = 0; i < 5; i++) {

    		}
		}



    }

}
