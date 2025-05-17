package com.proyecto.demo.security;
import com.proyecto.demo.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

   
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("LLEGAMOS AL LOGIN SECURITY");
        http
               .authorizeRequests()
                .antMatchers("/admin/*").hasRole("ADMIN")
               .antMatchers("/css/*", "/js/*", "/img/*",
                        "/**").permitAll()
                .and().
                formLogin()
                .loginPage("/usuario/loginUsuario")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/usuario/inicioUsuario")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
               .logoutSuccessUrl("/login?logout")             
               .permitAll().
              and().csrf().disable();
   }
}
