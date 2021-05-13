package View;

import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.CompraControl;
import DTO.CompraDTO;
import View.iterator.IIteratorCompra;
import View.iterator.IteratorCompraDTO;

public class TableCompra extends JTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	private NumberFormat moeda = NumberFormat.getCurrencyInstance();

	public void adicionarTabela(JFrame janela, String cargo) {

		if(cargo.equals("Atendente")||cargo.equals("Gerente")){
			modelo = new DefaultTableModel();
		    modelo.addColumn("Cod");
		    modelo.addColumn("Cliente");
			modelo.addColumn("Imovel");
			modelo.addColumn("Descricao");
			modelo.addColumn("Data Compra");
			modelo.addColumn("Pagamento");
			modelo.addColumn("Valor");
			modelo.addColumn("Funcioanario");

			tabela = new  JTable(modelo);
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
		
			
			contener = new JScrollPane(tabela);
			
			IIteratorCompra iterator = new IteratorCompraDTO(CompraControl.listaCompraDTO().getArrayDTO());
			
			while(iterator.hasNext()) {
				
				CompraDTO compra = (CompraDTO) iterator.next();
				
				modelo.addRow(new Object[] {compra.getId(),
							                    compra.getCliente().getNome(),
							                    compra.getIdImovel().getTipo().getNome(),
							                    compra.getIdImovel().getEstado(),
							                    compra.getDataCompra(),
							                    compra.getPagamento(),
							                    moeda.format(compra.getIdImovel().getPreco()),
							                    compra.getIdFuncionario().getNome(),
					                             });

			}
			
			contener.setBounds(10, 150,660,300);
			janela.add(contener);
			tabela.repaint();
		}
	 }
	 public int selectLinha() {
			return tabela.getSelectedRow();	
		   }
		   
		   public void excluirLinha(int linha) {
			    modelo.removeRow(linha);;	
			   }
		public void adicionarLinha(CompraDTO e) {
			modelo.addRow(new Object[] {e.getId(),
					                    e.getCliente().getNome(),
					                    e.getIdImovel().getTipo().getNome(),
					                    e.getIdImovel().getEstado(),
					                    e.getDataCompra(),
					                    e.getPagamento(),
					                    moeda.format(e.getIdImovel().getPreco()),
					                    e.getIdFuncionario().getNome(),
					                     });
			
		}
		public int seletctID() {
		
		return (int) tabela.getValueAt(selectLinha(),0);
			
	 }
		
		
	
		public void limparTabela() {
		while(tabela.getModel().getRowCount()>0) {
			modelo.removeRow(0);
		}
		

}
		public boolean filtroNome(String n) {
			   boolean band=false;
			   
			   IIteratorCompra iterator = new IteratorCompraDTO(CompraControl.listaCompraDTO().getArrayDTO());
				
			   while(iterator.hasNext()) {
					
					CompraDTO compra = (CompraDTO) iterator.next();
					
					if(compra.getCliente().getNome().contains(n)) {
						band=true;
										modelo.addRow(new Object[] {compra.getId(),
												                    compra.getCliente().getNome(),
												                    compra.getIdImovel().getTipo().getNome(),
												                    compra.getIdImovel().getEstado(),
												                    compra.getDataCompra(),
												                    compra.getPagamento(),
												                    moeda.format(compra.getIdImovel().getPreco()),
												                    compra.getIdFuncionario().getNome(),
													                 });
					}
					
			   }
			   return band;
			}
}	
