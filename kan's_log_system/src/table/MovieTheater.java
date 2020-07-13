package table;

public class MovieTheater {

	public String movie_theater_id;//映画館ＩＤ
	public String movie_theater_type_id;//映画館種類ＩＤ
	public String movie_theater_name;//映画館劇場名

	public MovieTheater(String movie_theater_id, String movie_theater_type_id, String movie_theater_name) {
		super();
		this.movie_theater_id = movie_theater_id;
		this.movie_theater_type_id = movie_theater_type_id;
		this.movie_theater_name = movie_theater_name;
	}

	public String getMovie_theater_id() {
		return movie_theater_id;
	}

	public void setMovie_theater_id(String movie_theater_id) {
		this.movie_theater_id = movie_theater_id;
	}

	public String getMovie_theater_type_id() {
		return movie_theater_type_id;
	}

	public void setMovie_theater_type_id(String movie_theater_type_id) {
		this.movie_theater_type_id = movie_theater_type_id;
	}

	public String getMovie_theater_name() {
		return movie_theater_name;
	}

	public void setMovie_theater_name(String movie_theater_name) {
		this.movie_theater_name = movie_theater_name;
	}






}
