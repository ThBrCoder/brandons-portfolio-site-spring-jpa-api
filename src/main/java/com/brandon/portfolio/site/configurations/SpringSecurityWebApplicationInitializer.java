package com.brandon.portfolio.site.configurations;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{

	/*
	 * 	It’s very important you have AbstractSecurityWebApplicationInitializer as part of your project, 
	 *  otherwise the Spring Security Filters will not be activated/kick in.
	 *  No additional code needed here.
	 */
}
