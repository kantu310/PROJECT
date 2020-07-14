package parts;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
import table.Movie;

public class MovieParts {

	public static ObservableList<Movie>getMovie(){

		ObservableList<Movie> obList = FXCollections.observableArrayList();

		String sql = "    	SELECT movie.MOVIE_ID,"
				+ "movie.MOVIE_DATE ,"
				+ "movie.MOVIE_TITLE,"
				+ "movie.MOVIE_IMAGE,"
				+ "movie.MOVIE_EVALUATION,"
				+ "movie.MOVIE_POPCORN,"
				+ "movie_theater.MOVIE_THEATER_NAME,"
				+ "movie_ticket.MOVIE_TICKET,"
				+ "movie.MOVIE_SEAT,"
				+ "movie.MOVIE_TIME \r\n" +
				"FROM kan_system.movie \r\n" +
				"JOIN movie_theater ON movie.MOVIE_THEATER_ID = movie_theater.MOVIE_THEATER_ID\r\n" +
				"JOIN movie_ticket ON movie.MOVIE_TICKET_ID = movie_ticket.MOVIE_TICKET_ID\r\n" +
				"WHERE USER_ID = \""+ ConstantData.getLoginUserID()+"\"\r\n" +
				"ORDER BY MOVIE_DATE DESC;";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {

				obList.add(new Movie(num.getInt(ConstantData.MOVIE_ID),
						num.getDate(ConstantData.MOVIE_DATE),
						num.getString(ConstantData.MOVIE_TITLE),
						num.getBlob(ConstantData.MOVIE_IMAGE),
						num.getInt(ConstantData.MOVIE_EVALUATION),
						num.getInt(ConstantData.MOVIE_POPCORN),
						num.getString(ConstantData.MOVIE_THEATER_NAME),
						num.getString(ConstantData.MOVIE_TICKET),
						num.getString(ConstantData.MOVIE_SEAT),
						num.getInt(ConstantData.MOVIE_TIME)));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		System.out.println(obList);
		return obList;
	}

	public static void setMovie(Data date, String title,Blob image, int eval, int pop, String seat, int time) {


		String sql = "update kan_system.movie "
				+ "set movie_date =" + date
				+ "movie_title =" + title
				+"movie_image =" + image
				+"movie_evaluation ="+ eval
				+"movie_popcorn =" + pop
				+"movie_seat =" + seat
				+"movie_time =" + time;

		int num = 0;

		try {
			num = SqlConnectionParts.sqlCreate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		}

		System.out.println(num);
	}

}
