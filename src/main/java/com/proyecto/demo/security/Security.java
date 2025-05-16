package com.proyecto.demo.security; // Asegúrate de que este sea tu paquete correcto

import com.proyecto.demo.servicios.UsuarioServicio; // Tu servicio de usuario
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Habilita la seguridad a nivel de método (ej. @PreAuthorize)
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServicio usuarioServicio; // Tu UserDetailsService

    /**
     * Define el bean para el PasswordEncoder.
     * BCryptPasswordEncoder es una opción fuerte y comúnmente utilizada.
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el AuthenticationManagerBuilder para usar el UserDetailsService personalizado
     * y el PasswordEncoder definido.
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServicio)      // Establece tu servicio para cargar usuarios
            .passwordEncoder(passwordEncoder());    // Establece el codificador de contraseñas
    }

    /**
     * Configura la seguridad HTTP, incluyendo autorización de rutas,
     * configuración del formulario de login, logout, y CSRF.
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Configurando HttpSecurity..."); // Mensaje para confirmar carga

        http
            .authorizeRequests(requests -> requests
                // Recursos estáticos y páginas públicas permitidas para todos
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/", // Página de inicio pública, si la tienes
                        "/error", // Página de error por defecto de Spring Boot
                        "/usuario/registrar", // Asumiendo que tienes una página de registro
                        "/usuario/loginUsuario", // Tu página de login
                        "/logincheck" // URL de procesamiento del login
                ).permitAll()
                // Rutas específicas para roles
                .antMatchers("/admin/**").hasRole("ADMIN") // Solo usuarios con ROLE_ADMIN
                .antMatchers("/usuario/inicioUsuario", "/usuario/perfil").hasAnyRole("USER", "ADMIN") // Ejemplo para usuarios logueados
                // Cualquier otra solicitud debe estar autenticada
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/usuario/loginUsuario")         // Especifica la URL de tu página de login personalizada
                .loginProcessingUrl("/logincheck")          // La URL a la que se envía el formulario de login (Spring Security la maneja)
                .usernameParameter("username")              // Nombre del parámetro para el nombre de usuario en el formulario
                .passwordParameter("password")              // Nombre del parámetro para la contraseña en el formulario
                .defaultSuccessUrl("/usuario/inicioUsuario", true) // URL a la que redirigir tras un login exitoso (true para forzar siempre esta URL)
                .failureUrl("/usuario/loginUsuario?error") // URL a la que redirigir tras un login fallido
                .permitAll() // Permite el acceso a la infraestructura de login (la página de login, etc.)
            )
            .logout(logout -> logout
                .logoutUrl("/logout")                       // URL para procesar el logout
                .logoutSuccessUrl("/usuario/loginUsuario?logout") // URL a la que redirigir tras un logout exitoso
                .invalidateHttpSession(true)                // Invalida la sesión HTTP
                .deleteCookies("JSESSIONID")                // Borra cookies (opcional, pero recomendado)
                .permitAll() // Permite el acceso a la funcionalidad de logout
            )
            .csrf(csrf -> csrf.disable()); // Deshabilita CSRF. Considera habilitarlo y configurarlo para producción.
    }
}