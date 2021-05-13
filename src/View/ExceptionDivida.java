package View;

import javax.swing.JOptionPane;

public class ExceptionDivida  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionDivida() {
	JOptionPane.showMessageDialog(null,"Cliente Com Divida não pode ser excluido!","Erro", JOptionPane.ERROR_MESSAGE);
	}
}
