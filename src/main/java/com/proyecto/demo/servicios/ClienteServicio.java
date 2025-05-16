
package com.proyecto.demo.servicios;

import com.proyecto.demo.entidades.Cliente;
import com.proyecto.demo.repositorios.ClienteRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    
    @Transactional
    public void registrar(String nombre , String descripcion){
        
        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        
        clienteRepositorio.save(c);
    
    
    
    }
    
    
    
    
    public List<Cliente> getAll(){
    
    
    return clienteRepositorio.findAll();
    }
    
}
