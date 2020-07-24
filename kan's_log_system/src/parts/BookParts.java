package parts;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import table.Book;

public class BookParts {

	public static ObservableList<Book>getBook(){

		ObservableList<Book> obList = FXCollections.observableArrayList();

		String sql = "SELECT BOOK_ID, BOOK_DATE, BOOK_TITLE, BOOK_TOPIC, BOOK_IMAGE FROM kan_system.book where USER_ID = \""+ConstantData.getLoginUserID()+"\"ORDER BY BOOK_DATE DESC;";

		ResultSet num;
		try {
			num = SqlConnectionParts.sqlConnectionQuery(sql);
			while(num.next()) {

				obList.add(new table.Book(num.getInt(ConstantData.BOOK_ID),
						num.getDate(ConstantData.BOOK_DATE),
						num.getString(ConstantData.BOOK_TITLE),
						num.getString(ConstantData.BOOK_TOPIC),
						num.getBlob(ConstantData.BOOK_IMAGE)));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		System.out.println(obList);
		return obList;
	}

	public static void deleteBook(int book_id) {

		String sql  = "DELETE FROM kan_system.book where book_id =" + book_id;
		System.out.println(sql);
		int num = 0;

		try {
			num = SqlConnectionParts.sqlCreate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		System.out.println(num);

	}

}
