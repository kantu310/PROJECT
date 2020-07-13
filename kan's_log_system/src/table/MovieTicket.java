package table;

public class MovieTicket {

	public String movie_ticket_id;//チケットＩＤ
	public String movie_theater_type_id;//映画館種類ＩＤ
	public String movie_ticket;//チケット

	public MovieTicket(String movie_ticket_id, String movie_theater_type_id, String movie_ticket) {
		super();
		this.movie_ticket_id = movie_ticket_id;
		this.movie_theater_type_id = movie_theater_type_id;
		this.movie_ticket = movie_ticket;
	}

	public String getMovie_ticket_id() {
		return movie_ticket_id;
	}

	public void setMovie_ticket_id(String movie_ticket_id) {
		this.movie_ticket_id = movie_ticket_id;
	}

	public String getMovie_theater_type_id() {
		return movie_theater_type_id;
	}

	public void setMovie_theater_type_id(String movie_theater_type_id) {
		this.movie_theater_type_id = movie_theater_type_id;
	}

	public String getMovie_ticket() {
		return movie_ticket;
	}

	public void setMovie_ticket(String movie_ticket) {
		this.movie_ticket = movie_ticket;
	}





}
