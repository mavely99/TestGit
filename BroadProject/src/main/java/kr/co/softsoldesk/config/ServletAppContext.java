package kr.co.softsoldesk.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.softsoldesk.interceptor.CheckLoginInterceptor;
import kr.co.softsoldesk.interceptor.CheckWriterInterceptor;
import kr.co.softsoldesk.interceptor.TopMenuInterceptor;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.BoardService;
import kr.co.softsoldesk.service.TopMenuService;

@Configuration
@EnableWebMvc
@ComponentScan("kr.co.softsoldesk.controller")
public class ServletAppContext implements WebMvcConfigurer{
//WebMvcConfigurer: Spring MVC ﾇﾁｷﾎﾁｧﾆｮ ｼｳﾁ､ ﾀﾎﾅﾍﾆ菎ﾌｽｺ

	@Resource(name="loginUserBean")
	private UserBean loginUserBean;
	
	@Autowired
	TopMenuService topMenuService;
	
	@Autowired
	BoardService boardService;
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
	//Controllerﾀﾇ ｸﾞｼｭｵ蟆｡ ｹﾝﾈｯﾇﾏｴﾂ jsp ﾀﾌｸｧ ｾﾕ ｵﾚｿ｡ ｰ豺ﾎｿﾍ ﾈｮﾀ蠡ﾚｸｦ ｺﾙｿｩﾁﾖｵｵｷﾏ ｼｳﾁ､
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	//ﾁ､ﾀ� ﾆﾄﾀﾏ ｰ豺ﾎ ｸﾅﾇﾎ
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/resources/");
	}
	//ﾀﾎﾅﾍｼﾁﾅﾍ ｵ﨧ﾏ

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		WebMvcConfigurer.super.addInterceptors(registry);
		
		TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserBean );
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
		reg1.addPathPatterns("/**");//ｸ� ｿ菘ｻ ﾁﾖｼﾒｿ｡ ｵｿﾀﾛ
		
		CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
		InterceptorRegistration reg2 = registry.addInterceptor(checkLoginInterceptor);
		reg2.addPathPatterns("/user/modify", "/user/logout", "/board/*");
		                                                      //ｺﾎｱﾗﾀﾎ ｻﾂｿ｡ｼｭ ｰﾔｽﾃﾆﾇ ﾁ｢ｼﾓ ｸｷﾀｽ
		reg2.excludePathPatterns("/board/main");//ｿｹｿﾜﾃｳｸｮ
		
		CheckWriterInterceptor checkWriterInterceptor = new CheckWriterInterceptor(loginUserBean, boardService);
	      InterceptorRegistration reg3 = registry.addInterceptor(checkWriterInterceptor);
	      reg3.addPathPatterns("/board/modify");
	}
	
	//ｿ｡ｷｯｸﾞｽﾃﾁ� ｵ﨧ﾏ
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		
		return new PropertySourcesPlaceholderConfigurer();
	}//ﾇﾁｷﾎﾆﾛﾆｼ ﾆﾄﾀﾏ ｷﾎｵ�
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setBasename("/WEB-INF/properties/error_message");
		
		return res;
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		
		return new StandardServletMultipartResolver();
	}
}
