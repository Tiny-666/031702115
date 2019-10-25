package GAME;

public class water13 {
	public static void main(String[] args) {
		LOGIN Lo = new LOGIN();
		Lo.Login();
		while(true) {
			if(Lo.odk!=0) break;
		try {
			Thread.sleep(40);//40ms 1s=1000ms 表示一秒画几次
		}catch(Exception e) {
			e.printStackTrace();
		}}
		String TOKEN = Lo.Token;
		String NAME = Lo.name;
		int USER_ID = Lo.User_id;
		ZHUYE z = new ZHUYE();
		z.Zhuye(NAME, TOKEN,USER_ID);
	}
}
