package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import data.ConstantData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import parts.BookParts;
import table.Book;

public class BookController extends MainmenuController {
	public static ObservableList<Book> bookList = FXCollections.observableArrayList();
	public int id;
	public String title;
	public Date date;
	public Image image;
	public String topic;
	public  File selectedFile = null;
	public  InputStream is = null;
	public  FileInputStream fis = null;
	byte[] r = null;

    @FXML
    private AnchorPane moviepane;

    @FXML
    private FlowPane bookFlowPane;

    @FXML
    private ImageView BookImage;

    @FXML
    private Text txtBookTitle;

    @FXML
    private Text txtBookDate;

    @FXML
    private WebView bookTopic;

    @FXML
    private HTMLEditor bookTopicEdit;

    @FXML
    private ImageView bookEdit;

    @FXML
    private TextField regBookTitle;

    @FXML
    private DatePicker regBookDate;

    @FXML
    private Button regBookImageBtn;

    @FXML
    private ImageView regBookImage;

    @FXML
    private Button btnReg;

    @FXML
    private Button newRegBtn;

    @FXML
    private Button btnRegNew;

    @FXML
    private Button deleteBtn;



@FXML
private void initialize() throws SQLException {
	displayBook();




}

@FXML
private void displayBook() throws SQLException {
	bookList = BookParts.getBook();

	for (Book book : bookList) {
			if(book.book_image == null) {
				Image img = new Image("application/image/image_file_24px.png");
				ImageView imgv = new ImageView(img);
				imgv.setFitHeight(120);
				imgv.setFitWidth(90);
				bookFlowPane.getChildren().addAll(imgv);
				imgv.setOnMouseClicked(event -> bookDetailDisplay(book.book_id,book.book_title,book.book_date,img,book.book_topic,book.book_image));
			}else {
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
				imgv.setFitHeight(120);
				imgv.setFitWidth(90);
				bookFlowPane.getChildren().addAll(imgv);
				imgv.setOnMouseClicked(event -> bookDetailDisplay(book.book_id,book.book_title,book.book_date,img,book.book_topic,book.book_image));
			} catch (IOException e) {
				e.printStackTrace();
				r = null;
			}
		}
	}
}

@FXML
private void bookDetailDisplay(int id,String title, Date date,Image img,String topic,java.sql.Blob blb) {
	regBookTitle.setVisible(false);
	regBookDate.setVisible(false);
	regBookImageBtn.setVisible(false);
	bookTopicEdit.setVisible(false);
	regBookImage.setVisible(false);
	btnReg.setVisible(false);
	btnRegNew.setVisible(false);
	deleteBtn.setVisible(false);
	this.id = id;
	this.title = title;
	this.date = date;
	this.image = img;
	this.topic = topic;
	try {
		if(!(blb == null)) {
		this.is = blb.getBinaryStream();
		}
	} catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	txtBookTitle.setVisible(true);
	txtBookDate.setVisible(true);
	BookImage.setVisible(true);
	bookTopic.setVisible(true);
	bookEdit.setVisible(true);

	txtBookTitle.setText(title);
	txtBookDate.setText("登録日：" + String.valueOf(date));
	BookImage.setImage(img);
	BookImage.setFitHeight(368);
	BookImage.setFitWidth(251);
	bookTopic.getEngine().loadContent(topic);

}

@FXML
void onBookEdit(MouseEvent event) {

	regBookTitle.setText(title);
	 LocalDate loDate = LocalDate.parse(String.valueOf(date));
	regBookDate.setValue(loDate);
	regBookImage.setImage(image);
	bookTopicEdit.setHtmlText(topic);

	txtBookTitle.setVisible(false);
	txtBookDate.setVisible(false);
	BookImage.setVisible(false);
	bookTopic.setVisible(false);
	bookEdit.setVisible(false);
	btnRegNew.setVisible(false);
	regBookTitle.setVisible(true);
	regBookDate.setVisible(true);
	regBookImageBtn.setVisible(true);
	bookTopicEdit.setVisible(true);
	regBookImage.setVisible(true);
	btnReg.setVisible(true);
	deleteBtn.setVisible(true);

}

