

package com.proyecto.demo.servicios;

import com.proyecto.demo.entidades.Balance;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.repositorios.BalanceRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServicio {

    @Autowired
    private BalanceRepositorio balanceRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Transactional
    private void registrar(String id) throws ErrorServicio{
        
        Balance balance = new Balance();
        balance.setIngresosTotales(0);
        balance.setPerdidasMensuales(0);
        balance.setUsuario(usuarioServicio.buscarPorId(id));
          
        
    
    balanceRepositorio.save(balance);
    }
}
