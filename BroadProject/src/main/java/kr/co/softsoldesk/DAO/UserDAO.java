package kr.co.softsoldesk.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.mapper.UserMapper;

@Repository
public class UserDAO {

	@Autowired
	private UserMapper userMapper;
	//ID�ߺ�Ȯ��
	public String checkUserIdExist(String user_id) {
		
		return userMapper.checkUserIdExist(user_id);
	}
	//ȸ������
	public void addUser(UserBean joinUserBean) {
		
		userMapper.addUser(joinUserBean);
		
	}
	//�α���
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		
		return userMapper.getLoginUserInfo(tempLoginUserBean);
	}
	 //������ ȸ�� ���� ��������
    public UserBean getModifyUserInfo(int user_idx) {
       
       return userMapper.getModifyUserInfo(user_idx);
    }
    //ȸ������-DB
    public void modifyUserInfo(UserBean modifyUserBean) {
    	
    	userMapper.modifyUserInfo(modifyUserBean); //userBean�� ���� �ִ� idx�� where(����)���� ��
    }
}
