

package com.proyecto.demo.controladores;


import com.proyecto.demo.entidades.Barra;
import com.proyecto.demo.entidades.Pedido;
import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.servicios.BarraServicio;
import com.proyecto.demo.servicios.PedidoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoServicio barraServicio;
    
 
    
      @GetMapping("/formulario/{idUsuario}")
    public String form(ModelMap modelo, @PathVariable String idUsuario) {
        
        modelo.put("idUsuario",idUsuario );
        
        
        return "registroBarra.html";
    }
    @GetMapping("/listartodas")
    public String tabladeBarra(ModelMap modelo, @PathVariable String idUsuario) {
        
        //modelo.addAttribute("barra",barraServicio.listarTodas().get(0).getNombre() );
        
        
        return "listarBarras.html";
    }
    
    
	@GetMapping("/eliminar/{id}")
	public String eliminar(ModelMap modelo, @PathVariable String id) {
		try {
			Pedido barra = barraServicio.buscarPorId(id);
                        if(barra!=null){
                            
                            barraServicio.eliminar(id);
                            
                
                
                }
			return "redirect:/admin/dashboard";
		}catch(Exception e) {
			modelo.put("error", "No fue posible deshabilitar");
			return "index_app.html";
		}
	}
    
    @GetMapping("/listabarras")
    public String listabarras(ModelMap modelo) {
        modelo.put("barras", barraServicio.listarTodas());
        
        return "registroCristaleria.html";
    }
    @GetMapping("/formularioCristaleria")
    public String formCristaleria(ModelMap modelo) {
        
        
        return "registroCristaleria.html";
    }
    
     @PostMapping("/registrar")
    public String registrar(ModelMap modelo,  @RequestParam String nombre, @RequestParam String idUsuario) throws ErrorServicio {
        
         System.out.println("EL NOMBRE DE LA BARRA LLEGOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

         
         
        
        modelo.put("titulo", "Bienvenido");
        modelo.put("descripcion", "Barra cargada correctamente");
        return "exitoBarra.html";
    }
    
    @GetMapping("/deshabilitar/{id}")
	public String deshabilitar(ModelMap modelo, @PathVariable String id) {
		try {
			barraServicio.deshabilitar(id);
			return "redirect:/admin/dashboard";
		}catch(Exception e) {
			modelo.put("error", "No fue posible deshabilitar");
			return "inicioAdmin";
		}
	}

}
