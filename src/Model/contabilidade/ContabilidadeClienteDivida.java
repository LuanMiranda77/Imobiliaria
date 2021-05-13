package Model.contabilidade;
/**
 * 
 * @author André
 * 
 */
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Control.ClienteControl;
import Control.DividaControl;
import DTO.ClienteDTO;
import DTO.DividaDTO;

public class ContabilidadeClienteDivida {
	private Document doc = new Document();
	private ClienteDTO cliente = new ClienteDTO();
	
	public void ClienteDivida() {
		  Date d = new Date();
	        String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
	        String [] data = hoje.split("/");
	        int dia =Integer.parseInt(data[0]); 
	        int mes =Integer.parseInt(data[1]); 
	        int ano = Integer.parseInt(data[2]); 
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("docDividas.pdf"));
			doc.open();
			Paragraph p = new Paragraph("Lista de clientes com dívidas");
			p.setAlignment(0);
			doc.add(p);
			p = new Paragraph("\n");
			doc.add(p);
			Image logo;
			try {
				logo = Image.getInstance("Icon/logo_pequena.png");
				logo.scaleToFit(150, 150);
			     logo.setAlignment(1);
			     doc.add(logo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			
			 PdfPTable tabEmpresa=new PdfPTable(2);
		     float[] t = {0.6f,0.4f};
		     tabEmpresa.setWidths(t);
		     tabEmpresa.addCell(new PdfPCell(new Paragraph("Empresa: Vila Nova Imobiliaria")));
		     tabEmpresa.addCell(new PdfPCell(new Paragraph("CNPJ: 39.485.348/00012-98")));
		     doc.add(tabEmpresa);
		     
		     PdfPTable tabela2=new PdfPTable(5);
		     float[] tams = {0.2f,0.3f,0.3f,0.2f,0.3f};
		     tabela2.setWidths(tams);
		     tabela2.addCell(new PdfPCell(new Paragraph("ID DÍVIDA")));
		     tabela2.addCell(new PdfPCell(new Paragraph("CPF")));  
		     tabela2.addCell(new PdfPCell(new Paragraph("NOME")));  
		     tabela2.addCell(new PdfPCell(new Paragraph("Valor")));
		     tabela2.addCell(new PdfPCell(new Paragraph("Data")));
		     doc.add(tabela2);
		     for(DividaDTO a: DividaControl.lista().getLista()) {
		    	 String []div =a.getData().split("/");
		    	 cliente =a.getCliente();
		    	 int diadv=Integer.parseInt(div[0]); 
		    	 int mesdv=Integer.parseInt(div[1]); 
		    	 int anodv=Integer.parseInt(div[2]); 
		    	 if(mes>mesdv && ano>=anodv) {
		    		 PdfPTable tabela3=new PdfPTable(5); 
		     	     tabela3.setWidths(tams);
		    	     tabela3.addCell(new PdfPCell(new Paragraph(""+ a.getId())));
		    	     tabela3.addCell(new PdfPCell(new Paragraph(cliente.getCpf())));  
		    	     tabela3.addCell(new PdfPCell(new Paragraph(cliente.getNome())));  
		   	         tabela3.addCell(new PdfPCell(new Paragraph(""+a.getPreco())));
		   	         tabela3.addCell(new PdfPCell(new Paragraph(""+a.getData())));
		 	         doc.add(tabela3); 
		    	 }
		    	 else if(dia>diadv && mes>=mesdv) {
		    		 PdfPTable tabela3=new PdfPTable(5); 
		     	     tabela3.setWidths(tams);
		    	     tabela3.addCell(new PdfPCell(new Paragraph(""+ a.getId())));
		    	     tabela3.addCell(new PdfPCell(new Paragraph(cliente.getCpf())));  
		    	     tabela3.addCell(new PdfPCell(new Paragraph(cliente.getNome())));  
		   	         tabela3.addCell(new PdfPCell(new Paragraph(""+a.getPreco())));
		   	         tabela3.addCell(new PdfPCell(new Paragraph(""+a.getData())));
		 	         doc.add(tabela3); 
		    	 }
		    	 
		     }
	} catch (FileNotFoundException | DocumentException | NullPointerException e) {
		JOptionPane.showMessageDialog(null, "Erro: "+e);
	}finally {
		doc.close();
	}
	try {
		Desktop.getDesktop().open(new File("docDividas.pdf"));
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Erro: "+e);
	} 
}

}
