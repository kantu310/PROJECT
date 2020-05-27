package function;

import java.util.HashMap;
import java.util.Map;

public class LoginFunction {
	public boolean rs = false;

	// 入力されたユーザーＩＤとパスワードを登録されたユーザー情報と照合し手判定するメソッド
	public boolean loginUser(HashMap<String, String> user, String id, String pass) {

		for (Map.Entry<String, String> entry : user.entrySet()) {

			if ((id.equals(entry.getKey()) && (pass.equals(entry.getValue())))) {
				rs = true;
			}
		}
		return rs;
	}
}
