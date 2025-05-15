

package com.proyecto.demo.servicios;

import com.proyecto.demo.entidades.Cristaleria;
import com.proyecto.demo.entidades.Ruptura;
import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.RupturaRepositorio;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RupturaServicio {
    
    
    
    @Autowired
    private RupturaRepositorio rupturaRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CristaleriaServicio cristaleriaServicio;
    @Autowired
    private BarraServicio barraServicio;
    
    @Transactional
    public void modificar( String nombre, String explicacion, int cantidad,String idCristaleria,String id) throws ErrorServicio {

       Ruptura ruptura = new Ruptura();
       Calendar calendario = new GregorianCalendar();
       
        
  

        
        if (!idCristaleria.isEmpty()) {

            Cristaleria cristaleria = cristaleriaServicio.buscarPorId(idCristaleria);
            Usuario usuario = usuarioServicio.buscarPorId(id);
          
           ruptura.setNombre(nombre);
           ruptura.setExplicacion(explicacion);
           ruptura.setIdUsuario(id);
           ruptura.setUsuario(usuario);
           
           ruptura.setNumeroDeRuptura(cantidad);
           ruptura.setCostoRuptura(cristaleria.getPrecio()*cantidad);
           ruptura.setTipoCristaleria(cristaleria);
           ruptura.setCalendario(calendario);
           cristaleria.setEnStock(cristaleria.getEnStock()-cantidad);
           
           if(cristaleria.isInsumo()){
               ruptura.setInsumo(true);
               
           }
           else{
           
               ruptura.setInsumo(false);
           }
           
           
           List<Ruptura> rupturas =usuario.getTodasLasRupturas();
           rupturas.add(ruptura);
           usuario.setTodasLasRupturas(rupturas);
       
          barraServicio.actualizarStockBarra(cristaleria.getBarraPerteneciente().getId(), cantidad);
           barraServicio.actualizarPrecioBarra(cristaleria.getBarraPerteneciente(), ruptura.getCostoRuptura());
        usuarioServicio.actualizarCapitalTotal(id);
        //COSTE MENSUAL
         
       
           //barraRepositorio.save(barraPerteneciente);

        }
        rupturaRepositorio.save(ruptura);
    }
    
    
    //RUPTURA DEL MES
    @Transactional
    public float actualizacionCosteMensualRupturas(String idUsuario,Calendar calendario) throws ErrorServicio{
        float costeMensual=0.f;
        Usuario usuario =usuarioServicio.buscarPorId(idUsuario);
        
        if(usuario!=null){
            for (Ruptura ruptura : usuario.getTodasLasRupturas()) {
                
                if(ruptura.getCalendario().get(Calendar.MONTH)==(calendario.get(Calendar.MONTH)+1)){
                    
                    costeMensual=costeMensual+ruptura.getCostoRuptura();
                
                
                
                }
                
                
            }
            usuario.setCosteMensual(costeMensual);
            return costeMensual;
        }
    
    return costeMensual;
    
    }
   public List<Ruptura> listarTodasRupturas(String id) throws ErrorServicio{
   List<Ruptura> rupturas = usuarioServicio.buscarPorId(id).getTodasLasRupturas();
      
 return rupturas;
   }
    
 @Transactional
    public void borrarTodo(){
    
    rupturaRepositorio.deleteAll();
    }
 
    
    
    public List<Ruptura> todasLasRupturas(String idUsuario){
      List<Ruptura> rupturas=null;
        for (Ruptura ruptura : rupturaRepositorio.findAll()) {
            if(ruptura.getIdUsuario().equals(idUsuario)){
            
                rupturas.add(ruptura);
            }
            
        }
      
    return rupturas;
    }
    
}