@FXML
void onregBookImageBtn(ActionEvent event) {
	FileChooser fc = new FileChooser();
	fc.setTitle("ファイルを開く");
	fc.getExtensionFilters().addAll(new  ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
	selectedFile = fc.showOpenDialog(stage);
	try {
		fis = new FileInputStream(selectedFile);
		Image img = new Image(fis);
		regBookImage.setImage(img);
		regBookImage.setFitHeight(368);
		regBookImage.setFitWidth(251);

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
}

@FXML
void onBtnReg(ActionEvent event) {
 	Alert dialog = new Alert(AlertType.NONE,"登録しますか？",ButtonType.YES,ButtonType.NO);
	dialog.setTitle("確認");
	Alert dialog2 = new Alert(AlertType.NONE, "入力されていない項目があります", ButtonType.OK);
	dialog2.setTitle("エラー");
	Alert dialog3 = new Alert(AlertType.NONE,"登録しました。",ButtonType.OK);
	dialog3.setTitle("完了");

	if(regBookTitle.getText().isEmpty()||
			regBookDate.getValue() == null ) {
		dialog2.showAndWait();
	}else {
		Optional<ButtonType >diaRs =dialog.showAndWait();
		if(diaRs.get() == ButtonType.YES) {
			Connection conn =null;
			try {
				conn = DriverManager.getConnection(ConstantData.MYSQL_URL, ConstantData.MYSQL_USER,
						ConstantData.MYSQL_PASSWORD);

				String sql = "update kan_system.book "
						+ "set book_date = ?, "
						+ "book_title = ?, "
						+ "book_topic = ?, "
						+ "book_image = ?"
						+ "where book_id = ?";

				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setString(1,regBookDate.getValue().toString());
				stmt.setString(2, regBookTitle.getText());
				stmt.setString(3, bookTopicEdit.getHtmlText());
				if(fis == null) {
					stmt.setBinaryStream(4,is);
				}else {
					fis = new FileInputStream(selectedFile);
					stmt.setBinaryStream(4, (InputStream)fis,(int)selectedFile.length());
				}

				stmt.setInt(5, id);
				int r = stmt.executeUpdate();
				dialog3.showAndWait();
				AnchorPane pane;
				pane = FXMLLoader.load(getClass().getResource("Book.fxml"));
				moviepane.getChildren().setAll(pane);

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				Alert dialog4 = new Alert(AlertType.NONE, "画像サイズが登録上限を超えています", ButtonType.OK);
				dialog4.setTitle("エラー");
				dialog4.showAndWait();
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}else if(diaRs.get() == ButtonType.NO){
			dialog.close();
		}
	}
}

@FXML
void onNewRegBtn(ActionEvent event) {
	txtBookTitle.setVisible(false);
	txtBookDate.setVisible(false);
	BookImage.setVisible(false);
	bookTopic.setVisible(false);
	bookEdit.setVisible(false);
	deleteBtn.setVisible(false);

	regBookTitle.setText("");
	regBookDate.setValue(null);
	Image img = new Image("application/image/image_file_24px.png");
	regBookImage.setImage(img);
	bookTopicEdit.setHtmlText("");

	regBookTitle.setVisible(true);
	regBookDate.setVisible(true);
	regBookImageBtn.setVisible(true);
	bookTopicEdit.setVisible(true);
	regBookImage.setVisible(true);
	btnRegNew.setVisible(true);
}

@FXML
void onBtnRegNew(ActionEvent event) {
	Alert dialog = new Alert(AlertType.NONE,"登録しますか？",ButtonType.YES,ButtonType.NO);
	dialog.setTitle("確認");
	Alert dialog2 = new Alert(AlertType.NONE, "入力されていない項目があります", ButtonType.OK);
	dialog2.setTitle("エラー");
	Alert dialog3 = new Alert(AlertType.NONE,"登録しました。",ButtonType.OK);
	dialog3.setTitle("完了");

	if(regBookTitle.getText().isEmpty()||
			regBookDate.getValue() == null ) {
		dialog2.showAndWait();
	}else {
		Optional<ButtonType >diaRs =dialog.showAndWait();
		if(diaRs.get() == ButtonType.YES) {
			Connection conn =null;
			try {
				conn = DriverManager.getConnection(ConstantData.MYSQL_URL, ConstantData.MYSQL_USER,
						ConstantData.MYSQL_PASSWORD);

				String sql = "insert into  kan_system.book (book_date, book_title, book_image, book_topic, user_id) values (?,?,?,?,?)";


				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setString(1,regBookDate.getValue().toString());
				stmt.setString(2, regBookTitle.getText());
				if(!(fis == null)) {
					fis = new FileInputStream(selectedFile);
					stmt.setBinaryStream(3, (InputStream)fis,(int)selectedFile.length());
				}else {
					stmt.setBytes(3, null);
				}
				stmt.setString(4, bookTopicEdit.getHtmlText());
				stmt.setString(5, ConstantData.loginUserID);

				int r = stmt.executeUpdate();
				dialog3.showAndWait();
				AnchorPane pane;
				pane = FXMLLoader.load(getClass().getResource("Book.fxml"));
				moviepane.getChildren().setAll(pane);

				} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				Alert dialog4 = new Alert(AlertType.NONE, "画像サイズが登録上限を超えています", ButtonType.OK);
				dialog4.setTitle("エラー");
				dialog4.showAndWait();
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}else if(diaRs.get() == ButtonType.NO){
			dialog.close();
		}
	}
}

@FXML
void onDeleteBtn(ActionEvent event) throws IOException {
	Alert dialog = new Alert(AlertType.NONE,"削除しますか？",ButtonType.YES,ButtonType.NO);
	dialog.setTitle("確認");
	Optional<ButtonType >diaRs =dialog.showAndWait();
	if(diaRs.get() == ButtonType.YES) {
		BookParts.deleteBook(id);
    	Alert dialog3 = new Alert(AlertType.NONE,"削除しました",ButtonType.OK);
    	dialog3.setTitle("完了");
    	dialog3.showAndWait();
		AnchorPane pane;
		pane = FXMLLoader.load(getClass().getResource("Book.fxml"));
		moviepane.getChildren().setAll(pane);
		}

}
}

