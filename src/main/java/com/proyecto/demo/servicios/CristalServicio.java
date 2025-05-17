


package com.proyecto.demo.servicios;

import com.proyecto.demo.entidades.Cristal;
import com.proyecto.demo.entidades.Foto;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.CristalRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CristalServicio {
    @Autowired
    private CristalRepositorio cristalRepositorio;
    
    @Autowired
    private FotoServicio fotoServicio;
     
     
    @Transactional
    public void registrar(MultipartFile archivo , String nombre,int insumo) throws ErrorServicio{
        
        Cristal cristal = new Cristal();
      
        Foto foto = fotoServicio.guardar(archivo);
        
        if(insumo>0){
        cristal.setInsumo(true);
        }else{
            cristal.setInsumo(false);
        
        }
        cristal.setFoto(foto);
        cristal.setNombre(nombre);
        
        cristalRepositorio.save(cristal);
        
    }
    
    public Cristal buscarPorId(String id){
    
    return cristalRepositorio.getById(id);
    }
    
    public List<Cristal> listarTodas(){
    
    return cristalRepositorio.findAll();
    }
    
    @Transactional
    public void borrarTodo(){
    
    cristalRepositorio.deleteAll();
    }
       

}
