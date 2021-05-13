package View;

/**
 * @author Agemiro
 */

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.TipoControl;
import DTO.TipoImovelDTO;
import View.iterator.IIteratorTipoImovel;
import View.iterator.IteratorTipoImovelDTO;

public class TabelaTipoImoveis extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;

	public void tabelaTipoImoveis(JFrame janela){

		modelo=new DefaultTableModel();
		
	    tabela = new JTable(modelo);
	    
	    modelo.addColumn("ID");
	    modelo.addColumn("Tipo");
	   // modelo.addColumn("% de Lucro");
	    
	    tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
		//tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
	    
		contener = new JScrollPane(tabela);
		contener.setBounds(25, 220, 200, 250);
		
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		
		IIteratorTipoImovel iterator = new IteratorTipoImovelDTO(TipoControl.listaTipoImovelDTO().getLista());
		
		while(iterator.hasNext()) {
			
			TipoImovelDTO tipoImovel = (TipoImovelDTO) iterator.next();
			
			Object[] linha = {tipoImovel.getId(),
			          		  tipoImovel.getNome(),
			         /// tipoImovel.getPocetagem()
			          };
			modelo.addRow(linha);

		}

		janela.add(contener);
		tabela.repaint();
	}
	
	public int selecionarLinha() {     
	   return tabela.getSelectedRow();	
	}

	public int retornarId() {
		try {
			return (int) tabela.getValueAt(tabela.getSelectedRow(),0);
		 }catch (ArrayIndexOutOfBoundsException e) {
			return -1; 
		}
	}
	public void excluirLinha(int linha) {
			modelo.removeRow(linha);
	}

}
