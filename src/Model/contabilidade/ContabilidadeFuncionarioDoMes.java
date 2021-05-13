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
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import DTO.CompraDTO;
import Model.Compra;


public class ContabilidadeFuncionarioDoMes {
	private Document doc = new Document();
	
	public void FuncionarioVendas(String data) {
		Compra compra = new Compra();
		String[] dataNova = data.split("/");
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("doc.pdf"));
			doc.open();
			Paragraph p = new Paragraph("Vendas dos Funcionários");
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
		     float[] tams = {0.1f,0.3f,0.3f,0.15f,0.2f};
		     tabela2.setWidths(tams);
		     tabela2.addCell(new PdfPCell(new Paragraph("ID I.")));
		     tabela2.addCell(new PdfPCell(new Paragraph("CPF")));  
		     tabela2.addCell(new PdfPCell(new Paragraph("FUNCIONARIO"))); 
		     tabela2.addCell(new PdfPCell(new Paragraph("PREÇO")));
		     tabela2.addCell(new PdfPCell(new Paragraph("DATA")));
		     doc.add(tabela2);
		    int cont = 0, soma = 0;
		    CompraDTO cdto = new CompraDTO();
		    for(CompraDTO a: compra.listaCompraDTO().getArrayDTO()) {
		    	 String[] d = a.getDataCompra().split("/");
		    	 if(d[1].equals(dataNova[0]) && d[2].equals(dataNova[1])) {
		    		 for(CompraDTO b: compra.listaCompraDTO().getArrayDTO()) {
		    			 if(a.getIdFuncionario().getId()==b.getIdFuncionario().getId()) {
		    				 cont++;
		    			 }
				    	if(cont>soma) {
				    		soma = cont;
				    		cdto = a;
				    	}	
		    		 }
		    	 }
		     }
		    for(CompraDTO c :compra.listaCompraDTO().getArrayDTO()) {
		    	if(c.getIdFuncionario().getId()==cdto.getIdFuncionario().getId()) {
		    		 PdfPTable tabela3=new PdfPTable(5); 
		     	     tabela3.setWidths(tams);
		    	     tabela3.addCell(new PdfPCell(new Paragraph(""+c.getId())));
		    	     tabela3.addCell(new PdfPCell(new Paragraph(""+c.getCliente().getCpf())));  
		   	         tabela3.addCell(new PdfPCell(new Paragraph(""+c.getIdFuncionario().getNome())));
		   	         tabela3.addCell(new PdfPCell(new Paragraph(""+c.getIdImovel().getPreco())));
		 	         tabela3.addCell(new PdfPCell(new Paragraph(""+c.getDataCompra())));  
		 	         doc.add(tabela3); 
		    	}
		    }
		} catch (FileNotFoundException | DocumentException  | NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Erro: "+e);
		}finally {
			doc.close();
		}
		try {
			Desktop.getDesktop().open(new File("doc.pdf"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro: "+e);
		} 
	}
}
