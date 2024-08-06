package kr.co.softsoldesk.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.softsoldesk.beans.UserBean;

public interface UserMapper {

	@Select("select user_id from user_table where user_id = #{user_id}")
	String checkUserIdExist(String user_id);
	
	@Insert("insert into user_table values(user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addUser(UserBean joinUserBean);
	
	@Select("select user_idx, user_name from user_table where user_id=#{user_id} and user_pw = #{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	//������ ȸ���� ������ ��������
	@Select("select user_id, user_name from user_table where user_idx = #{user_id}")
	UserBean getModifyUserInfo(int user_idx);
	
	//ȸ�� ����
	@Update("update user_table set user_pw = #{user_pw} where user_idx = #{user_idx}")
	void modifyUserInfo(UserBean modifyUserBean);
}
