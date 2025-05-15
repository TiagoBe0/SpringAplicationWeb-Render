

package com.proyecto.demo.entidades;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cristaleria {
    
      @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
     private boolean insumo;
     private String tipo;
     private boolean activo=true;
    @OneToOne
     private Barra barraPerteneciente;
    private int totalEnPedido;
    private String barraPertenecienteNombre;
    
    private String idUsuario;
    
     private int enStock;
     @ManyToOne
      Cristal cristalRepo;
     private float precio;
     
     private float precioTotal;
     
     private String descripcion;
        @OneToOne
    private Foto foto;
        
     @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;
    
      private Calendar calendario;
     private int anio;
     private int mes;
     private int dia;
     private int hora;

    public Calendar getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendar calendario) {
        this.calendario = calendario;
        setAnio(calendario.get(Calendar.YEAR));
        setMes(calendario.get(Calendar.MONTH)+1);
        setDia(calendario.get(Calendar.DATE));
        setHora(calendario.get(Calendar.HOUR));
    }

    public int getTotalEnPedido() {
        return totalEnPedido;
    }

    public void setTotalEnPedido(int totalEnPedido) {
        this.totalEnPedido = totalEnPedido;
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
     
     

    public String getId() {
        return id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public boolean isInsumo() {
        return insumo;
    }

    public void setInsumo(boolean insumo) {
        this.insumo = insumo;
    }

    public Cristal getCristalRepo() {
        return cristalRepo;
    }

    public void setCristalRepo(Cristal cristalRepo) {
        this.cristalRepo = cristalRepo;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setPrecioTotal() {
        this.precioTotal = this.precio*this.enStock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Barra getBarraPerteneciente() {
        return barraPerteneciente;
    }

    public void setBarraPerteneciente(Barra barraPerteneciente) {
        //this.barraPertenecienteNombre=barraPerteneciente.getNombre();
        
        this.barraPerteneciente = barraPerteneciente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBarraPertenecienteNombre() {
        return barraPertenecienteNombre;
    }

    public void setBarraPertenecienteNombre(String barraPertenecienteNombre) {
        this.barraPertenecienteNombre = barraPertenecienteNombre;
    }

    public int getEnStock() {
        return enStock;
    }

    public void setEnStock(int enStock) {
        this.enStock = enStock;
        
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
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
