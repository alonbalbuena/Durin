package com.dawes.seguridad;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import com.dawes.servicios.UsuariosServicios;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

	// permitimos el uso de medios estaticos como paginas de estilos fuentes y JS
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/css/**", "/icons/**", "/js/**", "/img/**");
	}

	// AUTENTICACION
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder codificador,
			UsuariosServicios servicioUsuario)
			throws Exception {
		// le pasamos el loadUserByUsername implementado en el servicioUsuario
		// autenticacion con los usuarios usados en la BBDD
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(servicioUsuario)
				.passwordEncoder(codificador).and()
				.build();
		// autenticacion con el usuario definido aqui
		// auth.inMemoryAuthentication().withUser("blah").password("blah").roles("USUARIO");
	}

	// AUTORIZACION

	// permisos dados a peticione http
	// si nos da error 403(estamos correctamente autenticado pero no tenemos el ROL
	// suficiente)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET, "/postear", "/permisos").hasRole("admin")
				.requestMatchers(HttpMethod.POST, "/cambiarPermisos").hasRole("admin")
				.requestMatchers(HttpMethod.POST, "/publicarComentario", "/cambiarConfiguracion")
				.hasAnyRole("admin", "usuario")
				.requestMatchers(HttpMethod.GET, "/configurar").hasAnyRole("admin", "usuario")
				.requestMatchers(HttpMethod.POST, "/crearUsuario").permitAll()
				.requestMatchers(HttpMethod.GET, "/", "/noticia", "/logeo", "/registro", "/categoria", "/busqueda")
				.permitAll()
				.and()
				.formLogin()
				.loginPage("/")// la pagina a la que redirecciona
				.loginProcessingUrl("/autentificacion")// la direccion que va en action del index
				/*
				 * el controlador con la ruta "/perform_login" no lo hemos configurado nosotrs
				 * si no que es SPring el que se encarga de todo ello, simepre que usemos el
				 * nombre de los campos del formulario siguiendo las convenciones de SPring
				 * Ej:
				 * (importante tambien que el metodo sea POST y que action sea la de
				 * "loginProcessingUrl"
				 * <input type="text" name="username" id="usuario" />
				 * <input type="text" name="password" id="contraseña" />
				 * Spring guarda esta informacion en una cookie "JSESSIONID" durante toda la
				 * navegacion
				 */
				.defaultSuccessUrl("/")// a que pagina redirige
				.failureUrl("/")// a que pagina redirige si hay fallo
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.and()
				.exceptionHandling()
				.accessDeniedPage("/error/403")
				.and()
				.csrf().disable();
		return http.build();
	}

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		RestTemplate plantilla = restTemplateBuilder.build();
		// RestTemplate plantilla = restTemplateBuilder.basicAuthentication("admin",
		// "admin").build();

		// indico que tipos de contenido son permitidos como respuesta a las peticiones
		// RESTTEMPLATE
		// (en este caso permito todos)
		// List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		// MappingJackson2HttpMessageConverter converter = new
		// MappingJackson2HttpMessageConverter();
		// converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		// messageConverters.add(converter);
		// plantilla.setMessageConverters(messageConverters);
		return plantilla;
	}

}
