package io.github.joyoungc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().ignoringAntMatchers("/h2console/**")
				.and()
			.headers().frameOptions().sameOrigin()
				.and()
			.authorizeRequests()
				.antMatchers("/webjars/**","/h2console/**","/", "/home").permitAll()
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
	// TODO - DB로 연동예정
	@Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	// auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		auth.inMemoryAuthentication()
			.withUser("joyoungc").password("password").roles("USER");
    }
	
	/**
	 * Password 암호화 Encoder
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
    

}
