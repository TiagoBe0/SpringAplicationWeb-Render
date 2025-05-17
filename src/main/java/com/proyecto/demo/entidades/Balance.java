

package com.proyecto.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Balance {

       @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
       
       private boolean insumo;
       private float perdidasTotales;
       private float perdidasMensuales;
       private float ingresosTotales;
       private float intresosMensuales;
       @ManyToOne
       private Usuario usuario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInsumo() {
        return insumo;
    }

    public void setInsumo(boolean insumo) {
        this.insumo = insumo;
    }

    public float getPerdidasTotales() {
        return perdidasTotales;
    }

    public void setPerdidasTotales(float perdidasTotales) {
        this.perdidasTotales = perdidasTotales;
    }

    public float getPerdidasMensuales() {
        return perdidasMensuales;
    }

    public void setPerdidasMensuales(float perdidasMensuales) {
        this.perdidasMensuales = perdidasMensuales;
    }

    public float getIngresosTotales() {
        return ingresosTotales;
    }

    public void setIngresosTotales(float ingresosTotales) {
        this.ingresosTotales = ingresosTotales;
    }

    public float getIntresosMensuales() {
        return intresosMensuales;
    }

    public void setIntresosMensuales(float intresosMensuales) {
        this.intresosMensuales = intresosMensuales;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
       
       
}
