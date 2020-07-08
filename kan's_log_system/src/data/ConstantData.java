package data;

/**
 * @author sato
 *【定数クラス】
 *なんかいろいろ定数を定義したクラス
 */

public  class ConstantData {

		//ログイン中のuserID
		public static String loginUserID;

		public static String getLoginUserID() {
			return loginUserID;
		}
		public static void setLoginUserID(String loginUserID) {
			ConstantData.loginUserID = loginUserID;
		}


		public static long currentDebt;
		public static long currentFixedMoney;

		public static long getCurrentDebt() {
			return currentDebt;
		}
		public static void setCurrentDebt(long currentDebt) {
			ConstantData.currentDebt = currentDebt;
		}
		public static long getCurrentFixedMoney() {
			return currentFixedMoney;
		}
		public static void setCurrentFixedMoney(long currentFixedMoney) {
			ConstantData.currentFixedMoney = currentFixedMoney;
		}


		//MYSQLに接続するための情報
		public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/kan_system?characterEncoding=UTF-8&serverTimezone=JST";
		public static final String MYSQL_USER = "root";
		public static final String MYSQL_PASSWORD = "5890336kan";

		//【user】ユーザー情報管理テーブルのカラム
		public static final String USER_ID = "USER_ID";
		public static final String PASSWORD = "PASSWORD";
		public static final String CREATE_TIME = "CREATE_TIME";

		// 【repayment_balance】返済残高管理テーブルのカラム
		public static final String LOAN_DATE = "LOAN_DATE";
		public static final String REPAID_AMOUNT = "REPAID_AMOUNT";
		public static final String DEPOSIT_AMOUNT = "DEPOSIT_AMOUNT";
		public static final String BALANCE = "BALANCE";

		//【debt】借金管理テーブルのカラム
		public static final String DEBT = "DEBT";
		public static final String DEBT_BALANCE="DEBT_BALANCE";
		public static final String FIXED_MONEY = "FIXED_MONEY";

		//【movie】映画鑑賞テーブルのカラム
		public static final String MOVIE_ID = "MOVIE_ID";
		public static final String MOVIE_DATE = "MOVIE_DATE";
		public static final String MOVIE_TITLE = "MOVIE_TITLE";
		public static final String MOVIE_IMAGE = "MOVIE_IMAGE";
		public static final String MOVIE_EVALUATION = "MOVIE_EVALUATION";
		public static final String MOVIE_POPCORN = "MOVIE_POPCORN";
		public static final String MOVIE_THEATER_ID ="MOVIE_THEATER_ID";
		public static final String MOVIE_TICKET_ID ="MOVIE_TICKET_ID";
		public static final String MOVIE_SEAT="MOVIE_SEAT";
		public static final String MOVIE_TIME="MOVIE_TIME";

		//【movie_theater】映画館テーブルのカラム
		public static final String MOVIE_THEATER_NAME="MOVIE_THEATER_NAME";

		//【movie_ticket】//映画チケットテーブルのカラム
		public static final String MOVIE_TICKET="MOVIE_TICKET";

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
		public static String getLoanDate() {
			return LOAN_DATE;
		}
		public static String getRepaidAmount() {
			return REPAID_AMOUNT;
		}
		public static String getDepositAmount() {
			return DEPOSIT_AMOUNT;
		}
		public static String getBalance() {
			return BALANCE;
		}
		public static String getDebt() {
			return DEBT;
		}
		public static String getDebtBalance() {
			return DEBT_BALANCE;
		}
		public static String getMovieId() {
			return MOVIE_ID;
		}
		public static String getMovieDate() {
			return MOVIE_DATE;
		}
		public static String getMovieTitle() {
			return MOVIE_TITLE;
		}
		public static String getMovieImage() {
			return MOVIE_IMAGE;
		}
		public static String getMovieEvaluation() {
			return MOVIE_EVALUATION;
		}
		public static String getMovieTheaterId() {
			return MOVIE_THEATER_ID;
		}
		public static String getMovieTicketId() {
			return MOVIE_TICKET_ID;
		}
		public static String getMovieSeat() {
			return MOVIE_SEAT;
		}
		public static String getMovieTime() {
			return MOVIE_TIME;
		}
		public static String getMovieTheaterName() {
			return MOVIE_THEATER_NAME;
		}
		public static String getMovieTicket() {
			return MOVIE_TICKET;
		}



}
