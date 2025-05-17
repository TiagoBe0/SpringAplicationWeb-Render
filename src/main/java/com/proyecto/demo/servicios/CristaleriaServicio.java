

package com.proyecto.demo.servicios;

import com.proyecto.demo.entidades.Barra;
import com.proyecto.demo.entidades.Cristal;
import com.proyecto.demo.entidades.Cristaleria;
import com.proyecto.demo.entidades.Foto;
import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.BarraRepositorio;
import com.proyecto.demo.repositorios.CristalRepositorio;

import com.proyecto.demo.repositorios.CristaleriaRepositorio;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CristaleriaServicio {
    
    
    @Autowired
    private CristaleriaRepositorio cristaleriaRepositorio;
     @Autowired
    private BarraServicio barraServicio;
     @Autowired
     private CristalServicio cristalServicio;
     
     @Autowired
     private UsuarioServicio usuarioServicio;
     
     
     @Autowired
     private BarraRepositorio barraRepositorio;
      @Autowired
     private CristalRepositorio cristalRepositorio;
    
     @Autowired
    private FotoServicio fotoServicio;

    
    @Transactional
    public void registrar(MultipartFile archivo, String tipo, String descripcion, float precio, int enStock,String idBarra) throws ErrorServicio {

      

        

       Cristaleria cristaleria = new Cristaleria();
       
      
        Foto foto = fotoServicio.guardar(archivo);
        cristaleria.setFoto(foto);
        cristaleria.setAlta(new Date());
        cristaleria.setDescripcion(descripcion);
        cristaleria.setPrecio(precio);
        cristaleria.setEnStock(enStock);
        cristaleria.setTipo(tipo);
        Calendar calendario = new GregorianCalendar();
        cristaleria.setCalendario(calendario);
        //Creamos una barra de pertenecencia y añadimos a lista de cristaleria comoa tributo
        Barra barra =barraServicio.buscarPorId(idBarra);
        Usuario usuario = usuarioServicio.buscarPorId(barra.getUsuario().getId());
        cristaleria.setIdUsuario(barra.getUsuario().getId());
         List<Cristaleria> cristalerias = barra.getListaCristalerias();
         cristalerias.add(cristaleria);
       
         barra.setListaCristalerias(cristalerias);
          float suma = barraServicio.calcularPrecioTotal(cristalerias);
          usuario.setCapitalTotal(suma);
        barra.setPrecioTotal(suma);
         
        cristaleria.setBarraPerteneciente(barra);
        
        
        cristaleriaRepositorio.save(cristaleria);
        

       

    }
    
    public List<Cristaleria> listarCristaleriasPorIdUsuario(String idUsuario){
    
    
   return cristaleriaRepositorio.buscarPorIdUsuario(idUsuario);
   
    
    }
     public List<Cristaleria> listarInsumosPorIdUsuario(String idUsuario){
    
    
    return cristaleriaRepositorio.buscarPorIdUsuario(idUsuario);
  
       
    
    }
    
    
    public List<Cristaleria> listarPorIdUsuario(String idUsuario){
    
    
    return cristaleriaRepositorio.buscarPorIdUsuario(idUsuario);
    }
     

    @Transactional
    public void modificar(MultipartFile archivo, String tipo, String descripcion, float precio, int enStock,String idBarra,String id,String idCristal) throws ErrorServicio {
       Cristal cristal =null;
       Cristal cristalNuevo = null;
       Cristaleria cristaleria = new Cristaleria();
       if(idCristal!=null){
       cristal = cristalServicio.buscarPorId(idCristal);
       }
           
       if(cristal !=null){
              cristaleria.setCristalRepo(cristal);
           
            cristaleria.setFoto(cristal.getFoto());
               
        
        }else{
            if(archivo!=null){
        Foto foto = fotoServicio.guardar(archivo);
      
        
        
        cristaleria.setFoto(foto);
            }
            else{
            
                cristaleria.setFoto(null);
            }
        
        
        }
   
        if (!idBarra.isEmpty()) {

            
        Barra barraPerteneciente = barraServicio.buscarPorId(idBarra);
        if(barraPerteneciente.isInsumo()){
            cristaleria.setInsumo(true);
            if(idCristal ==null && archivo != null){
            cristalServicio.registrar(archivo, null, 1);
            }
        
        
        }else{
            cristaleria.setInsumo(false);
            if(idCristal ==null && archivo != null){
            cristalServicio.registrar(archivo, null, -1);
            }
        }
        
        Usuario usuario = usuarioServicio.buscarPorId(id);
        cristaleria.setDescripcion(descripcion);
        cristaleria.setPrecio(precio);
        cristaleria.setEnStock(enStock);
        cristaleria.setPrecioTotal();
        cristaleria.setIdUsuario(id);
        cristaleria.setTipo(tipo);
        cristaleria.setBarraPerteneciente(barraPerteneciente);
        cristaleria.setBarraPertenecienteNombre(barraPerteneciente.getNombre());
        List<Cristaleria> cristalerias = barraPerteneciente.getListaCristalerias();
        
        cristalerias.add(cristaleria);
        barraPerteneciente.setTotalUnidades(barraPerteneciente.getTotalUnidades()+cristaleria.getEnStock());
         List<Cristaleria> cristaleriaUsuario =usuario.getTodasLasCristalerias();
         cristaleriaUsuario.add(cristaleria);
         usuario.setTodasLasCristalerias(cristaleriaUsuario);
          if( barraPerteneciente.isInsumo()){
                   
                    float suma = barraServicio.calcularPrecioTotalInsumos(cristalerias);
                     barraPerteneciente.setPrecioTotal(suma);
               }
               else{
                    float suma = barraServicio.calcularPrecioTotal(cristalerias);
                     barraPerteneciente.setPrecioTotal(suma);
               
               }
          
          
      
        barraPerteneciente.setListaCristalerias(cristalerias);
        
           //barraRepositorio.save(barraPerteneciente);
/*
            String idFoto = null;
            if (cristaleria.getFoto() != null) {
                idFoto = cristaleria.getFoto().getId();
                   Foto foto = fotoServicio.actualizar(idFoto, archivo);
            cristaleria.setFoto(foto);

            }
*/
         
            cristaleriaRepositorio.save(cristaleria);
        } else {

            throw new ErrorServicio("No se encontró el usuario solicitado");
        }

    }
    
     @Transactional
    public void borrarTodo(){
    
    cristaleriaRepositorio.deleteAll();
    }
       
      @Transactional
    public void alterar(MultipartFile archivo, String tipo, String descripcion, float precio, int enStock,String id,String idUsuario) throws ErrorServicio {
                
                
         if(!id.isEmpty()){
             Optional<Cristaleria> respuesta = cristaleriaRepositorio.findById(id);
             if(respuesta.isPresent()){
             
                 Cristaleria cristaleria =  respuesta.get();
                 
                 cristaleria.setDescripcion(descripcion);
                 cristaleria.setPrecio(precio);
                 cristaleria.setTipo(tipo);
                 cristaleria.setIdUsuario(idUsuario);
                 cristaleria.setEnStock(enStock);
                 cristaleria.setPrecioTotal();
                 if(archivo!=null){
                     
                     Foto foto =fotoServicio.guardar(archivo);
                     if(foto!=null){
                     
                         cristaleria.setFoto(foto);
                     }
                 
                 
                 }
                 cristaleriaRepositorio.save(cristaleria);
               
                 alterarActulizacion(idUsuario, id);
                
             }
          
       
         else {

            throw new ErrorServicio("No se encontró el usuario solicitado");
        }
         }

    }
    public List<Cristaleria> todasCristalrias(){
 
        return cristaleriaRepositorio.findAll();
        
    }
    
   @Transactional
     public void deshabilitar(String id) throws ErrorServicio{
          
         Cristaleria cristaleria= buscarPorId(id);
         if(cristaleria.isActivo()){
             cristaleria.setActivo(false);
         }
         else{
             cristaleria.setActivo(true);
         }
     }
     
     @Transactional
     public void alterarActulizacion(String idUsuario , String idCristaleria) throws ErrorServicio{
         
         Cristaleria cristaleria =buscarPorId(idCristaleria);
          Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
                String idBarra = cristaleria.getBarraPerteneciente().getId();
                Barra barra = barraServicio.buscarPorId(idBarra);
                List<Cristaleria> cristalerias=barra.getListaCristalerias();
                List<Cristaleria> cristaleriasUsuario=usuario.getTodasLasCristalerias();
                
                 for (Cristaleria cristaleria1 : cristalerias) {
                     if(cristaleria1.getId().equals(idCristaleria)){
                         
                         cristaleria.setBarraPertenecienteNombre(barra.getNombre());
                         cristaleria1.setEnStock(cristaleria.getEnStock());
                         cristaleria1.setDescripcion(cristaleria.getDescripcion());
                         cristaleria1.setPrecio(cristaleria.getPrecio());
                         cristaleria1.setTipo(cristaleria.getTipo());
                         cristaleria1.setPrecioTotal();
                         
                         barra.setListaCristalerias(cristalerias);
                     
                     
                     }
                 }
                 int conteo=0;
                   for (Cristaleria cristaleria3 : cristaleriasUsuario) {
                     if(cristaleria3.getId().equals(idCristaleria)){
                         
                           cristaleria.setBarraPertenecienteNombre(barra.getNombre());
                         cristaleria3.setEnStock(cristaleria.getEnStock());
                         cristaleria3.setDescripcion(cristaleria.getDescripcion());
                         cristaleria3.setPrecio(cristaleria.getPrecio());
                         cristaleria3.setTipo(cristaleria.getTipo());
                         cristaleria3.setPrecioTotal();
                         usuario.setTodasLasCristalerias(cristaleriasUsuario);
                     
                     
                     }
                     conteo=conteo+cristaleria3.getEnStock();
                 }
                
                 
               if(barra.isInsumo()){
                   
                    float suma = barraServicio.calcularPrecioTotalInsumos(cristalerias);
                    barra.setPrecioTotal(suma);
               }
               else{
                    float suma = barraServicio.calcularPrecioTotal(cristalerias);
                    barra.setPrecioTotal(suma);
               
               }
               
                 
                 barra.setTotalUnidades(conteo);
                
                  //usuarioServicio.actualizarListBarras(idUsuario, idBarra);
                 //usuarioServicio.actualizarCapitalTotal(idUsuario);
                 usuarioServicio.actualizarNumeroTotalDeCristalerias(idUsuario);
         
     
     
     
     }
    
    
    public Cristaleria buscarPorId(String id) throws ErrorServicio {

        Optional<Cristaleria> respuesta = cristaleriaRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Cristaleria cristaleria = respuesta.get();
            return cristaleria;
        } else {

            throw new ErrorServicio("No se encontró la cristaleria solicitada");
        }

    }
    
}
