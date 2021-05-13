package View;

import javax.swing.JOptionPane;

public class ExceptionClienteN  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3260949218677429391L;

	public ExceptionClienteN() {
	JOptionPane.showMessageDialog(null,"Cliente Não Encontrado!","Aviso", JOptionPane.ERROR_MESSAGE);
	}
}
