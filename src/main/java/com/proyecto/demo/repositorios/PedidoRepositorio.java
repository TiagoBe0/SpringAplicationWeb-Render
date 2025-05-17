

package com.proyecto.demo.repositorios;


import com.proyecto.demo.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido,String> {

}
