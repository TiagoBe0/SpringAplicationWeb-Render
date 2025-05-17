

package com.proyecto.demo.controladores;

import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.ZonaRepositorio;
import com.proyecto.demo.servicios.BarraServicio;
import com.proyecto.demo.servicios.CristalServicio;
import com.proyecto.demo.servicios.CristaleriaServicio;
import com.proyecto.demo.servicios.PedidoServicio;
import com.proyecto.demo.servicios.ProveedorServicio;
import com.proyecto.demo.servicios.RupturaServicio;
import com.proyecto.demo.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exito")
public class ExitoController {
    
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
    
        @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USUARIO')")
    @GetMapping("/registro-ruptura/{id}")
    public String cargarRuptura(@PathVariable String id,ModelMap model) throws ErrorServicio {
        //barraServicio.registrar(nombre, id);
       
       
        model.put("barras", usuarioServicio.buscarPorId(id).getBarras());
        model.put("cristalerias",usuarioServicio.buscarPorId(id).getTodasLasCristalerias());
       
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
    @GetMapping("/inicio/{id}")
    public String inicio(ModelMap modelo,@PathVariable String id) throws ErrorServicio {
        modelo.put("perfil",usuarioServicio.buscarPorId(id));
    	modelo.put("cristalerias",cristaleriaServicio.listarCristaleriasPorIdUsuario(id));
    	  
    	
        return "index_app_inicio.html";
    }

}
