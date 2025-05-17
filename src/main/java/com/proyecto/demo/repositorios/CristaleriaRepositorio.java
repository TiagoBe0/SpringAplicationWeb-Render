

package com.proyecto.demo.repositorios;

import com.proyecto.demo.entidades.Cristaleria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CristaleriaRepositorio extends JpaRepository<Cristaleria,String> {
  
    @Query("SELECT c FROM Cristaleria c WHERE c.idUsuario = :mail")
    public List<Cristaleria> buscarPorIdUsuario(@Param("mail") String mail);
    
}
