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

public class ContabilidadeImoveisVendidos {
	
	private Document doc = new Document();
	
	public void imoveisVendidos(String data) {
		Compra compra = new Compra();
		String[] dataNova = data.split("/");
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("docImoveisVendidos.pdf"));
			doc.open();
			Paragraph p = new Paragraph("Imóveis vendidos no mês");
			p.setAlignment(0);
			doc.add(p);
			p = new Paragraph("\n");
			doc.add(p);
			Image logo;
			try {
				logo = Image.getInstance("logo.jpeg");
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
		     float[] tams = {0.1f,0.2f,0.2f,0.2f,0.2f};
		     tabela2.setWidths(tams);
		     tabela2.addCell(new PdfPCell(new Paragraph("ID")));
		     tabela2.addCell(new PdfPCell(new Paragraph("TIPO")));  
		     tabela2.addCell(new PdfPCell(new Paragraph("CLIENTE"))); 
		     tabela2.addCell(new PdfPCell(new Paragraph("PREÇO")));
		     tabela2.addCell(new PdfPCell(new Paragraph("DATA")));
		     doc.add(tabela2);
		     
		     for(CompraDTO c: compra.listaCompraDTO().getArrayDTO()) {
		    	String[] d = c.getDataCompra().split("/");
		    	if(d[1].equals(dataNova[0]) && d[2].equals(dataNova[1])) {
		    		PdfPTable tabela = new PdfPTable(5);
		    		tabela.setWidths(tams);
		    		tabela.addCell(new PdfPCell(new Paragraph(c.getIdImovel().getId()+"")));
		    		tabela.addCell(new PdfPCell(new Paragraph(c.getIdImovel().getTipo().getNome())));
		    		tabela.addCell(new PdfPCell(new Paragraph(c.getCliente().getNome())));
		    		tabela.addCell(new PdfPCell(new Paragraph(""+c.getIdImovel().getPreco())));
		    		tabela.addCell(new PdfPCell(new Paragraph(c.getDataCompra())));
		    		doc.add(tabela);
		    	}
		     }
	} catch (FileNotFoundException | DocumentException  | NullPointerException e) {
		JOptionPane.showMessageDialog(null, "Erro: "+e);
	}finally {
		doc.close();
	}
	try {
		Desktop.getDesktop().open(new File("docImoveisVendidos.pdf"));
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Erro: "+e);
	} 
	}

}
