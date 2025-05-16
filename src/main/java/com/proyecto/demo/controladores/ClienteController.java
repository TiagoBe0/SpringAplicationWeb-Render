
package com.proyecto.demo.controladores;

import com.proyecto.demo.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    
    
    
    @GetMapping("/registro")
    public String registro(){
    return "registroCliente.html";
    }
    
    
     @PostMapping("/registro")
    public String registrar(ModelMap modelo,String nombre , String descripcion){
        
        clienteServicio.registrar(nombre, descripcion);
        
        modelo.put("clientes",clienteServicio.getAll());
        
        
    return "listaClientes.html";
    }
    
      @GetMapping("/listaClientes")
    public String registssso(ModelMap modelo){
        
        
        modelo.put("clientes",clienteServicio.getAll());
    return "listaClientes.html";
    }
    
    
    
}
