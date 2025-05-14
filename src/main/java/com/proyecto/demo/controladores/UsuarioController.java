package com.proyecto.demo.controladores;


import com.proyecto.demo.entidades.Barra;
import com.proyecto.demo.entidades.Cristaleria;
import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.entidades.Zona;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.ZonaRepositorio;
import com.proyecto.demo.servicios.BarraServicio;
import com.proyecto.demo.servicios.CristalServicio;
import com.proyecto.demo.servicios.CristaleriaServicio;
import com.proyecto.demo.servicios.PedidoServicio;
import com.proyecto.demo.servicios.ProveedorServicio;
import com.proyecto.demo.servicios.RupturaServicio;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CristaleriaServicio cristaleriaServicio;

    @Autowired
    private RupturaServicio rupturaServicio;
    @Autowired
    private ZonaRepositorio zonaRepositorio;
    @Autowired
    private BarraServicio barraServicio;
    @Autowired
    private PedidoServicio pedidoServicio;
     @Autowired
    private ProveedorServicio proveedorServicio;
    @Autowired
    private CristalServicio cristalServicio;
    
    
    //COPIA DE HTML FORMULARIO USUARIO
     
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-perfil-nuevo")
    public String editarPerfil(HttpSession session, @RequestParam String id,String nombre, ModelMap model) throws ErrorServicio {
        System.out.println("Estamos llegando a controlador del usuariossesision");
        //barraServicio.registrar(nombre, id);
       //List<Barra> barras =usuarioServicio.todasLasBarras(id);
         //model.put("barras", barras);

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            model.addAttribute("perfil", usuario);
            
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_perfilModificar.html";
    }
        //Este es el que llega a crear barra
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-perfil")
    public String editarPerfilviejo(HttpSession session, @RequestParam String id,String nombre, ModelMap model) throws ErrorServicio {
        System.out.println("Estamos llegando a controlador del usuariossesision");
        //barraServicio.registrar(nombre, id);
       //List<Barra> barras =usuarioServicio.todasLasBarras(id);
         //model.put("barras", barras);

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            model.addAttribute("perfil", usuario);
            
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroBarra.html";
    }
    //ESTE LELGA AL REGISTRO PEDIDO
       @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-pedido")
    public String registroPedido(HttpSession session, @RequestParam String id,String nombre, ModelMap model) throws ErrorServicio {
        System.out.println("Estamos llegando a controlador del usuariossesision");
        //barraServicio.registrar(nombre, id);
       //List<Barra> barras =usuarioServicio.todasLasBarras(id);
         //model.put("barras", barras);

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
             model.addAttribute("cristalerias", usuario.getTodasLasCristalerias());
            model.addAttribute("perfil", usuario);
            
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroPedido.html";
    }
    //Este es el que llega a crear proveedor
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-proveedor")
    public String registroProveedor(HttpSession session, @RequestParam String id,String nombre, ModelMap model) throws ErrorServicio {
        System.out.println("Estamos llegando a controlador del usuariossesision");
        //barraServicio.registrar(nombre, id);
       //List<Barra> barras =usuarioServicio.todasLasBarras(id);
         //model.put("barras", barras);

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            model.addAttribute("perfil", usuario);
            
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroProveedor.html";
    }
     //HTML CREAR CRISTAL
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-cristal")
    public String crearCristal(HttpSession session, @RequestParam String id,String nombre, ModelMap model) throws ErrorServicio {
         //barraServicio.registrar(nombre, id);
       //List<Barra> barras =usuarioServicio.todasLasBarras(id);
         //model.put("barras", barras);

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            model.addAttribute("perfil", usuario);
            
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroCristal.html";
    }
    
    //ESTE ES PARA ENTRAR AL FORMULARIO DE CRISTALERIA 
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-barra")
    public String cargarCristaleria(HttpSession session, @RequestParam String id, String nombre,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
System.out.println("NOMBRE E ID DE USUARIO BARRA _"+id+";"+nombre);
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        model.put("barras", usuarioServicio.buscarPorId(id).getBarras());
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
              model.addAttribute("cristales",cristalServicio.listarTodas());
            model.addAttribute("perfil", usuario);
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroCristaleria.html";
    }
     //ESTE ES PARA ENTRAR AL PANEL BARRA  ------------------cerebro
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/panel-barra")
    public String panelBarra(HttpSession session, @RequestParam String id, String nombre,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
        System.out.println("NOMBRE E ID DE USUARIO BARRA _"+id+";"+nombre);
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        
        model.put("barras", usuarioServicio.buscarPorId(id).getBarras());
      
         
         
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
              usuarioServicio.actualizarCapitalTotal(id);
                List<Cristaleria> cristalerias=usuario.getTodasLasCristalerias();
                model.addAttribute("cristalerias",cristalerias );
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
              model.addAttribute("proveedores", usuario.getProveedores());
            model.addAttribute("rupturas",usuario.getTodasLasRupturas());
             model.addAttribute("perfil", usuario);
                
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app.html";
    }
      //ESTE ES PARA ENTRAR AL PANEL DE STOCK DE BARTENDER
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/panel-insumos")
    public String panelInsumo(HttpSession session, @RequestParam String id, String nombre,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
        
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        
        model.put("barras", usuarioServicio.buscarPorId(id).getBarras());
        List<Cristaleria> cristalerias=usuarioServicio.buscarPorId(id).getTodasLasCristalerias();
         model.put("cristalerias",cristalerias );
         
         
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
              usuarioServicio.actualizarCapitalTotal(id);
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
              model.addAttribute("proveedores", usuario.getProveedores());
            model.addAttribute("rupturas",usuario.getTodasLasRupturas());
             model.addAttribute("perfil", usuario);
                
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_insumos.html";
    }
    
     //ESTE ES PARA ENTRAR AL FORMULARIO DE RUPTURA
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/editar-ruptura")
    public String cargarRuptura(HttpSession session , String id, String nombre,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
       System.out.println("NOMBRE E ID DE USUARIO BARRA _"+id+";"+nombre);
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        model.put("barras", usuarioServicio.buscarPorId(id).getBarras());
        model.put("cristalerias",usuarioServicio.buscarPorId(id).getTodasLasCristalerias());
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app_registroRuptura.html";
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/editar-barra-form")
    public String editarBarraFormulario(HttpSession session, @RequestParam String id, String nombre,ModelMap model) throws ErrorServicio {
        barraServicio.registrar(nombre, id);

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            //barraServicio.registrar(nombre, id);
            Usuario usuario = usuarioServicio.buscarPorId(id);
             model.addAttribute("barras", usuarioServicio.todasLasBarras(id));
            model.addAttribute("perfil", usuario);
        } catch (ErrorServicio e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index_app.html";
    }
    
     @GetMapping("/registroUsuario")
    public String registro() {
        
        
        return "registroUsuario.html";
    }
      @GetMapping("/registroUsuarioAdmin")
    public String registroAdmin() {
        
        
        return "registroUsuarioAdmin.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-perfil")
    public String actualizar(ModelMap modelo, HttpSession session, MultipartFile archivo, @RequestParam String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {

        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            usuarioServicio.modificar(archivo, id, nombre, apellido, mail, clave2, clave2);
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
            List<Zona> zonas = zonaRepositorio.findAll();
            modelo.put("zonas", zonas);
            modelo.put("error", ex.getMessage());
            modelo.put("perfil", usuario);

            return "perfil.html";
        }

    }
     
 //POST MODIFICAR ALTERAR CREAR CRISTALERIA
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/alterar-cristaleria")
    public String alterarCristaleria(ModelMap modelo, HttpSession session,   String idCristaleria,MultipartFile archivo, String tipo, String descripcion, float precio, int enStock,String id) throws ErrorServicio {
        
        //Aqui me comunico con modificar cristaleria
         cristaleriaServicio.alterar(archivo, tipo, descripcion, precio, enStock, idCristaleria, id);
        Cristaleria cristaleria = cristaleriaServicio.buscarPorId(idCristaleria);
         Usuario usuario = usuarioServicio.buscarPorId(id);
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(usuario.getId())) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(usuario.getId());
            //usuarioServicio.modificarBarra(id,nombre);
           
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "perfil.html";
        }

    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-barra")
    public String actualizarBarra(ModelMap modelo, HttpSession session,   String id,  String nombre ) throws ErrorServicio {
        System.out.println("ACTUALIZAR BARRA"+nombre+id);
        //Aqui me comunico con MODIFICARBARRA
         usuarioServicio.modificarBarra(id, nombre);
        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            //usuarioServicio.modificarBarra(id,nombre);
           
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "perfil.html";
        }

    }
    //POST ACTUALIZAR PROVEEDOR
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-proveedor")
    public String actualizarProveedor(ModelMap modelo, HttpSession session,   String id,  String nombre ,long contacto,String link ) throws ErrorServicio {
       
        //Aqui me comunico con MODIFICARBARRA
         usuarioServicio.modificarProveedor(id, nombre,contacto,link);
        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            //usuarioServicio.modificarBarra(id,nombre);
           
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "perfil.html";
        }

    }
     //POST ACTUALIZAR PROVEEDOR
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-pedido")
    public String actualizarPedido(ModelMap modelo, HttpSession session,   String id,  String nombre ,int contacto,String link ) throws ErrorServicio {
       
        //Aqui me comunico con MODIFICARBARRA
         pedidoServicio.modificarPedido(id, nombre,contacto,link);
        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            //usuarioServicio.modificarBarra(id,nombre);
           
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "perfil.html";
        }

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
     @GetMapping("/loginUsuario")
    public String palabrota() {
        
        return "loginUsuario1.html";
    }
     @GetMapping("/listarbarra/{idUsuario}")
    public String barras(ModelMap modelo , @PathVariable String idUsuario) throws ErrorServicio {
        List<Barra> barras =usuarioServicio.buscarPorId(idUsuario).getBarras();
       modelo.put("barras", barras);
        return "registroBarra.html";
    }
     @GetMapping("/inicioUsuario")
    public String inicioUsuario(ModelMap modelo) {
        
        
        
        
        return  "inicioUsuario.html";
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
    //REGISTRO USUARIO ADMIN
       @PostMapping("/registraradmin")
    public String registrarAdmin( ModelMap modelo,MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {
        System.out.println("LLEGAMOS A LOS CONTROLADORES TIOOOOOOO");
        try {
            usuarioServicio.registrarAdmin(archivo, nombre, apellido, mail, clave1, clave2);
        } catch (ErrorServicio ex) {
           
            return "index.html";
        }
        modelo.put("titulo", "Bienvenido a Tinder de Mascotas");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "exito.html";
    } 

 //POST CREAR CRISTALERIA
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-cristaleria")
    public String actualizarCristaleria(ModelMap modelo, HttpSession session,   String id,MultipartFile archivo, String tipo, String descripcion, float precio, int enStock,String idBarra,String idCristal) throws ErrorServicio {
        System.out.println("CRISTALERIA LELGA A CONTROLADRO USUARIO"+ precio +";"+idCristal);
        //Aqui me comunico con modificar cristaleria
          //usuarioServicio.actualizarCapitalTotal(id);
         cristaleriaServicio.modificar(archivo, tipo, descripcion, precio, enStock, idBarra,id, idCristal);
        Usuario usuario = null;
        try {
            //usuarioServicio.actualizarCapitalTotal(id);

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            //usuarioServicio.modificarBarra(id,nombre);
           
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "index_app.html";
        }

    }
    
    
    
    //CREAR CRISTALERIA PPST
    
    
     @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-cristal")
    public String actualizarCristal(ModelMap modelo, HttpSession session,@RequestParam   String id,MultipartFile archivo, String nombre) throws ErrorServicio {
        
        //Aqui me comunico con modificar cristaleria
          usuarioServicio.actualizarCapitalTotal(id);
         cristalServicio.registrar(archivo, nombre);
        Usuario usuario = null;
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            
            //usuarioServicio.modificarBarra(id,nombre);
             modelo.addAttribute("cristales",cristalServicio.listarTodas());
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
           

            return "perfil.html";
        }

    }
    
    
    
    
    
    
    
    
    

//METODO PARA MODIFICAR BARRA------------cerebro
@GetMapping("/modificar-barra-panel/{id}")
	public String modificarBarraPanel(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
		Barra barra =barraServicio.buscarPorId(id);
                  usuarioServicio.actualizarCapitalTotal(barra.getUsuario().getId());
            modelo.put("barra", barra);
            modelo.addAttribute("rupturas",barra.getUsuario().getTodasLasRupturas());
          
            modelo.put("cristalerias",barraServicio.buscarPorId(id).getListaCristalerias() );
            return "index_app_modificarBarraPanel.html";
	}
        
        
        
        
        //METODO PARA MODIFICAR BARRA queda  guardado para despuesS GESTIR DE RYTYRAS
        @GetMapping("/ruptura/{id}")
	public String crearRutura(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
		
            modelo.put("cristaleria",cristaleriaServicio.buscarPorId(id));
           
            return "registroRuptura.html";
	}

        
        //REGISTRAR RUPTURA -----------------------------------POST
 @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @PostMapping("/actualizar-ruptura")
    public String actualizarRuptura(ModelMap modelo, HttpSession session,@RequestParam String id, @RequestParam String idCristaleria, @RequestParam String nombre, @RequestParam String explicacion, @RequestParam int cantidad) throws ErrorServicio {

        Usuario usuario = null;
        rupturaServicio.modificar(nombre, explicacion, cantidad, idCristaleria,id);
        try {

            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/inicio";
            }

            usuario = usuarioServicio.buscarPorId(id);
            
            session.setAttribute("usuariosession", usuario);

            return "redirect:/inicio";
        } catch (ErrorServicio ex) {
            

            return "perfil.html";
        }

    }
    
    
    
    
    @GetMapping("/tablaCristalerias")
    public String tablaCristaleria(ModelMap modelo){
        modelo.put("cristalerias", cristaleriaServicio.todasCristalrias());
    return "tablaCristaleria.html";
    }

}//llave de clase
