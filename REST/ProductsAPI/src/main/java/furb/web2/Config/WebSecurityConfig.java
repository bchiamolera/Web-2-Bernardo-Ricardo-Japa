package furb.web2.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import furb.web2.Config.JwtAuthenticationEntryPoint;
import furb.web2.Config.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtRequestFilter jwtRequestFilter;
	private final UserDetailsService jwtUserDetailsService;

	@Autowired
	public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
							 JwtRequestFilter jwtRequestFilter,
							 UserDetailsService jwtUserDetailsService) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtRequestFilter = jwtRequestFilter;
		this.jwtUserDetailsService = jwtUserDetailsService;
	}
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    		.csrf((csrf) -> csrf
    			.disable()
    		)
    		.authorizeHttpRequests((authz) -> authz
    			.requestMatchers("/login", "/logout", "/register").permitAll()
    			.requestMatchers("/produtos", "/categorias").hasAnyRole("USER", "ADMIN")
    			.requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN")
    			.requestMatchers(HttpMethod.PUT).hasAnyRole("ADMIN")
    			.requestMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
    			.anyRequest().authenticated()
    		)
    		.exceptionHandling((exception) -> exception
    			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
    		)
            .sessionManagement((session) -> session
            	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
    	
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
