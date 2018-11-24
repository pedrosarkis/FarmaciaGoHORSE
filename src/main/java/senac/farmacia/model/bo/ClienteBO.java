package senac.farmacia.model.bo;

import java.util.List;

import senac.farmacia.model.dao.ClienteDAO;
import senac.farmacia.model.vo.Cliente;

public class ClienteBO {

	private ClienteDAO clientedao = new ClienteDAO();

	/**
	 * Salva um novo cliente
	 * 
	 * @param Cliente c um cliente
	 * @return String mensagem informando o que ocorreu, validando o CPF no banco e
	 *         também se foi ou nâo salvo
	 */
	public String inserir(Cliente t) {
		String mensagemRetorno = "";

		if (clientedao.existeCartao(t.getCartao())) {
			mensagemRetorno = "Cartão Já Existente, clique em gerar novamente";
		} else {

			if (clientedao.existeCliente(t.getCpf())) {
				mensagemRetorno = "Já existe cliente cadastrado com o CPF: " + t.getCpf();
			} else {
				if (clientedao.inserir(t)) {
					mensagemRetorno = "Cliente salvo";
				} else {
					mensagemRetorno = "Erro ao salvar cliente";
				}
			}

		}
		return mensagemRetorno;
	}

	public boolean alterar(Cliente t) {
		
		return false;
	}

	public boolean excluir(Cliente t) {
		
		return false;
	}

	public String listarTodos() {
		String mensagemRetorno = "";
		
		
		
		return null;
	}

}
