package com.proyecto.demo.controladores;

import com.proyecto.demo.entidades.Barra;
import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.entidades.Zona;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.ZonaRepositorio;
import com.proyecto.demo.servicios.BarraServicio;
import com.proyecto.demo.servicios.CristalServicio;
import com.proyecto.demo.servicios.CristaleriaServicio;
import com.proyecto.demo.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Controlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ZonaRepositorio zonaRepositorio;
    @Autowired
    private BarraServicio barraServicio;
    @Autowired
    private CristalServicio cristalServicio;
    @Autowired
    private CristaleriaServicio cristaleriaServicio;
    @GetMapping("/")
    public String index(ModelMap modelo) {
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
    @GetMapping("/exito-registro-ruptura/{id}")
    public String cargarRuptura(@PathVariable String id,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
       
        
        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
             model.put("cristalerias",usuario.getTodasLasCristalerias());
             model.put("exito","ruptura cargada exitosamente!");
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroRuptura.html";
    }
     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/exito-registro-cristaleria/{id}")
    public String registroCristaleria(@PathVariable String id,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
       
       
       
        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
           model.addAttribute("cristales",cristalServicio.listarTodas());
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
            model.put("exito","cristaleria cargada exitosamente!");
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroCristaleria.html";
    }
     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/error-registro-cristaleria/{id}")
    public String registroCristaleriaError(@PathVariable String id,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
       
       
       
        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
            model.addAttribute("cristales",cristalServicio.listarTodas());
            model.put("exito","registro exitoso!");
           
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroCristaleria.html";
    }
     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/exito-registro-barra/{id}")
    public String registroBarra(@PathVariable String id,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
       
       
       
        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
            model.put("exito","barra cargada exitosamente!");
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroBarra.html";
    }
   @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/exito-registro-cristal/{id}")
    public String registroCristal(@PathVariable String id,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
       
       
       
        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
            model.put("exito","cristal cargada exitosamente!");
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroCristal.html";
    }
     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/exito-registro-proveedor/{id}")
    public String registroProveedor(@PathVariable String id,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
       
       
       
        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
            model.put("exito","proveedor cargada exitosamente!");
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroProveedor.html";
    }

    
     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/exito-registro-item/{id}")
    public String cargarItem( @PathVariable String id,ModelMap model) throws ErrorServicio {
 
      
        model.put("barras", usuarioServicio.buscarPorId(id).getBarras());
     

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
              model.addAttribute("cristales",cristalServicio.listarTodas());
              
            model.addAttribute("perfil", usuario);
             model.put("exito","item cargado exitosamente!");
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroItem.html";
    }
     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/exito-registro-item-existente/{id}")
	public String modificarBarraPanel(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
		Barra barra =barraServicio.buscarPorId(id);
                  usuarioServicio.actualizarCapitalTotal(barra.getUsuario().getId());
            modelo.put("barra", barra);
            modelo.addAttribute("rupturas",barra.getUsuario().getTodasLasRupturas());
          
            modelo.put("cristalerias",barraServicio.buscarPorId(id).getListaCristalerias() );
            return "modificarBarraPanel.html";
	}
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/inicio/{id}")
    public String inicio(ModelMap modelo,@PathVariable String id) throws ErrorServicio {
        modelo.put("perfil",usuarioServicio.buscarPorId(id));
    	modelo.put("cristalerias",cristaleriaServicio.listarCristaleriasPorIdUsuario(id));
    	  
    	
        return "index_app_inicio.html";
    }

    @GetMapping("/loginUsuarioModelo")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
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

    @GetMapping("/registro")
    public String registro() {
        
        
        return "registroUsuario.html";
    }
      @GetMapping("/registroAdmin1942")
    public String registroUsuarioAdmin() {
        
        
        return "registroUsuarioAdmin.html";
    }

    @PostMapping("/registrar")
    public String registrar( ModelMap modelo,MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {
        
        try {
            usuarioServicio.registrar(archivo, nombre, apellido, mail, clave1, clave2);
        } catch (ErrorServicio ex) {
           
            return "index.html";
        }
        modelo.put("titulo", "Bienvenido a BartenderStock");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "index_app_inicio.html";
    }
    @PostMapping("/registrarAdmin")
    public String registrarAdmin( ModelMap modelo,MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {
        
        try {
            usuarioServicio.registrarAdmin(archivo, nombre, apellido, mail, clave1, clave2);
        } catch (ErrorServicio ex) {
           
            return "index.html";
        }
       modelo.put("titulo", "Bienvenido a BartenderStock");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "index_app_inicio.html";
    }

}
