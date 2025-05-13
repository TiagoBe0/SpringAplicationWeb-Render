package com.proyecto.demo.controladores;

import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.entidades.Zona;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.ZonaRepositorio;
import com.proyecto.demo.servicios.UsuarioServicio;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Controlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ZonaRepositorio zonaRepositorio;

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        @GetMapping("/calificanos")
    public String calificanos(){
        return "calificanos.html";
    }
    @GetMapping("/nosotros")
    public String nosotros(){
        return "nosotros.html";
    }
        @GetMapping("/servicios")
    public String servicios(){
        return "servicios.html";
    }
        @GetMapping("/brief")
    public String brief(){
        return "brief.html";
    }
    
      @GetMapping("/contacto")
    public String contacto(){
        return "contacto.html";
    }
    
    
     @GetMapping("/registro")
    public String registro(){
        return "registroUsuario.html";
    }
    
    
    //CONTROLADORES VIEJOS DE AQUI ABAJO
    
    
    
    
    
    
    
    
    
    
    
    
    
    @GetMapping("/render")
    public String inde_test_render(){
    
    return "index_render.html";
    }
     @GetMapping("/render-index")
    public String index_test(){
    
    return "index.html";
    }
    
    
        @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Usuario> usuariosActivos = usuarioServicio.todosLosUsuarios();
        //Recordar que utilizo el modelo,para viajar con la llave usuarios al HTML la lista usuariosactivos
        modelo.addAttribute("usuarios", usuariosActivos);
        return "index.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/inicio-render")
    public String inicio() {
        return "inicio.html";
    }

    @GetMapping("/login-render")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o clave incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Ha salido correctamente.");
        }
        return "login.html";
    }

    @GetMapping("/registro-render")
    public String registro(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        return "registro.html";
    }

    @GetMapping("/s")
    public String indexx(ModelMap modelo) {
        List<Usuario> usuariosActivos = usuarioServicio.todosLosUsuarios();
        //Recordar que utilizo el modelo,para viajar con la llave usuarios al HTML la lista usuariosactivos
        modelo.addAttribute("usuarios", usuariosActivos);
        return "error.html";
    }
    
    @GetMapping("/formularioBarra")
    public String form(ModelMap modelo) {
        
        
        return "formularioBarra.html";
    }
  

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/inicio")
    public String inicio(ModelMap modelo) {
    	
    	List<Usuario> usuarios = usuarioServicio.todosLosUsuarios();
    	
    	modelo.addAttribute("usuarios", usuarios);
    	
        return "index_app_inicio.html";
    }

    @GetMapping("/loginUsuarioModelo")
    public String loginn(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o clave incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Ha salido correctamente.");
        }
        return "loginUsuario1.html";
    }
     @GetMapping("/loginUsuarioControlador")
    public String loginUser() {
        
        return "loginUsuario1.html";
    }

    @GetMapping("/registrol")
    public String registrol() {
        
        
        return "registroUsuario.html";
    }

    @PostMapping("/registrar")
    public String registrar( ModelMap modelo,MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {
        System.out.println("LLEGAMOS A LOS CONTROLADORES TIOOOOOOO");
        try {
            usuarioServicio.registrar(archivo, nombre, apellido, mail, clave1, clave2);
        } catch (ErrorServicio ex) {
           
            return "index.html";
        }
        modelo.put("titulo", "Bienvenido a Tinder de Mascotas");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "exito.html";
    }

}
