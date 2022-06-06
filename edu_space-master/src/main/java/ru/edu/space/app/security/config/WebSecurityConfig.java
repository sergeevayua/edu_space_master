package ru.edu.space.app.security.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import ru.edu.space.app.security.handler.JWTAccessDeniedHandler;
import ru.edu.space.app.security.handler.JWTAuthenticationEntryPoint;
import ru.edu.space.app.security.jwt.JWTConfig;
import ru.edu.space.app.security.jwt.TokenProvider;

/**
 * @author agerus
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CorsFilter corsFilter;

    public WebSecurityConfig(TokenProvider tokenProvider,
                             JWTAccessDeniedHandler jwtAccessDeniedHandler,
                             JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint, CorsFilter corsFilter) {
        this.tokenProvider = tokenProvider;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.corsFilter = corsFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/registration/registration-confirm/**").permitAll()
                .antMatchers("/api/student").hasAuthority("USER")
                .antMatchers("/api/teacher").hasAuthority("TEACHER")
                .antMatchers("/api/user/**").hasAuthority("USER")
                .antMatchers("/api/role").hasAuthority("SUPERADMIN")
                .antMatchers("/reset-password").hasAuthority("SUPERADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(getJWTConfig());
    }

    @Value("12")
    private int strength;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(strength);
    }

    private JWTConfig getJWTConfig() {
        return new JWTConfig(tokenProvider);
    }
}
