package table;

import java.sql.Blob;
import java.sql.Date;

public class Movie {

	public int movie_id;//映画ＩＤ
	public Date movie_date;//映画鑑賞日付
	public String movie_title;//映画タイトル
	public Blob movie_image;//映画画像
	public int movie_evaluation;//映画の評価
	public int movie_popcorn;//ポップコーン度
	public String movie_theater_name;//鑑賞映画館名
	public String movie_ticket;//購入チケット
	public int movie_time;//上映時間

	//コンストラクタ
	public Movie(int movie_id, Date movie_date, String movie_title, Blob movie_image, int movie_evaluation,
			int movie_popcorn, String movie_theater_id, String movie_ticket, int movie_time) {
		super();
		this.movie_id = movie_id;
		this.movie_date = movie_date;
		this.movie_title = movie_title;
		this.movie_image = movie_image;
		this.movie_evaluation = movie_evaluation;
		this.movie_popcorn = movie_popcorn;
		this.movie_theater_name = movie_theater_id;
		this.movie_ticket = movie_ticket;
		this.movie_time = movie_time;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public Date getMovie_date() {
		return movie_date;
	}

	public void setMovie_date(Date movie_date) {
		this.movie_date = movie_date;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public Blob getMovie_image() {
		return movie_image;
	}

	public void setMovie_image(Blob movie_image) {
		this.movie_image = movie_image;
	}

	public int getMovie_evaluation() {
		return movie_evaluation;
	}

	public void setMovie_evaluation(int movie_evaluation) {
		this.movie_evaluation = movie_evaluation;
	}

	public int getMovie_popcorn() {
		return movie_popcorn;
	}

	public void setMovie_popcorn(int movie_popcorn) {
		this.movie_popcorn = movie_popcorn;
	}

	public String getMovie_theater_id() {
		return movie_theater_name;
	}

	public void setMovie_theater_id(String movie_theater_id) {
		this.movie_theater_name = movie_theater_id;
	}

	public String getMovie_ticket() {
		return movie_ticket;
	}

	public void setMovie_ticket(String movie_ticket) {
		this.movie_ticket = movie_ticket;
	}

	public int getMovie_time() {
		return movie_time;
	}

	public void setMovie_time(int movie_time) {
		this.movie_time = movie_time;
	}




}
