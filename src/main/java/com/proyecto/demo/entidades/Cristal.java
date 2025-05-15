


package com.proyecto.demo.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Cristal {
    
     @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
     
    

    private String nombre;
    @OneToMany
      List<Cristaleria> listaCristalerias;
    @OneToOne
    private Foto foto;
    
    private boolean insumo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isInsumo() {
        return insumo;
    }

    public void setInsumo(boolean insumo) {
        this.insumo = insumo;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public List<Cristaleria> getListaCristalerias() {
        return listaCristalerias;
    }

    public void setListaCristalerias(List<Cristaleria> listaCristalerias) {
        this.listaCristalerias = listaCristalerias;
    }
    
    
    
    
    
    
    
    

}
