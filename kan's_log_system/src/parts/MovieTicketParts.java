package parts;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import table.MovieTicket;

public class MovieTicketParts {
	public static ObservableList<MovieTicket>getMovieTicket(){

		ObservableList<MovieTicket> obList = FXCollections.observableArrayList();

		String sql = "SELECT * FROM kan_system.movie_ticket order by num;";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {

				obList.add(new MovieTicket(num.getString(ConstantData.MOVIE_TICKET_ID),
						num.getString(ConstantData.MOVIE_THEATER_TYPE_ID),
						num.getString(ConstantData.MOVIE_TICKET)));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		System.out.println(obList);
		return obList;
	}


}
