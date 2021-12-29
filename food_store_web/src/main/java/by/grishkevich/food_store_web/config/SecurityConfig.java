package by.grishkevich.food_store_web.config;

import by.grishkevich.food_store_web.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@ComponentScan({"by.grishkevich.food_store_data.services"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().disable();
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/products", "/api/orders",
                        "/api/countries", "/api/categories", "/api/trademarks",
                        "/api/products/*", "/api/orders/*","/api/countries/*",
                        "/api/categories/*","/api/trademarks/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/products", "/api/orders",
                        "/api/countries", "/api/categories", "/api/trademarks").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/products/*", "/api/orders/*","/api/countries/*",
                        "/api/categories/*","/api/trademarks/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/products/**", "/api/orders/*","/api/countries/*",
                        "/api/categories/*","/api/trademarks/*").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET, "/api/products", "/api/orders").hasRole("USER")
                //.antMatchers(HttpMethod.POST, "/api/orders").hasRole("USER")
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.GET,"/api/products", "/api/orders").permitAll()
                .antMatchers(HttpMethod.POST,"/api/orders","/register").permitAll()
                .antMatchers("/api/docs", "/login", "/register", "/api/swagger-ui.html", "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        //configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
