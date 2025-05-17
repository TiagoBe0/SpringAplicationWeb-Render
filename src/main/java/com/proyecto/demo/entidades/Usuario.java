package com.proyecto.demo.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import com.proyecto.demo.enums.Rol;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private String apellido;
    private String mail;
    private String clave;
    //estadisticas
    private int totalDeBarras;
    private float capitalTotal;
  
    private int diasLimpios;
     
    private float costeMensual;
     private float capitalTotalInsumos;
  
    private int diasLimpiosInsumos;
    private int totalInsumos;
    @OneToMany
     private List<Balance> balances;
    private float costeMensualInsumos;
    @ManyToOne
    private Zona zona;
    
    @OneToMany
    private List<Barra> barras;
        @OneToMany
    private List<Pedido> pedidos;
    @OneToMany
    private List<Proveedor> proveedores;
    
    @OneToMany
    private List<Cristaleria> todasLasCristalerias;
     
    @OneToMany
    private List<Ruptura> todasLasRupturas;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    @OneToOne
    private Foto foto;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;

    
    
    private int totalCristalerias;
    
    public List<Barra> getBarras() {
        return barras;
    }

    public float getCapitalTotalInsumos() {
        return capitalTotalInsumos;
    }

    public void setCapitalTotalInsumos(float capitalTotalInsumos) {
        this.capitalTotalInsumos = capitalTotalInsumos;
    }

    public int getTotalInsumos() {
        return totalInsumos;
    }

    public void setTotalInsumos(int totalInsumos) {
        this.totalInsumos = totalInsumos;
    }

    public int getDiasLimpiosInsumos() {
        return diasLimpiosInsumos;
    }

    public void setDiasLimpiosInsumos(int diasLimpiosInsumos) {
        this.diasLimpiosInsumos = diasLimpiosInsumos;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public float getCosteMensualInsumos() {
        return costeMensualInsumos;
    }

    public void setCosteMensualInsumos(float costeMensualInsumos) {
        this.costeMensualInsumos = costeMensualInsumos;
    }

    public void setBarras(List<Barra> barras) {
        this.barras = barras;
    }

    public int getDiasLimpios() {
        return diasLimpios;
    }

    public float getCosteMensual() {
        return costeMensual;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public void setCosteMensual(float costeMensual) {
        this.costeMensual = costeMensual;
    }

    public int getTotalCristalerias() {
        return totalCristalerias;
    }

    public void setTotalCristalerias(int totalCristalerias) {
        this.totalCristalerias = totalCristalerias;
    }

   

   

    public void setDiasLimpios(int diasLimpios) {
        this.diasLimpios = diasLimpios;
    }

    public List<Cristaleria> getTodasLasCristalerias() {
        return todasLasCristalerias;
    }

    public int getTotalDeBarras() {
        return totalDeBarras;
    }

    public void setTotalDeBarras(int totalDeBarras) {
        this.totalDeBarras = totalDeBarras;
    }

    public float getCapitalTotal() {
        return capitalTotal;
    }

    public void setCapitalTotal(float capitalTotal) {
        this.capitalTotal = capitalTotal;
    }

    public void setTodasLasCristalerias(List<Cristaleria> todasLasCristalerias) {
        this.todasLasCristalerias = todasLasCristalerias;
    }

    public List<Ruptura> getTodasLasRupturas() {
        return todasLasRupturas;
    }

    public void setTodasLasRupturas(List<Ruptura> todasLasRupturas) {
        float suma=0.f;
        for (Ruptura r : todasLasRupturas)
        {
            if(!r.isInsumo()){
                
                    suma=suma+r.getCostoRuptura();
            }
            
        }
        setCosteMensual(suma);
        this.todasLasRupturas = todasLasRupturas;
    }
    
    
    
    public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", clave=" + clave + ", zona=" + zona + ", alta=" + alta + ", baja=" + baja + '}';
    }

    
    
}
