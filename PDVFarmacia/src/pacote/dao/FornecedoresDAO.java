package pacote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import pacote.jdbc.ConnectionFactory;
import pacote.model.Fornecedores;


	public class FornecedoresDAO {
		private Connection con;

		public FornecedoresDAO() {
			this.con =  new ConnectionFactory().getConnection();
		}

		public void cadastrarFornecedores(Fornecedores obj) {
			try {
				// 1 passo - criar o comando SQL
				String sql = "insert into tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";

				// 2 passo- conectar o banco de dados e organizar o comando sql
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, obj.getNome());
				stmt.setString(2, obj.getCnpj());
				stmt.setString(3, obj.getEmail());
				stmt.setString(4, obj.getTelefone());
				stmt.setString(5, obj.getCelular());
				stmt.setString(6, obj.getCep());
				stmt.setString(7, obj.getEndereco());
				stmt.setInt(8, obj.getNumero());
				stmt.setString(9, obj.getComplemento());
				stmt.setString(10, obj.getBairro());
				stmt.setString(11, obj.getCidade());
				stmt.setString(12, obj.getUf());

				// 3 passo - executar o comando sql
				stmt.execute();
				stmt.close();

				JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, "Erro: " + erro);

			}
		}
		
		public void alterarFornecedores(Fornecedores obj) {
			try {
				// 1 passo - criar o comando SQL
				String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,"
						+ "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";

				// 2 passo- conectar o banco de dados e organizar o comando sql
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, obj.getNome());
				stmt.setString(2, obj.getCnpj());
				stmt.setString(3, obj.getEmail());
				stmt.setString(4, obj.getTelefone());
				stmt.setString(5, obj.getCelular());
				stmt.setString(6, obj.getCep());
				stmt.setString(7, obj.getEndereco());
				stmt.setInt(8, obj.getNumero());
				stmt.setString(9, obj.getComplemento());
				stmt.setString(10, obj.getBairro());
				stmt.setString(11, obj.getCidade());
				stmt.setString(12, obj.getUf());

				stmt.setInt(13, obj.getId());

				// 3 passo - executar o comando sql
				stmt.execute();
				stmt.close();

				JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, "Erro: " + erro);

			}

		}

		public void excluirFornedores(Fornecedores obj) {
			try {
				// 1 passo - criar o comando SQL
				String sql = "delete from tb_fornecedores where id = ?";

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
		
		/************* Metodo Listar Fornecedores **************/

		public List<Fornecedores> listarFornecedores() {
			try {
				// 1 passo criar a lista
				List<Fornecedores> lista = new ArrayList<>();

				// 2 passo criar o comando SQLe executar
				String sql = "select*from tb_fornecedores";
				PreparedStatement stmt = con.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql

				while (rs.next()) {
					Fornecedores obj = new Fornecedores();// cria um novo objeto

					obj.setId(rs.getInt("id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
					obj.setNome(rs.getString("nome"));
					obj.setCnpj(rs.getString("cnpj"));
					obj.setEmail(rs.getString("email"));
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

		/************* Metodo buscar Fornecedores **************/

		public List<Fornecedores> buscarFornecedoresPorNome(String nome) {
			try {
				// 1 passo criar a lista
				List<Fornecedores> lista = new ArrayList<>();

				// 2 passo criar o comando SQLe executar
				String sql = "select*from tb_fornecedores where nome like ?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, nome);

				ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql

				while (rs.next()) {
					Fornecedores obj = new Fornecedores();// cria um novo objeto

					obj.setId(rs.getInt("id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
					obj.setNome(rs.getString("nome"));
					obj.setCnpj(rs.getString("cnpj"));
					obj.setEmail(rs.getString("email"));
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
		
		public Fornecedores consultaPorNome(String nome) {
			try {
				// 1 passo criar o comando SQLe executar
				String sql = "select*from tb_fornecedores where nome=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, nome);

				ResultSet rs = stmt.executeQuery(); // vai receber a execucao do sql
				Fornecedores obj = new Fornecedores();
				
				if(rs.next()) {
					
					obj.setId(rs.getInt("id")); // pega o resultado que vai encontrando no rs e jogando na coluna id
					obj.setNome(rs.getString("nome"));
					obj.setCnpj(rs.getString("cnpj"));
					obj.setEmail(rs.getString("email"));
					obj.setTelefone(rs.getString("telefone"));
					obj.setCelular(rs.getString("celular"));
					obj.setCep(rs.getString("cep"));
					obj.setEndereco(rs.getString("endereco"));
					obj.setNumero(rs.getInt("numero"));
					obj.setComplemento(rs.getString("complemento"));
					obj.setBairro(rs.getString("bairro"));
					obj.setCidade(rs.getString("cidade"));
					obj.setUf(rs.getString("estado"));
				}
				
				return obj;
				
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Fornecedor não Encontrado");
				return null;
			}
		}
		
}
