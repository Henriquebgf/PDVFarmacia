package pacote.jdbc;

import javax.swing.JOptionPane;

public class TestaConexao {

	public TestaConexao() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		try {
			new ConnectionFactory().getConnection();
			JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro na conex�o!" + erro);
		}

	}

}
