package pacote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import pacote.jdbc.ConnectionFactory;
import pacote.model.Funcionarios;
import pacote.view.Frame_Login;
import pacote.view.Frame_Menu_Principal;

public class FuncionariosDAO {

	private Connection con;

	public FuncionariosDAO() {
		this.con = new ConnectionFactory().getConnection();
	}

	// Método Cadastrar Funcionarios
	public void cadastrarFuncionario(Funcionarios obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "insert into tb_funcionarios(nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// 2 passo- conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getSenha());
			stmt.setString(6, obj.getCargo());
			stmt.setString(7, obj.getNivel_acesso());
			stmt.setString(8, obj.getTelefone());
			stmt.setString(9, obj.getCelular());
			stmt.setString(10, obj.getCep());
			stmt.setString(11, obj.getEndereco());
			stmt.setInt(12, obj.getNumero());
			stmt.setString(13, obj.getComplemento());
			stmt.setString(14, obj.getBairro());
			stmt.setString(15, obj.getCidade());
			stmt.setString(16, obj.getUf());

			// 3 passo - executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}
	}

	public void alterarFuncionario(Funcionarios obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "update tb_funcionarios set nome=?,rg=?,cpf=?,email=?,senha=?,cargo=?,nivel_acesso=?,telefone=?,celular=?,cep=?,"
					+ "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";

			// 2 passo- conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getSenha());
			stmt.setString(6, obj.getCargo());
			stmt.setString(7, obj.getNivel_acesso());
			stmt.setString(8, obj.getTelefone());
			stmt.setString(9, obj.getCelular());
			stmt.setString(10, obj.getCep());
			stmt.setString(11, obj.getEndereco());
			stmt.setInt(12, obj.getNumero());
			stmt.setString(13, obj.getComplemento());
			stmt.setString(14, obj.getBairro());
			stmt.setString(15, obj.getCidade());
			stmt.setString(16, obj.getUf());

			stmt.setInt(17, obj.getId());

			// 3 passo - executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}

	}

	public void excluirFuncionario(Funcionarios obj) {
		try {
			// 1 passo - criar o comando SQL
			String sql = "delete from tb_funcionarios where id = ?";

			// 2 passo- conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, obj.getId());

			// 3 passo - executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}

	}

	/************* Metodo Listar Clientes **************/

	public List<Funcionarios> listarFuncionarios() {
		try {
			// 1 passo criar a lista
			List<Funcionarios> lista = new ArrayList<>();

			// 2 passo criar o comando SQLe executar
			String sql = "select*from tb_funcionarios";
			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql

			while (rs.next()) {
				Funcionarios obj = new Funcionarios();// cria um novo objeto

				obj.setId(rs.getInt("id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setSenha(rs.getString("senha"));
				obj.setCargo(rs.getString("cargo"));
				obj.setNivel_acesso(rs.getString("nivel_acesso"));
				obj.setTelefone(rs.getString("telefone"));
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setUf(rs.getString("estado"));

				lista.add(obj); // adiciona o objeto a lista
			}

			return lista;// retorna a lista com os objetos do banco de dados

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}
	}

	/************* Metodo buscar Funcionarios **************/

	public List<Funcionarios> buscarFuncionarioPorNome(String nome) {
		try {
			// 1 passo criar a lista
			List<Funcionarios> lista = new ArrayList<>();

			// 2 passo criar o comando SQLe executar
			String sql = "select*from tb_funcionarios where nome like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql

			while (rs.next()) {
				Funcionarios obj = new Funcionarios();// cria um novo objeto

				obj.setId(rs.getInt("id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setSenha(rs.getString("senha"));
				obj.setCargo(rs.getString("cargo"));
				obj.setNivel_acesso(rs.getString("nivel_acesso"));
				obj.setTelefone(rs.getString("telefone"));
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setUf(rs.getString("estado"));

				lista.add(obj); // adiciona o objeto a lista
			}

			return lista;// retorna a lista com os objetos do banco de dados

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
			return null;
		}

	}

	// Metodo efetuaLogin
	public void efetuaLogin(String email, String senha) {
		try {
			// 1 passo -SQL
			String sql = "select*from tb_funcionarios where email =? and senha = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, senha);

			ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql

			if (rs.next()) {
				// Usuario logou

				// Caso seja Gerente
				if (rs.getString("nivel_acesso").equals("Administrador")){

					JOptionPane.showMessageDialog(null, "Seja bem vindo ao sistema");
					Frame_Menu_Principal frame = new Frame_Menu_Principal();
					frame.usuariologado = rs.getString("nome");
					frame.setVisible(true);
				}

				// Caso seja usuario limitado

				else if (rs.getString("nivel_acesso").equals("Usuário")){

					JOptionPane.showMessageDialog(null, "Seja bem vindo ao sistema");
					Frame_Menu_Principal frame = new Frame_Menu_Principal();
					frame.usuariologado = rs.getString("nome");

					// Desabilitar os menus
					frame.menu_item_posicaoDia_1.setEnabled(false);
					frame.menu_item_historicoVendas.setVisible(false);
					
					frame.setVisible(true);

				}

			} else {
				// Dados incorretos
				JOptionPane.showMessageDialog(null, "Dados incorretos!");
				new Frame_Login().setVisible(true);
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro:" + erro );
		}
	}
}
