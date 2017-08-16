package org.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailServiceImpl userDetailService;
	@Autowired
	AuthFailure authFailure;
	@Autowired
	AuthSuccess authSuccess;
	@Autowired
	EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
	
	@Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder authBuilder ) throws Exception{
	  authBuilder.userDetailsService(userDetailService);
		
	}
	@Override
	public void configure(HttpSecurity security) throws Exception{
		security.csrf().disable()
		    .exceptionHandling()
		    .authenticationEntryPoint(entryPointUnauthorizedHandler)
		    .and()
			.formLogin()
			.successHandler(authSuccess)
			.failureHandler(authFailure)
			.and()
			.authorizeRequests()
			.antMatchers("/**")
			.permitAll();
	}
}
