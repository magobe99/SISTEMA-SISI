package com.proyectoSisi.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.proyectoSisi.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements ApplicationContextAware{
	
	//Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/include/**","/css/**","/data/**","/img/**","/js/**","/images/**","/recursos/**"
    };
    
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	// LIMITE DE SESIÓNES ABIERTAS
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	// ------------------------
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	

	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        	.sessionManagement(session -> session // LIMITE DE SESIÓNES ABIERTAS
	        	.maximumSessions(1)) // LIMITE DE SESIÓNES ABIERTAS
	            .authorizeRequests()
		        .antMatchers(resources).permitAll()  
		        .antMatchers("/","/login","/forgot_password","/reset_password","/noEncontrado","/send","/succeful").permitAll()
		        .antMatchers("/index").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') or hasRole('ROLE_VENDEDOR')")
		        .antMatchers("/inventarios**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/categorias**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/categorias/nuevo").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/categorias/guardar").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/categorias/editar/{id_Categoria}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/categorias/eliminar/{id_Categoria}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/clientes**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') or hasRole('ROLE_VENDEDOR')")
		        .antMatchers("/clientes/eliminar/{id}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productos**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productos/solicitar").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productos/nuevo").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productos/guardar").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productos/editar/{IdMateriaPrima}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productos/eliminar/{IdMateriaPrima}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productos/detalle/{IdMateriaPrima}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productosPRO**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') or hasRole('ROLE_VENDEDOR')")
		        .antMatchers("/productosPRO/editar/{IdProduct}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productosPRO/guardar").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productosPRO/guardarLote").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/productosPRO/eliminar/{IdProduct}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/report**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO')")
		        .antMatchers("/proveedores**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/proveedores/nuevo").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/proveedores/guardar").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/proveedores/editar/{id}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/proveedores/eliminar/{id}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/Devoluciones/").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/Devoluciones/{id}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/Devoluciones/list").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/registro**").access("hasRole('ROLE_ADMINISTRADOR')")
		        .antMatchers("/api/csv**").access("hasRole('ROLE_ADMINISTRADOR')")
		        .antMatchers("/contact").access("hasRole('ROLE_ADMINISTRADOR')")
		        .antMatchers("/contact2").access("hasRole('ROLE_ADMINISTRADOR')")
		        .antMatchers("/usuarios**").access("hasRole('ROLE_ADMINISTRADOR')")
		        .antMatchers("/usuarios/guardar").access("hasRole('ROLE_ADMINISTRADOR')")
		        .antMatchers("/usuarios/editar/{id}").access("hasRole('ROLE_ADMINISTRADOR')")
		        .antMatchers("/usuarios/eliminar/{id}").access("hasRole('ROLE_ADMINISTRADOR')")
		        .antMatchers("/producciones**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/movimientosMP**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/lotes**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/movimientosPP**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/comprar**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/compras**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/compras/detalle/{id}").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') ")
		        .antMatchers("/vender**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') or hasRole('ROLE_VENDEDOR')")
		        .antMatchers("/ventas**").access("hasRole('ROLE_ADMINISTRADOR') or hasRole('ROLE_COORDINADOR_LOGISTICO') or hasRole('ROLE_VENDEDOR')")
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .permitAll()
	                .defaultSuccessUrl("/index")
	                .failureUrl("/login?error=true")
	                .usernameParameter("username")
	                .passwordParameter("password")
	                .and()
	            .logout()
	                .invalidateHttpSession(true)
	                .clearAuthentication(true)
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/login?logout")
	                .permitAll()
	                .and().csrf().disable();
	        	
	    }
	  
}






