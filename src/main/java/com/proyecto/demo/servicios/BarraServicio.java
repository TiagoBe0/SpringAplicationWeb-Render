

package com.proyecto.demo.servicios;

import com.proyecto.demo.entidades.Barra;
import com.proyecto.demo.entidades.Cristaleria;
import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.BarraRepositorio;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarraServicio {
    
    @Autowired
    private BarraRepositorio barraRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Transactional
    public void registrar(String nombre,String idUsuario) throws ErrorServicio {

       
       Barra barra = new Barra();
       //barra.setAlta(new Date());
       barra.setUsuario(usuarioServicio.buscarPorId(idUsuario));
       barra.setActiva(true);
       usuarioServicio.actualizarListBarras(idUsuario,barra.getId());
       barra.setNombre(nombre);
       
      
        
        barraRepositorio.save(barra);
        

       

    }
    
    //ACTUALIZAR PRECIO TOTAL DE BARRA
    
    @Transactional
    public void actualizarPrecioBarra(Barra barra,float costoRuptura) throws ErrorServicio{
        //buscamos el usuario y getiamos las listas de Barra
    
    barra.setPrecioTotal(barra.getPrecioTotal()-costoRuptura);
    
    
    
    
    }
    
    
    public int[] actualizarStockBarra(String idUsuario) throws ErrorServicio{
        int[] array=new int[2];
        int total=0;
        int totalInsumos=0;
        Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
        if(usuario!=null){
            
            for (Barra barra : usuario.getBarras()) {
               if(barra.isInsumo() ){
                    totalInsumos=totalInsumos+barra.getTotalUnidades();
               }else{
                    total=total+barra.getTotalUnidades();
               
               
               }
            }
            array[0]=total;
            array[1]=totalInsumos;
        
        
        }
    
    return array;
    }
        
    @Transactional
    public void actualizarStockBarra(String idBarra,int numeroRupturas) throws ErrorServicio{
        //buscamos el usuario y getiamos las listas de Barra
    Barra barra = buscarPorId(idBarra);
    barra.setTotalUnidades(barra.getTotalUnidades()-numeroRupturas);
     
            
    
    }
   
    public List<Barra> listarTodas(){
        return barraRepositorio.findAll();
    }
    public float calcularPrecioTotal(List<Cristaleria> cristalerias){
        
        float suma =0;
        
        for (Cristaleria cristaleria : cristalerias) {
            if(!cristaleria.isInsumo()){
            suma = suma + cristaleria.getPrecioTotal();
            
            }
        }
        
    
    return suma;
    
    
    }
    public float calcularPrecioTotalInsumos(List<Cristaleria> cristalerias){
        
        float suma =0;
        
        for (Cristaleria cristaleria : cristalerias) {
            
             if(cristaleria.isInsumo()){
            suma = suma + cristaleria.getPrecioTotal();
            
            }
        }
        
    
    return suma;
    
    
    }
    //ELIMINAR BARRA
    @Transactional
    public void eliminar(String idBarra) throws ErrorServicio{
        
        buscarPorId(idBarra).setActiva(false);
        
    
    
        
        
    }
    
     @Transactional
    public void borrarTodo(){
    
   barraRepositorio.deleteAll();
    }
       
    
     public Barra buscarPorId(String id) throws ErrorServicio {

        Optional<Barra> respuesta = barraRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Barra barra = respuesta.get();
            return barra;
        } else {

            throw new ErrorServicio("No se encontró la barra solicitada");
        }

    }
     
     public void deshabilitar(String id) throws ErrorServicio{
     
          Barra barra = buscarPorId(id);
          barraRepositorio.delete(barra);
          barraRepositorio.deleteById(id);
     
     }
     

}
