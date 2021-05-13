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

import Control.FuncionarioControl;
import DTO.FuncionarioDTO;
import Model.Funcionario;

public class ContabilidadeDespesasDosFuncionarios {
	private Document doc = new Document();

	public void despesas() {
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("docDespesas.pdf"));
			doc.open();
			Paragraph p = new Paragraph("Despesas dos Funcionários");
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
		     float[] tams = {0.1f,0.4f,0.2f,0.3f};
		     tabela2.setWidths(tams);
		     tabela2.addCell(new PdfPCell(new Paragraph("ID")));
		     tabela2.addCell(new PdfPCell(new Paragraph("FUNCIONARIO")));  
		     tabela2.addCell(new PdfPCell(new Paragraph("CARGO")));  
		     tabela2.addCell(new PdfPCell(new Paragraph("SALÁRIO"))); 
		     doc.add(tabela2);
		     double salario = 0;
		     
		     for(FuncionarioDTO a: FuncionarioControl.listaDeFuncionarioDTO().getLista()) {
		    	 PdfPTable tabela3=new PdfPTable(4); 
	     	     tabela3.setWidths(tams);
	    	     tabela3.addCell(new PdfPCell(new Paragraph(""+a.getId())));
	    	     tabela3.addCell(new PdfPCell(new Paragraph(""+a.getNome())));  
	    	     tabela3.addCell(new PdfPCell(new Paragraph(""+a.getCargo())));  
	   	         tabela3.addCell(new PdfPCell(new Paragraph(""+a.getSalario())));
	   	         doc.add(tabela3);
	   	         salario = salario+a.getSalario();
		     }
		     PdfPTable tabela4=new PdfPTable(2);
		     float[] tama = {0.6f,0.4f};
     	     tabela4.setWidths(tama);
    	     tabela4.addCell(new PdfPCell(new Paragraph("TOTAL")));
    	     tabela4.addCell(new PdfPCell(new Paragraph(""+salario)));
    	     doc.add(tabela4);
		     
	} catch (FileNotFoundException | DocumentException | NullPointerException e) {
		JOptionPane.showMessageDialog(null, "Erro: "+e);
	}finally {
		doc.close();
	}
	try {
		Desktop.getDesktop().open(new File("docDespesas.pdf"));
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Erro: "+e);
	} 
	}
}
