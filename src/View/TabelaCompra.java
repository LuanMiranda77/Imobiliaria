package View;
/**
 * 
 * @author André
 * 
 */
import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.CompraControl;
import DTO.CompraDTO;
import View.iterator.IIteratorCompra;
import View.iterator.IteratorCompraDTO;

public class TabelaCompra extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;


	public void gerarTabela(JFrame janela) {
		modelo = new DefaultTableModel();
	    tabela = new JTable(modelo);
	    modelo.addColumn("ID");
	    modelo.addColumn("CPF");
	    modelo.addColumn("ID F.");
	    modelo.addColumn("ID I.");
	    modelo.addColumn("PAGAMENTO");
	    modelo.addColumn("DATA");
	    modelo.addColumn("PREÇO");	    
	    modelo.addColumn("TIPO");
	    
		contener = new JScrollPane(tabela);
		contener.setBounds(600, 20,652,350);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		try {
			
			IIteratorCompra iterator = new IteratorCompraDTO(CompraControl.listaCompraDTO().getArrayDTO());
			
			while(iterator.hasNext()) {
				
				CompraDTO compra = (CompraDTO) iterator.next();
				
				if(compra != null) {
					Object[] linha = {compra.getId(),compra.getCliente().getCpf(), compra.getIdFuncionario().getId(), compra.getIdImovel().getId(), compra.getPagamento(), compra.getDataCompra(),
						compra.getIdImovel().getPreco(), compra.getIdImovel().getTipo().getNome()}; 
						
					modelo.addRow(linha);
				}
				
			}
	
		}catch(NullPointerException e) {}
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(80);		
		tabela.getColumnModel().getColumn(7).setPreferredWidth(150);
		
		janela.add(contener);
		tabela.repaint();
	}
	
	public int selecionarLinha() {     
	   return tabela.getSelectedRow();	
	}
	public void limparTabela(int linha) {
		modelo.removeRow(linha);
	}
	public int retornarId(int linha) {
		return (int) tabela.getValueAt(linha, 0);
	}
}
