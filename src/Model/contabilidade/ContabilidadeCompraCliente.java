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

public class ContabilidadeCompraCliente {
	private Document doc = new Document();
	
	public void compraCliente(String cpf) {
		Compra compra = new Compra();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("docCompraCliente.pdf"));
			doc.open();
			Paragraph p = new Paragraph("Compras de cada cliente");
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
		     
		     PdfPTable tabela2=new PdfPTable(6);
		     float[] tams = {0.2f,0.2f,0.2f,0.1f,0.15f,0.15f};
		     tabela2.setWidths(tams);
		     tabela2.addCell(new PdfPCell(new Paragraph("CPF")));
		     tabela2.addCell(new PdfPCell(new Paragraph("NOME"))); 
		     tabela2.addCell(new PdfPCell(new Paragraph("TELEFONE")));
		     tabela2.addCell(new PdfPCell(new Paragraph("ID"))); 
		     tabela2.addCell(new PdfPCell(new Paragraph("PREÇO")));
		     tabela2.addCell(new PdfPCell(new Paragraph("DATA")));
		     doc.add(tabela2);
		     
		     for(CompraDTO c: compra.listaCompraDTO().getArrayDTO()) {
		    	 if(c.getCliente().getCpf().equals(cpf)) {
		    		 PdfPTable tabela = new PdfPTable(6);
		    		 tabela.setWidths(tams);
		    		 tabela.addCell(new PdfPCell(new Paragraph(c.getCliente().getCpf())));
		    		 tabela.addCell(new PdfPCell(new Paragraph(c.getCliente().getNome())));
		    		 tabela.addCell(new PdfPCell(new Paragraph(c.getCliente().getTelefone())));
		    		 tabela.addCell(new PdfPCell(new Paragraph(c.getIdImovel().getId())));
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
		Desktop.getDesktop().open(new File("docCompraCliente.pdf"));
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Erro: "+e);
	} 
}

}
