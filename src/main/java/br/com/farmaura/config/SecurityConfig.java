package br.com.farmaura.config;

import br.com.farmaura.repository.UsuarioRepository;
import br.com.farmaura.security.EstadoContaFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UsuarioRepository usuarioRepository;

    public SecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        // Console H2
                        .requestMatchers("/h2-console/**").permitAll()

                        // Recursos estáticos e páginas públicas
                        .requestMatchers(
                                "/",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/favicon.ico",
                                "/login",
                                "/auth/login",
                                "/auth/cadastrar"
                        ).permitAll()

                        // Consulta pública de produtos
                        .requestMatchers(HttpMethod.GET, "/api/produtos", "/api/produtos/**").permitAll()

                        // Gestão de produtos (ADMIN e FARMACEUTICO)
                        .requestMatchers("/api/produtos", "/api/produtos/**")
                        .hasAnyRole("ADMIN", "FARMACEUTICO")

                        // Gestão de usuários (somente ADMIN)
                        .requestMatchers("/api/usuarios", "/api/usuarios/**")
                        .hasRole("ADMIN")

                        // Demais páginas exigem autenticação
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/?loginErro=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )

                .addFilterAfter(
                        new EstadoContaFilter(usuarioRepository),
                        UsernamePasswordAuthenticationFilter.class
                )

                // Ignora CSRF para console H2 e APIs REST
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/api/**")
                )

                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                );

        return http.build();
    }
}
