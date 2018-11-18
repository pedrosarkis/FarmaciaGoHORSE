package senac.farmacia.model.bo;

import senac.farmacia.model.dao.FuncionarioDAO;
import senac.farmacia.model.vo.Funcionario;

public class FuncionarioBO {
	
	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	
	public String inserir(Funcionario t) {
		String mensagemRetorno = "";
		
		
		if (funcionarioDAO.existeFuncionario(t.getCpf())) {
			mensagemRetorno = "Já existe funcionario cadastrado com o CPF: " + t.getCpf();
		} else {
			if (funcionarioDAO.inserir(t)) {
				mensagemRetorno = "Funcionario salvo";
			} else {
				mensagemRetorno = "Erro ao salvar funcionario";
			}
		}

	
	return mensagemRetorno;
		
	}

}
