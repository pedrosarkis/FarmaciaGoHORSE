package senac.farmacia.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import senac.farmacia.database.Dao;
import senac.farmacia.model.vo.Cliente;
import senac.farmacia2.BaseDAO;

public class ClienteDAO extends Dao implements BaseDAO<Cliente> {

	public boolean inserir(Cliente cliente) {
		PreparedStatement stmt;
		
		
	
		try {
			stmt = conexao.prepareStatement("Insert into Cliente (nome,cpf,ncartao,dtnascimento) values (?,?,?,?)");
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setInt(3, cliente.getCartao()); 
			stmt.setDate(4, new java.sql.Date(cliente.getDtnascimento().getTime()));
			
			int retorno = stmt.executeUpdate();
			return (retorno > 0);
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	
	public boolean alterar(Cliente cliente) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Update Cliente set nome = ? , cpf = ?");
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;

		}

	}

	
	public boolean excluir(Cliente cliente) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Delete From Cliente where id = ?");
			stmt.setInt(1, cliente.getIdCliente());
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	
	public List<Cliente> listarTodos() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Select idCliente,nome,cpf,dtnascimento from Cliente");
			ResultSet res = stmt.executeQuery();
			List<Cliente> list = new ArrayList<>();
			while (res.next()) {
				Cliente c = new Cliente();
				c.setIdCliente(res.getInt("idCliente"));
				c.setNome(res.getString("nome"));
				c.setCpf(res.getString("cpf"));
				c.setDtnascimento(res.getTime("dtnascimento"));
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;

		}

	}

	public boolean existeCliente(String cpf) {
		PreparedStatement stmt; 
		boolean existe = false;
		try {
			stmt = conexao.prepareStatement("Select count(cpf) from Cliente where cpf = ?" );
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
	
	public boolean existeCartao (int cartao) {
		PreparedStatement stmt ;
		boolean existe = false;
		try {
			stmt = conexao.prepareStatement("Select count(ncartao) from cliente where ncartao = ?");
			stmt.setInt(1, cartao);
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				int quantidade = res.getInt(1);
				existe = quantidade > 0;
			}
			return existe;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false; 
		}
	}
	
	public Cliente listarPorCartao(int cartao) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Select idCliente,nome,cpf,dtnascimento,ncartao from Cliente where ncartao= ?");
			stmt.setInt(1, cartao);
			ResultSet res = stmt.executeQuery();
			Cliente c = new Cliente();
			while (res.next()) {
				
				c.setIdCliente(res.getInt("idCliente"));
				c.setNome(res.getString("nome"));
				c.setCpf(res.getString("cpf"));
				c.setDtnascimento(res.getDate("dtnascimento"));
				c.setCartao(res.getInt("ncartao"));
				
			}
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
			return null;

		}

	}
	
	public Cliente listarPorCPF(String cpf) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("Select idCliente,nome,cpf,dtnascimento,ncartao from Cliente where cpf= ?");
			stmt.setString(1, cpf);
			ResultSet res = stmt.executeQuery();
			Cliente c = new Cliente();
			while (res.next()) {
				
				c.setIdCliente(res.getInt("idCliente"));
				c.setNome(res.getString("nome"));
				c.setCpf(res.getString("cpf"));
				c.setDtnascimento(res.getDate("dtnascimento"));
				c.setCartao(res.getInt("ncartao"));
				
			}
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
			return null;

		}

	}
}
