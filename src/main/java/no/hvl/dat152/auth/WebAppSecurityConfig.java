/**
 * 
 */
package no.hvl.dat152.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/**
 * 
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebAppSecurityConfig {

	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(authorize -> authorize
	        	.requestMatchers("/", "/login", "/css/**", "/h2-console/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	        	.loginPage("/login")
	        	.loginProcessingUrl("/login")
	        	.usernameParameter("username")
	        	.passwordParameter("password")
	        	.defaultSuccessUrl("/", true)
	        	.failureUrl("/login?error")
	        	.permitAll()
	        )
	        .logout(logout -> logout
	        	.logoutUrl("/logout")
	        	.logoutSuccessUrl("/login?logout")
	        	.invalidateHttpSession(true)
	        	.clearAuthentication(true)
	        	.deleteCookies("JSESSIONID")
	        	.permitAll()
	        )
	        .anonymous(anon -> anon.disable());

	    http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
	    http.headers(headers -> headers
	    	.frameOptions(frame -> frame.sameOrigin())
	    	.cacheControl(cache -> cache.disable())
	    );

	    return http.build();
	}
    
    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
	public UserDetailsService userDetailsService() {
    	
    	PasswordEncoder encoder = passwordEncoder();
		UserDetails user = User.withUsername("user")
			.password(encoder.encode("password"))
			.roles("USER")
			.build();
		
		UserDetails admin = User.withUsername("admin")
			.password(encoder.encode("password123"))
			.roles("ADMIN")
			.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
}
