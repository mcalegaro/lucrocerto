package br.com.cgr.lucrocerto.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages="br.com.cgr.lucrocerto.service.impl")
public class SecurityConfigExt extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	// ---
	@Autowired
	private AuthenticationManagerBuilder auth;

	// ---
	@Bean(name="myAuthenticationManager")
    public AuthenticationManager authenticationManager() throws Exception {
        return auth.build();
    }
	 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(
				passwordEncoder);
		// new PasswordEncoderImpl());
		// auth.inMemoryAuthentication().withUser("user").password("password")
		// .roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**", "/showSignup", "/about",
						"/signup", "/login/**").permitAll()

				.anyRequest().authenticated()

				.and().formLogin().loginPage("/login").permitAll()

				.and().httpBasic()

				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

	}

}