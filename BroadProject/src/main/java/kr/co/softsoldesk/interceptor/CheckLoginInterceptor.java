package kr.co.softsoldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{

	//�α��� ���� �Ǵ��ϴ� UserBean
	private UserBean loginUserBean;
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
	
		this.loginUserBean = loginUserBean;
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(loginUserBean.isUserLogin()==false) {//�α׾ƿ�����
			String contextPath = request.getContextPath();//��Ʈ���
			response.sendRedirect(contextPath+"/user/not_login");
			return false;
		}
		
		return true;//�α��� ����
	}

	
}
