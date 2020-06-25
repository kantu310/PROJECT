package application;

import function.LoginFunction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import parts.UserParts;


public class Controller extends Main{
	@FXML
	public TextField login_user_id;
	public TextField login_password;
	public Label login_error_message;
	Stage stage;
	Scene scene;


	public void onButtonLogin(Event event) throws Exception {

		UserParts s = new UserParts();
		LoginFunction u = new LoginFunction();

		//ユーザ情報を取得後、ログイン機能を呼び出してログイン結果を取得しますします
		boolean rs = u.loginUser(s.getUserInformation(), login_user_id.getText(), login_password.getText());

		if (rs == true) {
			//ログイン画面を閉じてメイン画面へ遷移
			System.out.println("ログイン成功だよ！");
			((Node) event.getSource()).getScene().getWindow().hide();
			Parent parent = FXMLLoader.load(getClass().getResource("Mainmenu.fxml"));
			stage = new Stage();

			scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Kan's Log System");
			stage.show();


		} else if (rs == false) {
			//エラーメッセージを表示
			System.out.println("ログイン失敗");
			login_error_message.setText("user_id or password is incorrect!!");
		}

	}



}

