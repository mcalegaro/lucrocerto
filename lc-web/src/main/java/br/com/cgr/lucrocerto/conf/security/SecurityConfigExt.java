package br.com.cgr.lucrocerto.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.cgr.lucrocerto.service.LCPasswordEncoder;
import br.com.cgr.lucrocerto.service.providers.LCAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "br.com.cgr.lucrocerto.service.providers")
public class SecurityConfigExt extends WebSecurityConfigurerAdapter {

	@Autowired
	private LCPasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManagerBuilder auth;
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return auth.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(){
		LCAuthenticationProvider lcAuthenticationProvider = new LCAuthenticationProvider();
		lcAuthenticationProvider.setUserDetailsService(userDetailsService);
		lcAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return lcAuthenticationProvider;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		auth.authenticationProvider(authenticationProvider());
		// new PasswordEncoderImpl());
		// auth.inMemoryAuthentication().withUser("user").password("password")
		// .roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**", "/showSignup", "/about", "/signup", "/login/**", "/confirm" , "/awatingConfirmation")
				.permitAll()

				.anyRequest().authenticated()

				.and().formLogin().loginPage("/login").permitAll()

				.and().httpBasic()

				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

	}

}