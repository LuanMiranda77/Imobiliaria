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
import com.itextpdf.text.pdf.PdfWriter;

public class ContabilidadeCartaCobranca {
	private Document doc = new Document();
	
	public void cartaCobraca() {
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("docCartaCobranca.pdf"));
			doc.open();
			Paragraph p = new Paragraph("Carta de Cobrança");
			p.setAlignment(0);
			doc.add(p);
			p = new Paragraph("\n");
			doc.add(p);
			Image im;
			Image logo;
			try {
				im = Image.getInstance("Icon/logo_pequena.png");
				im.scaleToFit(150,150);
				im.setAlignment(1);
				doc.add(im);
				logo = Image.getInstance("icon/carta-cobranca.png");
				logo.scaleToFit(2500, 550);
			    logo.setAlignment(1);
			     doc.add(logo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		}catch (FileNotFoundException | DocumentException  | NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Erro: "+e);
		}finally {
			doc.close();
		}
		try {
			Desktop.getDesktop().open(new File("docCartaCobranca.pdf"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro: "+e);
		} 
	}

}
