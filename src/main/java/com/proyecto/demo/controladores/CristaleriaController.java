

package com.proyecto.demo.controladores;

import com.proyecto.demo.entidades.Cristaleria;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.servicios.CristaleriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/cristaleria")
public class CristaleriaController {
    
    @Autowired
    private CristaleriaServicio cristaleriaServicio;
    
    
     @GetMapping("/formulario")
    public String form(ModelMap modelo) {
        
        
        return "registroCristaleria.html";
    }
    
    
    
     @PostMapping("/registrar")
    public String registrar(ModelMap modelo,  MultipartFile archivo,@RequestParam String tipo, @RequestParam String idBarra,@RequestParam String descripcion,@RequestParam float precio,@RequestParam int enStock) {

        try {
            cristaleriaServicio.registrar( archivo,  tipo,  descripcion,  precio,  enStock,idBarra);
        } catch (ErrorServicio ex) {
           modelo.put(tipo,"tipo");
            modelo.put(descripcion,"tipo");
             
            return "registro.html";
        }
        modelo.put("titulo", "Bienvenido");
        modelo.put("descripcion", "Cristaleria cargada correctamente");
        return "exito.html";
    }
    
     @PostMapping("/actualizar-perfil")
    public String registrar(MultipartFile archivo, String tipo, String descripcion, float precio, int enStock,String id) throws ErrorServicio {

        Cristaleria cristaleria = new Cristaleria();
       
            cristaleria = cristaleriaServicio.buscarPorId(id);
            //cristaleriaServicio.modificar(archivo, tipo, descripcion, precio, enStock, id);
            

            return "exito.html";
        

    }
    
      @GetMapping("/deshabilitar/{id}")
	public String deshabilitar(ModelMap modelo, @PathVariable String id) {
		try {
			cristaleriaServicio.deshabilitar(id);
			return "inicio";
		}catch(Exception e) {
			modelo.put("error", "No fue posible deshabilitar");
			return "error";
		}
	}

}
