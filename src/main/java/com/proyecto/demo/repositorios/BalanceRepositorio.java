


package com.proyecto.demo.repositorios;


import com.proyecto.demo.entidades.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepositorio extends JpaRepository<Balance,String>{

}
