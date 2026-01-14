package org.example.soccer_manager.config;

import org.example.soccer_manager.service.impl.UserInforDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        @Autowired
        private UserInforDetailService userDetailsService;

        @Bean
        public PasswordEncoder passwordEncoder() {

                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
                authenticationProvider.setPasswordEncoder(passwordEncoder());
                return authenticationProvider;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf((csrf) -> csrf.disable())
                                // Config các đường dẫn không cần đăng nhập
                                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                                                .requestMatchers("/login", "/logoutSuccessful", "/403", "/css/**",
                                                                "/js/**", "/images/**", "/api/**", "/uploads/**")
                                                .permitAll()
                                                .requestMatchers("/logout", "/soccers/**").authenticated())
                                // Cấu hình lại form login
                                .formLogin((formLogin) -> formLogin
                                                .usernameParameter("username")
                                                .passwordParameter("password")
                                                .loginPage("/login")
                                                .failureUrl("/login?error=true")
                                                .loginProcessingUrl("/login")
                                                .defaultSuccessUrl("/soccers"))
                                .logout((logout) -> logout.deleteCookies("remove")
                                                .invalidateHttpSession(false)
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/logoutSuccessful"))
                                // Cấu hình lỗi 403 - Truy cập bị từ chối
                                .exceptionHandling((exception) -> exception
                                                .accessDeniedPage("/403")

                                );
                return http.build();
        }

}