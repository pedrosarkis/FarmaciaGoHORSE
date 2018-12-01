package senac.farmacia.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

import senac.farmacia.database.Dao;
import senac.farmacia.model.vo.Estoque;
import senac.farmacia.model.vo.Remedio;
import senac.farmacia2.BaseDAO;

public class RemedioDAO extends Dao implements BaseDAO<Remedio> {
	private Estoque estoque;

	public boolean inserir(Remedio remedio) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"Insert Into Remedio (laboratorio,nomeComercial,composicao,concentracao,quantidadeComprimidos,precoUnitario) values (?,?,?,?,?,?)");

			stmt.setString(1, remedio.getLaboratorio());
			stmt.setString(2, remedio.getNomecomercial());
			stmt.setString(3, remedio.getComposiçao());
			stmt.setString(4, remedio.getConcentraçao());
			stmt.setInt(5, remedio.getQdtecomprimidos());
			stmt.setDouble(6, remedio.getPrecounitario());
			stmt.executeUpdate();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean alterar(Remedio remedio) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"Update Remedio set laboratorio =? , nomeComercial = ? , composicao = ? , concentracao = ? , quantidadeComprimidos = ? ,precoUnitario = ? where idRemedio =?");
			stmt.setString(1, remedio.getLaboratorio());
			stmt.setString(2, remedio.getNomecomercial());
			stmt.setString(3, remedio.getComposiçao());
			stmt.setString(4, remedio.getConcentraçao());
			stmt.setInt(5, remedio.getQdtecomprimidos());
			stmt.setDouble(6, remedio.getPrecounitario());
			stmt.setInt(7, remedio.getIdRemedio());
			
			int retorno = stmt.executeUpdate();
			
			
			return (retorno > 0);
			
			

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Deu erro no update papai");
			return false;
		}

	}

	public boolean excluir(Remedio remedio) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("Delete from Remedio where idRemedio =?");
			stmt.setInt(1, remedio.getIdRemedio());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public List<Remedio> listarTodos() {

		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"Select idRemedio,laboratorio,nomeComercial,composicao,concentracao,quantidadeComprimidos,precoUnitario from Remedio");
			ResultSet res = stmt.executeQuery();
			List<Remedio> list = new ArrayList<>();
			while (res.next()) {
				Remedio r = new Remedio();
				r.setIdRemedio(res.getInt("idRemedio"));
				r.setLaboratorio(res.getString("laboratorio"));
				r.setNomecomercial(res.getString("nomeComercial"));
				r.setComposiçao(res.getString("composicao"));
				r.setQdtecomprimidos(res.getInt("quantidadeComprimidos"));
				r.setPrecounitario(res.getDouble("precoUnitario"));
				r.setConcentraçao(res.getString("concentracao"));
				list.add(r);

			}
			return list;

		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Remedio> listarRemedioPorQuantidade(int maiorque,int menorque) {

		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"Select idRemedio,laboratorio,nomeComercial,composicao,concentracao,quantidadeComprimidos,precoUnitario from Remedio where quantidadeComprimidos > ? and quantidadeComprimidos < ?");
			stmt.setInt(1, maiorque);
			stmt.setInt(2, menorque);
			ResultSet res = stmt.executeQuery();
			List<Remedio> list = new ArrayList<>();
			while (res.next()) {
				Remedio r = new Remedio();
				r.setIdRemedio(res.getInt("idRemedio"));
				r.setLaboratorio(res.getString("laboratorio"));
				r.setNomecomercial(res.getString("nomeComercial"));
				r.setComposiçao(res.getString("composicao"));
				r.setQdtecomprimidos(res.getInt("quantidadeComprimidos"));
				r.setPrecounitario(res.getDouble("precoUnitario"));
				r.setConcentraçao(res.getString("concentracao"));
				list.add(r);

			}
			return list;

		} catch (Exception e) {
			return null;
		}
	}


	public List<Remedio> PesquisarRemedioPorNome(String nome) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"Select idRemedio,composicao,nomeComercial,quantidadeComprimidos,laboratorio,precoUnitario,concentracao from Remedio where nomecomercial like ?");
			stmt.setString(1, nome + '%');
			ResultSet res = stmt.executeQuery();
			List<Remedio> list = new ArrayList<>();
			while (res.next()) {
				Remedio r = new Remedio();

				r.setIdRemedio(res.getInt("idRemedio"));
				r.setComposiçao(res.getString("composicao"));
				r.setNomecomercial(res.getString("nomeComercial"));
				r.setQdtecomprimidos(res.getInt("quantidadeComprimidos"));
				r.setLaboratorio(res.getString("laboratorio"));
				r.setPrecounitario(res.getDouble("precoUnitario"));
				r.setConcentraçao(res.getString("concentracao"));

				list.add(r);
			}
			return list;

		} catch (Exception e) {
			return null;
		}
	}

	public boolean verificarRemedio(String nome, String marca, int quantidade, String concentracao) {
		PreparedStatement stmt;
		boolean existe = false;
		try {
			stmt = conexao.prepareStatement(
					"Select count(idRemedio) from Remedio where nomeComercial =? and laboratorio = ? and quantidadeComprimidos = ? and concentracao = ? ");
			stmt.setString(1, nome);
			stmt.setString(2, marca);
			stmt.setInt(3, quantidade);
			stmt.setString(4, concentracao);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				int qtde = res.getInt(1);
				existe = qtde > 0;

			}

			return existe;

		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Erro no retorno public Remedio obterPorId (int idRemedio) { try {
	 * PreparedStatement stmt; stmt =
	 * conexao.prepareStatement("Select * from Remedio where idRemedio = ?");
	 * stmt.setInt(1, idRemedio); ResultSet res = stmt.executeQuery();
	 * 
	 * while ( res.next()) { Remedio remedio = new Remedio();
	 * remedio.setComposiçao(res.getString("composicao"));
	 * remedio.setConcentraçao(res.getDouble("concentracao"));
	 * remedio.setIdRemedio(res.getInt("idRemedio"));
	 * remedio.setLaboratorio(res.getString("laboratorio"));
	 * remedio.setPrecounitario(res.getDouble("precoUnitario"));
	 * remedio.setQdtecomprimidos(res.getInt("quantidadeComprimidos"));
	 * 
	 * }
	 * 
	 * return remedio;
	 * 
	 * 
	 * } catch (Exception e) { return null; }
	 * 
	 * }
	 */

}
