package parts;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import table.MovieTheater;

public class MovieTheaterParts {

	public static ObservableList<MovieTheater>getMovieTheater(){

		ObservableList<MovieTheater> obList = FXCollections.observableArrayList();

		String sql = "SELECT * FROM kan_system.movie_theater where MOVIE_THEATER_ID <> \"common\" ORDER BY movie_theater_name";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {

				obList.add(new MovieTheater(num.getString(ConstantData.MOVIE_THEATER_ID),
						num.getString(ConstantData.MOVIE_THEATER_TYPE_ID),
						num.getString(ConstantData.MOVIE_THEATER_NAME)));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		System.out.println(obList);
		return obList;
	}


}
