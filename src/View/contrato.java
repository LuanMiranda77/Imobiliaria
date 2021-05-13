package View;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import DTO.ClienteDTO;
import DTO.ImovelDTO;
import DTO.TipoImovelDTO;
import Model.Extenso;

public class contrato {
	
	
		
	//|@AUTOR LUAN MIRANDA
	
	public contrato(ClienteDTO cliente,ImovelDTO imovel) throws DocumentException, IOException{
		Document historico=new Document();
    	String arquivoPdf="contrato.pdf";
    	 //adata do pedido
	     Date d = new Date();
	        String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
	
		 PdfWriter.getInstance(historico, new FileOutputStream(arquivoPdf));
		 historico.open();
	     
	    
	     
	    Image logo = Image.getInstance("Icon/logo_pequena.png");
	     logo.scaleToFit(150, 150);
	     logo.setAlignment(1);
	     historico.add(logo);
	     
	     Paragraph p  =new Paragraph();
	     p.setAlignment(0);
	     historico.add(p);
	     p=new Paragraph("\n\n");
	     historico.add(p);
	     
	     PdfPTable titulo=new PdfPTable(1);
	     PdfPCell titulocel=new PdfPCell(new Paragraph("ESCRITURA PARTICULAR DE COMPRA E VENDA \n" ));
	     titulocel.setBackgroundColor(new BaseColor(139, 137, 137));
	     titulocel.setBorder(5);
	     titulocel.setHorizontalAlignment(1);
	     titulo.addCell(titulocel);
	     historico.add(titulo);
	     
	     Paragraph p1  =new Paragraph("SAIBAM, quanto este particular instrumento de contrato de compromisso de compra e venda virem na "
	    		 					+ "Data: " + hoje + " nesta cidade e Comarca de Monteiro, Estado das Paraíba, os abaixo qualificados:" );
	     p1.setAlignment(0);
	     historico.add(p1);
	     p1=new Paragraph("\n");
	     historico.add(p1);
	     
	          
	     //VENDEDOR
	     PdfPTable titulo1=new PdfPTable(1);
	     PdfPCell celEmp=new PdfPCell(new Paragraph("OUTORGANTE VENDEDOR:"));
	     celEmp.setBackgroundColor(new BaseColor(139, 137, 137));
	     celEmp.setBorder(5);
	     celEmp.setHorizontalAlignment(1);
	     titulo1.addCell(celEmp);
	     historico.add(titulo1);
	     
	    //Nome da Empresa CNPJ
	     PdfPTable tbCliente=new PdfPTable(2);
	     float[] t = {0.6f,0.4f};
	     tbCliente.setWidths(t);
	     tbCliente.addCell(new PdfPCell(new Paragraph("Empresa: Imobiliaria Vila nova")));
	     tbCliente.addCell(new PdfPCell(new Paragraph("CNPJ: 32.111.324/0001-52")));
	     historico.add(tbCliente);
	     
	     //Cp da Empresa
	     PdfPTable endCliente=new PdfPTable(2);
	     float[] t1 = {0.6f,0.4f};
	     endCliente.setWidths(t1);
	     endCliente.addCell(new PdfPCell(new Paragraph("Eendereço: Rua das Benção, nº 35")));
	     endCliente.addCell(new PdfPCell(new Paragraph("Cidade: Monteiro-PB")));
	     historico.add(endCliente);
	     
	     PdfPTable cepCliente1a=new PdfPTable(2);
	     float[] t4= {0.6f,0.4f};
	     cepCliente1a.setWidths(t4);
	     cepCliente1a.addCell(new PdfPCell(new Paragraph("Bairro: Centro")));
	     cepCliente1a.addCell(new PdfPCell(new Paragraph("Cep: 58500-000")));
	     historico.add(cepCliente1a);
	     
	     p=new Paragraph("\n");
	     historico.add(p);
	     
	     //COMPRADOR
	     PdfPTable titulo2=new PdfPTable(1);
	     PdfPCell celEmp1=new PdfPCell(new Paragraph("OUTORGANTE COMPRADOR:"));
	     celEmp1.setBackgroundColor(new BaseColor(139, 137, 137));
	     celEmp1.setBorder(5);
	     celEmp1.setHorizontalAlignment(1);
	     titulo2.addCell(celEmp1);
	     historico.add(titulo2);
	     
	     //compra dor do imovel
	     PdfPTable tbCliente1=new PdfPTable(2);
	     float[] tt = {0.6f,0.4f};
	     tbCliente1.setWidths(tt);
	     tbCliente1.addCell(new PdfPCell(new Paragraph("Nome: "+ cliente.getNome())));
	     tbCliente1.addCell(new PdfPCell(new Paragraph("CPF:"+ cliente.getCpf())));
	     historico.add(tbCliente1);
	     
	     //Cp da Empresa
	     PdfPTable endCliente1=new PdfPTable(2);
	     float[] t2 = {0.6f,0.4f};
	     endCliente1.setWidths(t2);
	     endCliente1.addCell(new PdfPCell(new Paragraph("Endereço: "+cliente.getEndereco())));
	     endCliente1.addCell(new PdfPCell(new Paragraph("Telefone: "+cliente.getTelefone())));
	     historico.add(endCliente1);
	     
	     PdfPTable cepCliente1=new PdfPTable(2);
	     float[] t5= {0.6f,0.4f};
	     cepCliente1.setWidths(t5);
	     cepCliente1.addCell(new PdfPCell(new Paragraph("Bairro: Centro")));
	     cepCliente1.addCell(new PdfPCell(new Paragraph("Cep: 58500-000")));
	     historico.add(cepCliente1);
	     
	     p=new Paragraph("\n");
	     historico.add(p);
	     
	     Paragraph p2  =new Paragraph("CONTRATAM A COMPRA E VENDA DE UM IMOVEL"+"("+imovel.getTipo().getNome()+") "
	    		+imovel.getEnderco()+", "+imovel.getEstado()+","
	           +" COM O COMPRIMENTO "+imovel.getFrente()+" DE FRENTE, POR "+imovel.getFundo()+" NOS FUNDOS, PERVAZENDO UMA AREIA DE "+imovel.getAreaQuadrada()+" METROS QUADRADOS.\r\n\n" + 
	     		"Pelo preço abaixo estipulado e acordado entre ambas as partes:\n\n"
	     		+"1º - O preço de venda é de R$ "+imovel.getPreco()+" ("+new Extenso(imovel.getPreco()).toString()+")"+" que o COMPRADOR paga ao VENDEDOR no ato da assinatura deste contrato.\n\n" + 
	     		"2º - O VENDEDOR se obriga a outorgar ao COMPRADOR, ou a quem for por ele indicado, a escritura definitiva de compra e venda do imóvel objeto do presente contrato;\n\n" + 
	     		"3º - O COMPRADOR tomará posse do imóvel depois de ter pagado o total da quantia estipulada neste contrato;\r\n\n" + 
	     		"4º - Ficam a cargo do COMPRADOR todas as despesas de escrituração definitiva, inclusive as de registro, e bem assim quaisquer impostos que de ora em diante pesem sobre o imóvel;\r\n\n" + 
	     		"5º - Se os VENDEDORES, cumpridos pelo COMPRADOR as obrigações por este assumidas, se recusarem de dar-lhe escritura definitiva, serão obrigados a restituir-lhe em dobro as quantias recebidas;\r\n\n" + 
	     		"6º - Se o COMPRADOR deixar de cumprir as obrigações aqui assumidas, inclusive a do pagamento do valor de compra, ficam o VENDEDOR com o direito de haver como rescindido  este contrato, perdendo o COMPRADOR  em favor  do VENDEDOR todas as despesas já pagas, sem direito a indenização alguma, devendo somente receber do VENDEDOR  aquantia já paga por esta compra;\r\n" + 
	     		"7º - as penas previstas neste contrato se farão efetivas independentemente de interpelação judicial ou extrajudicial;\r\n\n" + 
	     		"8º - os contratantes fazem expressos renuncia de foro, por mais privilegiado que seja escolhendo para qualquer questão relativa ao presente contrato o foro da comarca de Monteiro-PB;\r\n\n" + 
	     		"9º - O presente contrato obriga em todos os seus termos não só os contratantes como seus herdeiros e sucessores, em conformidade com o Código Civil Brasileiro;\r\n\n" + 
	     		"E assim, por estarem justos e contratados, assinam o presente instrumento em (02) duas vias, de igual teor e forma, juntamente com as testemunhas, a tudo presentes. " );
				p2.setAlignment(0);
				historico.add(p2);
				p2=new Paragraph("\n\n");
				historico.add(p2);
				
				Paragraph p3  =new Paragraph("Monteiro-PB, "+"Data: "+hoje);
				p3.setAlignment(0);
				historico.add(p3);
				p3=new Paragraph("\n\n");
				historico.add(p3);
				
				Paragraph p4  =new Paragraph("\n\n\n_____________________________________________________\n"
	 					+ "VENDEDOR: IMOBILIARIA VILA NOVA" );
				p4.setAlignment(1);
				historico.add(p4);
				p4=new Paragraph("\n\n");
				historico.add(p4);
				
				Paragraph p5  =new Paragraph("\n\n\n_____________________________________________________\n"
	 					+ "COMPRADOR: "+cliente.getNome()+" CPF: "+cliente.getCpf() );
				p5.setAlignment(1);
				historico.add(p5);
				p5=new Paragraph("\n\n\n\n");
				historico.add(p5);
				
				Paragraph p6  =new Paragraph("__________________________________  "+"   _____________________________________\n"
	 					+ "1º Testemunha                  "+"                                   2º Testemunha ");
				p6.setAlignment(1);
				historico.add(p6);
				p6=new Paragraph("\n");
				historico.add(p6);
						    
	 
	     
	         
	     	   historico.close();
	         Desktop.getDesktop().open(new File("contrato.pdf"));
	         
	
	
}
	public static void main(String[] args)  {
		try {
			ClienteDTO luan =new ClienteDTO();
			luan.setCpf("6545454+616+1");
			luan.setNome("LUAN MIRANDA");
			luan.setEndereco("RUA DOS MACACOS");
			luan.setTelefone("216166666");
			
			TipoImovelDTO tipo = new TipoImovelDTO();
			tipo.setNome("TERRENO");
			
			ImovelDTO imovel = new ImovelDTO();
			imovel.setEstado("UMA CASA COM 4 QUASTROS, 3 SUITE E PUTARIA");
			imovel.setEnderco("RUA DA ROLA GROSSA, Nº 33");
			imovel.setFrente(10.00f);
			imovel.setFundo(10.00f);
			imovel.setAreaQuadrada(imovel.getFrente()*imovel.getFundo());
			imovel.setPreco(25500.32);
			imovel.setTipo(tipo);
			new contrato(luan,imovel);
;
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	}
	    	
