

package com.proyecto.demo.entidades;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Ruptura {
    
     @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
     private String nombre;
      private String idUsuario;
     private int numeroDeRuptura;
     private String explicacion;
     @ManyToOne
     private Usuario usuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;
     @OneToOne
     private Cristaleria tipoCristaleria;
     
     private float costoRuptura;
     
     private Calendar calendario;
     private int anio;
     private int mes;
     private int dia;
     private int hora;
     private boolean insumo;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDeRuptura() {
        return numeroDeRuptura;
    }

    public Calendar getCalendario() {
        return calendario;
    }

    public boolean isInsumo() {
        return insumo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setInsumo(boolean insumo) {
        this.insumo = insumo;
    }

   

    public void setCalendario(Calendar calendario) {
        this.calendario = calendario;
        setAnio(calendario.get(Calendar.YEAR));
        setMes(calendario.get(Calendar.MONTH)+1);
        setDia(calendario.get(Calendar.DATE));
        setHora(calendario.get(Calendar.HOUR));
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setNumeroDeRuptura(int numeroDeRuptura) {
        this.numeroDeRuptura = numeroDeRuptura;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
     
     

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
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

    public Cristaleria getTipoCristaleria() {
        return tipoCristaleria;
    }

    public void setTipoCristaleria(Cristaleria tipoCristaleria) {
        this.tipoCristaleria = tipoCristaleria;
    }

    public float getCostoRuptura() {
        return costoRuptura;
    }

    public void setCostoRuptura(float costoRuptura) {
        this.costoRuptura = costoRuptura;
    }
     
     
    

}
