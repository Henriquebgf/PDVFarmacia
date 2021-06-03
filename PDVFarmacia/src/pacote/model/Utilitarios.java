package pacote.model;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Container;

public class Utilitarios {

	// Metodo Limpar Tela
	public void limparTela(JPanel container) {
		Component components[] = container.getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {
				((JTextField) component).setText(null);
			}
		}

	}

	public static void limparCampos(Container container) {
		Component components[] = container.getComponents();
		for (Component component : components) {
			if (component instanceof JFormattedTextField) {
				JFormattedTextField field = (JFormattedTextField) component;
				field.setValue(null);
			} else if (component instanceof JTextField) {
				JTextField field = (JTextField) component;
				field.setText("");
			} else if (component instanceof JTextArea) {
				JTextArea area = (JTextArea) component;
				area.setText("");
			}

		}
	}
	
	

	
}
