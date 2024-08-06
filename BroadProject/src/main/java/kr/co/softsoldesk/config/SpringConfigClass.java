package kr.co.softsoldesk.config;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringConfigClass implements WebApplicationInitializer{
//WebApplicationInitializer: web.xml ���� �ڹ� Ŭ������ ���� �� ���ø����̼� ����, ���ø����̼� ���� �� �ڵ� ����

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletAppContext.class);
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		//DispatcherServlet: ��û �߻� �� ��û�� ó���ϴ� ������ DispatcherServlet���� ����
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
		
		servlet.setLoadOnStartup(1);//������ �ε� ����, ������ ���۵� �� �ٷ� �ε�
		servlet.addMapping("/");//��� ��û�� �ش� ������ ó��
		
//=================================================================================================================
		
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);
		
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);
		//������ �߻��Ǵ� �̺�Ʈ ó�� ����
		
//=================================================================================================================
		
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		//�Ķ���� ���ڵ� ����
		filter.setInitParameter("encoding", "utf-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
		
//=================================================================================================================

		MultipartConfigElement multipartConfig = new MultipartConfigElement(null, 52428800, 524288000, 0);
		// null: ����� �Է� ������ �ӽñ���� ��Ĺ���� �����ϴ� ������ �ӽ� ������
		//���ε尡������ �뷮����: 52428800: 50mb(����), 524288000: 500mb(��ü), 0: ������ �Ӱ谪(�ڵ�����)
		servlet.setMultipartConfig(multipartConfig);
	}

	
}
