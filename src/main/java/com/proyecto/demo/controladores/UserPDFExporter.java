

package com.proyecto.demo.controladores;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Jpeg;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.proyecto.demo.entidades.Barra;
import com.proyecto.demo.entidades.Cristaleria;
import com.proyecto.demo.entidades.Pedido;
import com.proyecto.demo.entidades.Ruptura;
import com.proyecto.demo.entidades.Usuario;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


public class UserPDFExporter {
    
    private List<Usuario> listUsers;
    private List<Cristaleria> cristalerias;
    private List<Ruptura> rupturas;
    private List<Barra> barras;
    private List<Pedido> pedidos;

    public UserPDFExporter(List<Usuario> listUsers, List<Cristaleria> cristalerias, List<Ruptura> rupturas, List<Barra> barras, List<Pedido> pedidos) {
        this.listUsers = listUsers;
        this.cristalerias = cristalerias;
        this.rupturas = rupturas;
        this.barras = barras;
        this.pedidos = pedidos;
    }

  
    
    
    private void writeTableHeader(){
    
    }
    
     private void writeTableData(){
    
    }
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Stock", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Tipo", font));
        table.addCell(cell);
        
       
         
        cell.setPhrase(new Phrase("Foto", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Coste Unitario", font));
        table.addCell(cell);
        //cell.setPhrase(new Phrase("Descripcion", font));
        //table.addCell(cell);
         
        cell.setPhrase(new Phrase("Barra", font));
        table.addCell(cell);       
    }
     private void writeTableHeaderInsumos(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Stock", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Tipo", font));
        table.addCell(cell);
        
       
         
        cell.setPhrase(new Phrase("Foto", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Coste Unitario", font));
        table.addCell(cell);
        //cell.setPhrase(new Phrase("Descripcion", font));
        //table.addCell(cell);
         
        cell.setPhrase(new Phrase("Barra", font));
        table.addCell(cell);       
    }
      private void writeTableHeaderRuptura(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Cantidad de Rupturas", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Tipo", font));
        table.addCell(cell);
        
       
         
        cell.setPhrase(new Phrase("Nombre Responsable", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Coste Unitario", font));
        table.addCell(cell);
         
        //cell.setPhrase(new Phrase("Explicacion", font));
        //table.addCell(cell);       
        
        cell.setPhrase(new Phrase("Fecha", font));
        table.addCell(cell);  
    }
    private void writeTableData(PdfPTable table) {
        for (Usuario user : listUsers) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getMail());
            table.addCell(user.getNombre());
            table.addCell(user.getApellido());
            table.addCell(String.valueOf(user.getAlta()));
        }
    }
    
    
    
    
       private void writeTableDataCristalerias(PdfPTable table) throws BadElementException, IOException {
           
           
        for (Cristaleria c : cristalerias) {
            if(!c.isInsumo()){
            Image imagen = new Jpeg(c.getFoto().getContenido(), 10.0f, 10.0f);
            table.addCell(String.valueOf(c.getEnStock()));
            table.addCell(c.getTipo());
           
            table.addCell(imagen);
             
            table.addCell(String.valueOf(c.getPrecio())+"$");
            
            //table.addCell(c.getDescripcion());
            table.addCell(c.getBarraPertenecienteNombre());
        }
    }
       }
       
       //escribe tabla de insumos
         private void writeTableDataInsumos(PdfPTable table) throws BadElementException, IOException {
           
           
        for (Cristaleria c : cristalerias) {
            if(c.isInsumo()){
            Image imagen = new Jpeg(c.getFoto().getContenido(), 10.0f, 10.0f);
            table.addCell(String.valueOf(c.getEnStock()));
            table.addCell(c.getTipo());
           
            table.addCell(imagen);
             
            table.addCell(String.valueOf(c.getPrecio())+"$");
            
            //table.addCell(c.getDescripcion());
            table.addCell(c.getBarraPertenecienteNombre());
        }
        }
    }
       //llena los campos del pdf rupturas
        private void writeTableDataRupturas(PdfPTable table) throws BadElementException, IOException {
           
           
        for (Ruptura r : rupturas) {
            if(!r.isInsumo()){
           
            table.addCell(String.valueOf(r.getNumeroDeRuptura()));
            table.addCell(r.getTipoCristaleria().getTipo());
           
            table.addCell(r.getNombre());
            table.addCell(String.valueOf(r.getCostoRuptura())+"$");
            table.addCell(r.getExplicacion());
            //table.addCell(String.valueOf(r.getDia()+"/"+r.getAnio()));
        }
        }
    }
         //llena los campos del pdf rupturas
        private void writeTableDataVencimientos(PdfPTable table) throws BadElementException, IOException {
           
           
        for (Ruptura r : rupturas) {
            if(r.isInsumo()){
           
            table.addCell(String.valueOf(r.getNumeroDeRuptura()));
            table.addCell(r.getTipoCristaleria().getTipo());
           
            table.addCell(r.getNombre());
            table.addCell(String.valueOf(r.getCostoRuptura())+"$");
            table.addCell(r.getExplicacion());
            //table.addCell(String.valueOf(r.getDia()+"/"+r.getAnio()));
        }
        }
    }
     //exporta el pdf cristalerias
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Lista de Cristalerias", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableDataCristalerias(table);
         
        document.add(table);
         
        document.close();
         
    }
    //exporta el pdf
     public void exportRuptura(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A6.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Lista de Rupturas", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeaderRuptura(table);
        writeTableDataRupturas(table);
         
        document.add(table);
         
        document.close();
         
    }
      //exporta el pdf
     public void exportVencimientos(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A6.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Lista de Vencimientos", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeaderRuptura(table);
        writeTableDataVencimientos(table);
         
        document.add(table);
         
        document.close();
         
    }
    //export insumo
      public void exportInsumos(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Lista de Cristalerias", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeaderInsumos(table);
        writeTableDataInsumos(table);
         
        document.add(table);
         
        document.close();
         
    }

}
