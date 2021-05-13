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
import Model.CalcularPrcentagem;
import Model.Compra;

public class ContabilidadeLucroMes {
 private Document  doc = new Document();
 
 public void lucroNoMes(String data) {
	 Compra compra = new Compra();
		String[] dataNova = data.split("/");
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("docLucroMes.pdf"));
			doc.open();
			Paragraph p = new Paragraph("Lucro do Mês");
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
		     tabela2.addCell(new PdfPCell(new Paragraph("ID")));
		     tabela2.addCell(new PdfPCell(new Paragraph("CPF")));  
		     tabela2.addCell(new PdfPCell(new Paragraph("FUNCIONARIO"))); 
		     tabela2.addCell(new PdfPCell(new Paragraph("PREÇO")));
		     tabela2.addCell(new PdfPCell(new Paragraph("% DA COMPRA")));
		     doc.add(tabela2);
		     double soma = 0, cont = 0;
		     CalcularPrcentagem cp = new CalcularPrcentagem();
		     for(CompraDTO a: compra.listaCompraDTO().getArrayDTO()) {
		    	 String[] d = a.getDataCompra().split("/");
		    	 if(d[1].equals(dataNova[0]) && d[2].equals(dataNova[1])) {
		    		cont = cp.calcularPorcentagem(a.getIdImovel().getPreco());
		    		System.out.print(a.getIdFuncionario().getId());
				    soma = soma + cont;		
				    PdfPTable tabela3 = new PdfPTable(5);
				     tabela3.setWidths(tams);
				     tabela3.addCell(new PdfPCell(new Paragraph(a.getId()+"")));
				     tabela3.addCell(new PdfPCell(new Paragraph(a.getCliente().getCpf())));  
				     tabela3.addCell(new PdfPCell(new Paragraph(a.getIdFuncionario().getNome()+""))); 
				     tabela3.addCell(new PdfPCell(new Paragraph(a.getIdImovel().getPreco()+"")));
				     tabela3.addCell(new PdfPCell(new Paragraph(cont+"")));
				     doc.add(tabela3);
		    	}
		     }
		     PdfPTable tabela3=new PdfPTable(2);
		     float[] tama = {0.6f,0.4f};
     	     tabela3.setWidths(tama);
    	     tabela3.addCell(new PdfPCell(new Paragraph("TOTAL")));
    	     tabela3.addCell(new PdfPCell(new Paragraph(""+soma)));
    	     doc.add(tabela3);
		     
		     
			} catch (FileNotFoundException | DocumentException | NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e);
				}finally {
					doc.close();
				}
				try {
					Desktop.getDesktop().open(new File("docLucroMes.pdf"));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e);
				} 
			}

		     
 }

