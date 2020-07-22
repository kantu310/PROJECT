package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import parts.BookParts;
import table.Book;

public class BookController {
	public static ObservableList<Book> bookList = FXCollections.observableArrayList();


    @FXML
    private AnchorPane moviepane;

    @FXML
    private TableView<Book> book_view;

    @FXML
    private TableColumn<Book, String> bookTitleCal;

    @FXML
    private TableColumn<?, ?> bookImageCal;

    @FXML
    private TableColumn<Book, Date> bookDateCal;



@FXML
private void initialize() throws SQLException {
	bookList = BookParts.getBook();//movieテーブルから情報を取得
	displayBook();

}

@FXML
private void displayBook() throws SQLException {
	bookTitleCal.setCellValueFactory(new PropertyValueFactory<>("book_title"));
	bookDateCal.setCellValueFactory(new PropertyValueFactory<>("book_date"));
	book_view.setItems(bookList);

	byte[] r = null;

	for (Book book : bookList) {



	InputStream is = book.book_image.getBinaryStream();
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
		bookImageCal.setCellValueFactory(new ima);
	} catch (IOException e) {
		e.printStackTrace();
		r = null;
	}
	}
}
}

