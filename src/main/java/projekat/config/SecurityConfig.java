package projekat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("customer")
		.password("osd")
		.roles("customer")
		.and()
		.withUser("employee")
		.password("palacinke")
		.roles("employee")
		.and()
		.withUser("owner")
		.password("store")
		.roles("store owner");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.httpBasic();
		http.authorizeRequests()
			.antMatchers("/api/ingredients/**").hasRole("employee")
			.antMatchers("/api/reports/**").hasRole("store owner")
			.antMatchers("/api/pancakes/**").hasRole("customer")
			.antMatchers("/api/orders/**").hasRole("customer");
	}
	
	//zbog jednostavnosti ostavljen je ovaj password encoder iako ovo nije bas ispravno
	@SuppressWarnings("deprecation")
	@Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
