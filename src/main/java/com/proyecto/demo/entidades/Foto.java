package com.proyecto.demo.entidades;

import java.util.Base64;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Foto {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private String mime;
    
     private String contenidoBase64; // El nuevo atributo que contendrá la cadena Base64

    

    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContenidoBase64() {
        return contenidoBase64;
    }

    public void setContenidoBase64(String contenidoBase64) {
        this.contenidoBase64 = contenidoBase64;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public byte[] getContenido() {
        return contenido;
    }


    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
        
         if (contenido != null && contenido.length > 0) {
            this.contenidoBase64 = Base64.getEncoder().encodeToString(contenido);
        } else {
            this.contenidoBase64 = null; // O una cadena vacía, según tu preferencia
        }
    }
    
    
    
    
    
}
