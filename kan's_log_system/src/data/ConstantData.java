package data;

/**
 * @author sato
 *【定数クラス】
 *なんかいろいろ定数を定義したクラス
 */

public  class ConstantData {
		//MYSQLに接続するための情報
		public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/kan_system?characterEncoding=UTF-8&serverTimezone=JST";
		public static final String MYSQL_USER = "root";
		public static final String MYSQL_PASSWORD = "5890336kan";

		//【user】ユーザー情報管理テーブルのカラム
		public static final String USER_ID = "USER_ID";
		public static final String PASSWORD = "PASSWORD";
		public static final String CREATE_TIME = "CREATE_TIME";





		public static String getMysqlUrl() {
			return MYSQL_URL;
		}
		public static String getMysqlUser() {
			return MYSQL_USER;
		}
		public static String getMysqlpasswprd() {
			return MYSQL_PASSWORD;
		}
		public static String getUserId() {
			return USER_ID;
		}
		public static String getPassword() {
			return PASSWORD;
		}
		public static String getCreateTime() {
			return CREATE_TIME;
		}


}
