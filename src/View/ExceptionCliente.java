package View;

import javax.swing.JOptionPane;

public class ExceptionCliente  extends Exception{
	public ExceptionCliente() {
	JOptionPane.showMessageDialog(null,"CPF j� cadastrado !","Aviso", JOptionPane.ERROR_MESSAGE);
	}
}
