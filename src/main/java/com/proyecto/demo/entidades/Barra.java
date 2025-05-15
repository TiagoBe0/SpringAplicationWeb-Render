

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
public class Barra {

    
    
       @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
       
       private String nombre;
       
       private boolean activa=true;
     @OneToMany
      List<Cristaleria> listaCristalerias;
       @ManyToOne
       private Usuario usuario;
       
       private boolean insumo;
       private float precioTotal;
       private float precioTotalInsumos;
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrecioTotalInsumos() {
        return precioTotalInsumos;
    }

    public void setPrecioTotalInsumos(float precioTotalInsumos) {
        this.precioTotalInsumos = precioTotalInsumos;
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

    public boolean isInsumo() {
        return insumo;
    }

    public void setInsumo(boolean insumo) {
        this.insumo = insumo;
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
