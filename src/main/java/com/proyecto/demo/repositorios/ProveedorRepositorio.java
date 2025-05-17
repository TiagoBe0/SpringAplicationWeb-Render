

package com.proyecto.demo.repositorios;


import com.proyecto.demo.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor,String> {

}
