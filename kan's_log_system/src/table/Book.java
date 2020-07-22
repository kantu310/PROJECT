package table;

import java.sql.Blob;
import java.sql.Date;

public class Book {

	public int book_id;//本ＩＤ
	public Date book_date;//登録日付
	public String book_title;//本タイトル
	public String book_topic;//トピック
	public Blob book_image;//本表紙

	public Book(int book_id, Date book_date, String book_title, String book_topic, Blob book_image) {
		super();
		this.book_id = book_id;
		this.book_date = book_date;
		this.book_title = book_title;
		this.book_topic = book_topic;
		this.book_image = book_image;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public Date getBook_date() {
		return book_date;
	}

	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getBook_topic() {
		return book_topic;
	}

	public void setBook_topic(String book_topic) {
		this.book_topic = book_topic;
	}

	public Blob getBook_image() {
		return book_image;
	}

	public void setBook_image(Blob book_image) {
		this.book_image = book_image;
	}






}
