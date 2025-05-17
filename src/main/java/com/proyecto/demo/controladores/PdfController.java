

package com.proyecto.demo.controladores;

import com.proyecto.demo.entidades.Cristaleria;
import com.proyecto.demo.entidades.Ruptura;
import com.proyecto.demo.entidades.Usuario;
import com.proyecto.demo.errores.ErrorServicio;
import com.proyecto.demo.servicios.BarraServicio;
import com.proyecto.demo.servicios.CristaleriaServicio;
import com.proyecto.demo.servicios.PedidoServicio;
import com.proyecto.demo.servicios.RupturaServicio;
import com.proyecto.demo.servicios.UsuarioServicio;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    private UsuarioServicio usuarioServicio;
     @Autowired
    private CristaleriaServicio cristaleriaServicio;

    @Autowired
    private RupturaServicio rupturaServicio;
    @Autowired
    private BarraServicio barraServicio;
    @Autowired
    private PedidoServicio pedidoServicio;
    
    @GetMapping("/cristalerias/{id}")
    public void exportToPDF(HttpServletResponse response,@PathVariable String id) throws IOException, ErrorServicio{
        response.setContentType("application/pdf");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=cristalerias.pdf";
        response.setHeader(headerKey, headerValue);
        
        List<Cristaleria> cristalerias= cristaleriaServicio.listarCristaleriasPorIdUsuario(id);
         UserPDFExporter exporter = new UserPDFExporter(null, cristalerias, null, null, null);
          exporter.export(response);
    
    
    
    }
    
     @GetMapping("/insumo/{id}")
    public void exportToPDFInsumos(HttpServletResponse response,@PathVariable String id) throws IOException, ErrorServicio{
        response.setContentType("application/pdf");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=insumos.pdf";
        response.setHeader(headerKey, headerValue);
        
        List<Cristaleria> cristalerias= cristaleriaServicio.listarInsumosPorIdUsuario(id);
         UserPDFExporter exporter = new UserPDFExporter(null, cristalerias, null, null, null);
          exporter.exportInsumos(response);
    
    
    
    }
    
     @GetMapping("/ruptura/{id}")
    public void exportToPDFRuptura(HttpServletResponse response,@PathVariable String id) throws IOException, ErrorServicio{
        response.setContentType("application/pdf");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=rupturas.pdf";
        response.setHeader(headerKey, headerValue);
        
        List<Ruptura> rupturas= rupturaServicio.listarTodasRupturas(id);
         UserPDFExporter exporter = new UserPDFExporter(null, null, rupturas, null, null);
          exporter.exportRuptura(response);
    
    
    
    }
    
     @GetMapping("/vencimiento/{id}")
    public void exportToPDFVencimientos(HttpServletResponse response,@PathVariable String id) throws IOException, ErrorServicio{
        response.setContentType("application/pdf");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=vencimientos.pdf";
        response.setHeader(headerKey, headerValue);
        
        List<Ruptura> rupturas= rupturaServicio.listarTodasRupturas(id);
         UserPDFExporter exporter = new UserPDFExporter(null, null, rupturas, null, null);
          exporter.exportVencimientos(response);
    
    
    
    }
   
}
