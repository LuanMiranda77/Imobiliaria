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
import DTO.ImovelDTO;
import Model.Compra;
import Model.Imovel;

public class ContabilidadeBairrosMaisVendidos {
	
	private Document doc = new Document();
	
	public void bairros(String data) {
		Compra compra = new Compra();
		String[] dataNova = data.split("/");
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("docBairro.pdf"));
			doc.open();
			Paragraph p = new Paragraph("Bairro mais vendido do mês");
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
		     
		     PdfPTable tabela2=new PdfPTable(4);
		     float[] tams = {0.2f,0.3f,0.3f,0.2f};
		     tabela2.setWidths(tams);
		     tabela2.addCell(new PdfPCell(new Paragraph("Rua")));
		     tabela2.addCell(new PdfPCell(new Paragraph("Número")));
		     tabela2.addCell(new PdfPCell(new Paragraph("Bairro")));
		     tabela2.addCell(new PdfPCell(new Paragraph("Cidade")));
		     doc.add(tabela2);
		     
		     int cont = 0, soma = 0;
		     String[] comp = null;
			    for(CompraDTO a: compra.listaCompraDTO().getArrayDTO()) {
			    	 Imovel i = new Imovel();
			    	 ImovelDTO idto =  new ImovelDTO();
			    	 idto.setId(a.getIdImovel().getId());
			    	 idto = i.pesquisarImovel(idto);
			    	 String[] bairro = idto.getEnderco().split("-");
			    	 String[] d = a.getDataCompra().split("/");
			    	
			    	 if(d[1].equals(dataNova[0]) && d[2].equals(dataNova[1])) {
			    		 for(CompraDTO b: compra.listaCompraDTO().getArrayDTO()) {
			    			 Imovel e = new Imovel();
					    	 ImovelDTO idto2 =  new ImovelDTO();
					    	 idto2.setId(b.getIdImovel().getId());
					    	 idto2 = e.pesquisarImovel(idto2);
					    	 String[] bairro2 = idto.getEnderco().split("-");
			    			 if(bairro[2].equals(bairro2[2])) {
			    				 cont++;
			    			 }
					    	if(cont>soma) {
					    		soma = cont;
					    		comp = bairro;
					    	}	
			    		 }
			    	 }
			     }
			    for(CompraDTO c :compra.listaCompraDTO().getArrayDTO()) {
			    	 Imovel i = new Imovel();
			    	 ImovelDTO idto =  new ImovelDTO();
			    	 idto.setId(c.getIdImovel().getId());
			    	 idto = i.pesquisarImovel(idto);
			    	 String[] bairro = idto.getEnderco().split("-");
			    	if(bairro[2].equals(comp[2])) {
			    		 PdfPTable tabela3=new PdfPTable(4); 
			     	     tabela3.setWidths(tams);
			    	     tabela3.addCell(new PdfPCell(new Paragraph(bairro[0])));
			    	     tabela3.addCell(new PdfPCell(new Paragraph(bairro[1])));  
			   	         tabela3.addCell(new PdfPCell(new Paragraph(bairro[2])));
			   	         tabela3.addCell(new PdfPCell(new Paragraph(bairro[3])));  
			 	         doc.add(tabela3); 
			    	}
			    }
		
	}catch (FileNotFoundException | DocumentException | NullPointerException e) {
		JOptionPane.showMessageDialog(null, "Erro: "+e);
	}finally {
		doc.close();
	}
	try {
		Desktop.getDesktop().open(new File("docBairro.pdf"));
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Erro: "+e);
	} 
	}
}
