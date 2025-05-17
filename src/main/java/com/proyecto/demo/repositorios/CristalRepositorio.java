


package com.proyecto.demo.repositorios;

import com.proyecto.demo.entidades.Cristal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CristalRepositorio extends JpaRepository<Cristal,String>{

}
