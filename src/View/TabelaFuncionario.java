package View;

/**
 * 
 * @author André
 * 
 */

import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Control.FuncionarioControl;
import DTO.FuncionarioDTO;
import View.iterator.IIteratorFuncionario;
import View.iterator.IteratorFuncionarioDTO;

public class TabelaFuncionario extends JTable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tabela = new JTable(modelo);
	
	public void gerarTabela(JFrame janela) {
		
		modelo.addColumn("ID");
		modelo.addColumn("NOME");
		modelo.addColumn("CARGO");
		modelo.addColumn("SALÁRIO");
		
		JScrollPane contener = new JScrollPane(tabela);
		contener.setBounds(10, 150, 591,300);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		try {
			
			IIteratorFuncionario iterator = new IteratorFuncionarioDTO(FuncionarioControl.listaDeFuncionarioDTO().getLista());
			
			while(iterator.hasNext()) {
				
				FuncionarioDTO funcionario = (FuncionarioDTO) iterator.next();
				
				modelo.addRow(new Object[] {funcionario.getId(), funcionario.getNome(), funcionario.getCargo(), funcionario.getSalario()});
				
			}

		}catch(NullPointerException e) {}
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(0).setResizable(false);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(250);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
		janela.add(contener);
	}
	
	public int selectLinha() {
		return tabela.getSelectedRow();	
	}
	 
	public void excluirLinha(int linha) {
		 modelo.removeRow(linha);;	
	}
		   
	public void limparTabela() {
		 while(tabela.getModel().getRowCount()>0) {
			 modelo.removeRow(0);
		 }
	}
	
	public int getId(int linha) {
		return (int) tabela.getValueAt(linha, 0);
	}
	
	public int seletctID() {		 
		 try {
			return (int) tabela.getValueAt(tabela.getSelectedRow(),0);
		 }catch (ArrayIndexOutOfBoundsException e) {
			return -1; 
		 }
	}
	 
	public boolean filtroNome(String n) {
		boolean band=false;
		String p = n.toUpperCase();
		
		IIteratorFuncionario iterator = new IteratorFuncionarioDTO(FuncionarioControl.listaDeFuncionarioDTO().getLista());
		
		while(iterator.hasNext()) {
			
			FuncionarioDTO funcionario = (FuncionarioDTO) iterator.next();
			
			if(funcionario.getNome().contains(p)) {
				modelo.addRow(new Object[] {funcionario.getId(), funcionario.getNome(), funcionario.getCargo(), funcionario.getSalario()
				});
				band = true;
			}
			
		}
		return band;
	}
}
