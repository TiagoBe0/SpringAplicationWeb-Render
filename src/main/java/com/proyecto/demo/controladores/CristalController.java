


package com.proyecto.demo.controladores;

import com.proyecto.demo.servicios.CristalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cristal")
public class CristalController {
    
    
    @Autowired
    private CristalServicio cristalServicio;
    
    
    
    
    

}
