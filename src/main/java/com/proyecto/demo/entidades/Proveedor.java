

package com.proyecto.demo.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Proveedor {

    
    
       @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
       
       private String nombre;
        private String link;
       private boolean activa=true;
     @OneToMany
      List<Cristaleria> listaCristalerias;
       @ManyToOne
       private Usuario usuario;
       
        private long celular;
       private float precioTotal;
       
       private int totalUnidades;
        @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    public String getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cristaleria> getListaCristalerias() {
        return listaCristalerias;
    }

    public void setListaCristalerias(List<Cristaleria> listaCristalerias) {
        this.listaCristalerias = listaCristalerias;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(int totalUnidades) {
        this.totalUnidades = totalUnidades;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getBaja() {
        return baja;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }
    
    
       
}
