package View;



import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.ClienteControl;
import DTO.ClienteDTO;
import View.iterator.IIteratorCliente;
import View.iterator.IteratorClienteDTO;


public class TableClientes extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	int i;

	public void gerarTabela(JFrame janela, String nome) {
		
		
	    modelo = new DefaultTableModel();
	    modelo.addColumn("Cod");
	    modelo.addColumn("Cliente");
		modelo.addColumn("Endereço");
		modelo.addColumn("CPF/CNPJ");
		modelo.addColumn("Telefone");
		
		tabela = new  JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(47);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(130);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(130);
		
		contener = new JScrollPane(tabela);
		
		/*
		 * Iterator aplicado
		 */
		IIteratorCliente iterator = new IteratorClienteDTO(ClienteControl.listaDeClientesDTO().getArrayDTO());
		
		while (iterator.hasNext()) {
			
			ClienteDTO cliente = (ClienteDTO) iterator.next();
			
			modelo.addRow(new Object[] {cliente.getId(),
					cliente.getNome(),
					cliente.getEndereco(),					                 
					cliente.getCpf(),
					cliente.getTelefone(),
                     });
		}
		
		contener.setBounds(10, 150,720,300);
		janela.add(contener);
		tabela.repaint();

	}
	public int selecionarLinha() {
		return tabela.getSelectedRow();	
	}
	 
	public int seletctID() {
		 try {
			return (int) tabela.getValueAt(tabela.getSelectedRow(),0);
		 }catch (ArrayIndexOutOfBoundsException e) {
			return -1; 
		 }
	}
		   
	public void excluirLinha(int linha) {
		 modelo.removeRow(linha);;	
	}
		   
	public void limparTabela() {
		while(tabela.getModel().getRowCount()>0) {
			modelo.removeRow(0);
		}
	}
	
	public boolean filtroCod(int n) {
		boolean band=false;
		
		IIteratorCliente iterator = new IteratorClienteDTO(ClienteControl.listaDeClientesDTO().getArrayDTO());
		
		while (iterator.hasNext()) {
			
			ClienteDTO cliente = (ClienteDTO) iterator.next();
			
			modelo.addRow(new Object[] {cliente.getId(),
                    cliente.getNome(),
                    cliente.getEndereco(),					                 
                    cliente.getCpf(),
                    cliente.getTelefone(),
                    cliente.getDivida()
                     });
		}
			
		return band;
	}
	public boolean filtroNome(String n) {
		
		boolean band=false;
		
		IIteratorCliente iterator = new IteratorClienteDTO(ClienteControl.listaDeClientesDTO().getArrayDTO());
		
		while (iterator.hasNext()) {
			
			ClienteDTO cliente = (ClienteDTO) iterator.next();
			
			if(cliente.getNome().contains(n)) {
				modelo.addRow(new Object[] {cliente.getId(),
						                    cliente.getNome(),
						                    cliente.getEndereco(),					                 
						                    cliente.getCpf(),
						                    cliente.getTelefone(),
						               
				                             });
	            band = true;
			}
		
		}
		return band;

	}

}