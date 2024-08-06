package kr.co.softsoldesk.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserBean {

	private int user_idx;
	
	@Size(min=2, max=5)
	@Pattern(regexp = "[가-힣]*")//ﾇﾑｱﾛ
	private String user_name;
	
	@Size(min=4, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_id;
	
	@Size(min=4, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw;
	
	//DBｿ｡ ﾀﾖｴﾂ ｼﾓｼｺﾀｻ ｿﾅｰﾜｿﾂ ｰﾍ, ｸ� ｼﾓｼｺﾀﾌ ﾀﾏﾄ｡ﾇﾒ ﾇﾊｿ莇ﾂ ｾｽ!!
	@Size(min=4, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw2;//ｺﾐｹ｣ ﾈｮﾀﾎﾀｻ ﾀｧﾇﾘ ｻﾎ ｸｸｵ�. DBｿ｡ｴﾂ ｾﾙ->DBｿ｡ ｾﾈｳﾖﾀｸｸ� ｾﾈﾅﾍﾁ�

	private boolean userIdExist;//ｾﾆﾀﾌｵ� ﾁﾟｺｹﾈｮﾀﾎ ｿｩｺﾎ
	
	private boolean userLogin;//ｷﾎｱﾗﾀﾎｻﾂ
	
	public UserBean() {
		
		this.userIdExist=false;//underIdExistﾀﾇ ﾃﾊｱ�ｰｪﾀｺ false
		this.userLogin=false;//ﾃﾊｱ�ｰｪ
	}
	
	public boolean isUserLogin() {
		return userLogin;
	}
	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}

	
	public boolean isUserIdExist() {
		return userIdExist;
	}
	public void setUserIdExist(boolean userIdExist) {
		this.userIdExist = userIdExist;
	}
	public String getUser_pw2() {
		return user_pw2;
	}
	public void setUser_pw2(String user_pw2) {
		this.user_pw2 = user_pw2;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	
}
