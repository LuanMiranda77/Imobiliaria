package View;

import javax.swing.JOptionPane;

public class ExceptionFuncionario extends Exception {
	
	public ExceptionFuncionario() {
		JOptionPane.showMessageDialog(null, "ID já cadastrado", "Atenção", 2);
	}

}
