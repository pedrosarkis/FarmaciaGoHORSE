package senac.farmacia.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import senac.farmacia.database.Dao;
import senac.farmacia.model.vo.Funcionario;
import senac.farmacia2.BaseDAO;

public class FuncionarioDAO extends Dao implements BaseDAO<Funcionario> {

	
	public boolean inserir(Funcionario funcionario) {
		PreparedStatement stmt;
		
		
		
		try {
			stmt = conexao.prepareStatement("Insert into Funcionario (nome,cpf,dtnascimento,dtAdmissao) values (?,?,?,?)");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf()); 
			stmt.setDate(3, new java.sql.Date(funcionario.getDtNascimento().getTime()));
			stmt.setDate(4, new java.sql.Date(funcionario.getDtAdmissao().getTime()));
			
			int retorno = stmt.executeUpdate();
			return (retorno > 0);
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	
	public boolean alterar(Funcionario funcionario) {
			try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Update Funcionario set nome = ? , cpf = ?");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	
	public boolean excluir(Funcionario funcionario) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Delete From Funcionario where id = ?");
			stmt.setInt(1, funcionario.getIdFuncionario());
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	public List<Funcionario> listarTodos() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Select idFuncionario,nome,cpf from Funcionario");
			ResultSet res = stmt.executeQuery();
			List<Funcionario> list = new ArrayList<>();
			while (res.next()) {
				Funcionario f = new Funcionario();
				f.setIdFuncionario(res.getInt("idFuncionario"));
				f.setNome(res.getString("nome"));
				f.setCpf(res.getString("cpf"));
				//f.setDtNascimento(res.getTime("dtnascimento"));
				
				list.add(f);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;

		}
	}
	
	public List<Funcionario> popularComboNomeFuncionario() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Select idFuncionario,nome from Funcionario");
			ResultSet res = stmt.executeQuery();
			List<Funcionario> list = new ArrayList<>();
			while (res.next()) {
				Funcionario f = new Funcionario();
				
				f.setNome(res.getString("nome"));
				f.setIdFuncionario(res.getInt("idFuncionario"));
				
				
				
				list.add(f);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;

		}
	}
	
	
	public boolean existeFuncionario(String cpf) {
		PreparedStatement stmt; 
		boolean existe = false;
		try {
			stmt = conexao.prepareStatement("Select count(cpf) from Funcionario where cpf = ?" );
			stmt.setString(1, cpf);
			
			ResultSet res =  stmt.executeQuery();   
			while(res.next()) {
				int quantidade = res.getInt(1);
				existe = quantidade > 0;
			}
			
			return existe;
		} catch (SQLException e) {
			if(e instanceof SQLIntegrityConstraintViolationException) //Verifica violação da constraint UNIQUE da coluna em questão
			e.printStackTrace();
			return false;
		}
	}
}
