package View;

import javax.swing.JOptionPane;

public class ExceptionFuncionario extends Exception {
	
	public ExceptionFuncionario() {
		JOptionPane.showMessageDialog(null, "ID j� cadastrado", "Aten��o", 2);
	}

}
