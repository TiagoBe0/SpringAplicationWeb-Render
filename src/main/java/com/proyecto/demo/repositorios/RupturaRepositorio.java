

package com.proyecto.demo.repositorios;

import com.proyecto.demo.entidades.Cristaleria;
import com.proyecto.demo.entidades.Ruptura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RupturaRepositorio extends JpaRepository<Ruptura,String> {

}
