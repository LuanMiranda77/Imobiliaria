package View;
/**
 * 
 * @author luan
 * 
 */
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.ImovelControl;
import Control.TipoControl;
import DTO.ImovelDTO;
import DTO.TipoImovelDTO;
import View.iterator.IIteratorImovel;
import View.iterator.IteratorImovelDTO;

public class TabelaImoveis extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8679019858685912438L;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;

	public void gerarTabela(JFrame janela,String nome) {
		
		modelo = new DefaultTableModel();
	    tabela = new JTable(modelo);
	    
	    modelo.addColumn("ID");
	    modelo.addColumn("Tipo");
	    modelo.addColumn("Descrição");
	    modelo.addColumn("Frente");
	    modelo.addColumn("Fundo");
	    modelo.addColumn("Área²");
	    modelo.addColumn("Endereço");
		modelo.addColumn("Preço");
		modelo.addColumn("Status");
		
		contener = new JScrollPane(tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		
		if (nome.equals("estoque")) {
			
			contener.setBounds(10, 150, 973, 300);
			
			IIteratorImovel iterator = new IteratorImovelDTO(ImovelControl.listaImovelDTO().getArrayDTO());
			
			while(iterator.hasNext()) {
				
				ImovelDTO imovel = (ImovelDTO) iterator.next();
				
				TipoImovelDTO tipo = new TipoImovelDTO();
				
				tipo = TipoControl.pesquisarInt(imovel.getTipo().getId());
				
				if(!imovel.getStatus().equals("Vendido")) {
					Object[] linha = {imovel.getId(), 
									tipo.getNome(),
									imovel.getEstado(),
									imovel.getFrente(),
									imovel.getFundo(),
									imovel.getAreaQuadrada(),
									imovel.getEnderco(),
									imovel.getPreco(), 
									imovel.getStatus()};
					modelo.addRow(linha);
				}
				
			}

		}
		else if(nome.equals("contabilidade")) {
			contener.setBounds(350, 150,935,350);
		}
		
		else {
			contener.setBounds(10, 150, 973, 300);
			
			IIteratorImovel iterator = new IteratorImovelDTO(ImovelControl.listaImovelDTO().getArrayDTO());
			
			while(iterator.hasNext()) {
				
				ImovelDTO imovel = (ImovelDTO) iterator.next();
				
				TipoImovelDTO tipo = new TipoImovelDTO();
				
				tipo = TipoControl.pesquisarInt(imovel.getTipo().getId());
				
				if(imovel.getStatus().equals("Vendido")) {
					Object[] linha = {imovel.getId(), 
									tipo.getNome(),
									imovel.getEstado(),
									imovel.getFrente(),
									imovel.getFundo(),
									imovel.getAreaQuadrada(),
									imovel.getEnderco(),
									imovel.getPreco(), 
									imovel.getStatus()};
					modelo.addRow(linha);
				}
			}
		}
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(232);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(8).setPreferredWidth(80);	
		
		janela.add(contener);
		tabela.repaint();
	}
	
	public int selecionarLinha() {     
	   return tabela.getSelectedRow();	
	}
	
	public void limparTabela(int linha) {
		modelo.removeRow(linha);
	}
	
    public void limparTabela2() {
    	while(tabela.getModel().getRowCount()>0) {
			modelo.removeRow(0);
		}
	}
    
	public int retornarId() {
		 try {
				return (int) tabela.getValueAt(tabela.getSelectedRow(),0);
		 }catch (ArrayIndexOutOfBoundsException e) {
				return -1; 
		 }
	}
	
	public boolean filtroNome(String n) {
		
		    boolean band = false;
		    String d = n.toUpperCase();
		    
		    IIteratorImovel iterator = new IteratorImovelDTO(ImovelControl.listaImovelDTO().getArrayDTO());
			
			while(iterator.hasNext()) {
				
				ImovelDTO imovel = (ImovelDTO) iterator.next();
				
				TipoImovelDTO tipo = new TipoImovelDTO();
				
				tipo = TipoControl.pesquisarInt(imovel.getTipo().getId());
				
				if(tipo.getNome().contains(d)) {
					band=true;
					if(!imovel.getStatus().equals("Vendido")) {
					modelo.addRow(new Object[] {imovel.getId(), 
												tipo.getNome(),
												imovel.getEstado(),
												imovel.getFrente(),
												imovel.getFundo(),
												imovel.getAreaQuadrada(),
												imovel.getEnderco(),
												imovel.getPreco(), 
												imovel.getStatus()
								                 });
				}
				}
				
			}
			return band;
	}
	public boolean filtroNome2(String n) {
		   boolean band=false;
		   String d=n.toUpperCase();
		    
		   IIteratorImovel iterator = new IteratorImovelDTO(ImovelControl.listaImovelDTO().getArrayDTO());
			
			while(iterator.hasNext()) {
				
				ImovelDTO imovel = (ImovelDTO) iterator.next();
				
				TipoImovelDTO tipo = new TipoImovelDTO();
				tipo=TipoControl.pesquisarInt(imovel.getTipo().getId());
				if(tipo.getNome().contains(d)) {
					band=true;
					if(imovel.getStatus().equals("Vendido")) {
					modelo.addRow(new Object[] {imovel.getId(), 
												tipo.getNome(),
												imovel.getEstado(),
												imovel.getFrente(),
												imovel.getFundo(),
												imovel.getAreaQuadrada(),
												imovel.getEnderco(),
												imovel.getPreco(), 
												imovel.getStatus()
								                 });
					}
				}
			}
			return band;
		}
}
