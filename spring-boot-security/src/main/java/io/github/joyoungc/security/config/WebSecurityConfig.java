package io.github.joyoungc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().antMatchers("/webjars/**").permitAll()
			.and()
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
    
	// 한명의 USER 등록
	@Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	// auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		auth.inMemoryAuthentication()
			.withUser("joyoungc").password("password").roles("USER");
    }
    

}
