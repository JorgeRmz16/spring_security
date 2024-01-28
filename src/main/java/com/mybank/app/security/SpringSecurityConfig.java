package com.mybank.app.security;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{// permisos
		return http.authorizeHttpRequests((authz) -> authz
						.requestMatchers(HttpMethod.GET,"/appi/users").permitAll()// ("/appi/users")// ruta para dar seguridad o dar permisos
						.requestMatchers(HttpMethod.POST,"/appi/users/register").permitAll()
						.anyRequest().authenticated())
						.csrf(config -> config.disable()) // desahabilitar csrf para evitaar vulnerabilidades
						.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // envio del token con datos del usuario para autenticar
						.build();																						//se maneja en el token toda la autenticacion
	}
}
