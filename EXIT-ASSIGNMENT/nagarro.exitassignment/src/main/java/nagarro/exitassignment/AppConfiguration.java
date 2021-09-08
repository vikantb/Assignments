package nagarro.exitassignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nagarro.exitassignment.filter.AdminFilter;
import nagarro.exitassignment.filter.AuthorizationFilter;

/**
 * configurations like creating bean for filters
 * 
 * @author vikantbhati
 *
 */
@Configuration
public class AppConfiguration {

	@Autowired
	private AuthorizationFilter authorizationFilter;

	@Autowired
	private AdminFilter adminFilter;

	/**
	 * creating a bean for all API's for start with admin
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<AdminFilter> filterRegistrationBeanForAuth() {
		FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<AdminFilter>();
		registrationBean.setFilter(adminFilter);
		registrationBean.addUrlPatterns("/admin/*");

		return registrationBean;
	}

	/**
	 * creating a bean for all API's for start with api
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<AuthorizationFilter> filterRegistrationBeanForAdmin() {
		FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<AuthorizationFilter>();
		registrationBean.setFilter(authorizationFilter);
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}

}
