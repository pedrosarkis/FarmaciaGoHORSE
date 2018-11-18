package senac.farmacia.model.bo;

import senac.farmacia.model.dao.EstoqueDAO;
import senac.farmacia.model.dao.VendaDAO;
import senac.farmacia.model.vo.Venda;

public class VendaBO {
	VendaDAO  vendadao = new VendaDAO();
	EstoqueDAO estoquedao = new EstoqueDAO();
	
	
	
	
	public String inserir (Venda t){
		String mensagemRet = ""; 
		if(vendadao.inserir(t)) {
			
			mensagemRet = "Vendido com Sucesso";
			if(!estoquedao.subtrairEstoque(t.getRemedio().getIdRemedio(), t.getQuantidade())) {
				mensagemRet = "Erro ao dar update no estoque";
			} else { 
				mensagemRet= "Update Feito";
			}
			
			
			
		} else {
			mensagemRet = "BO - Erro ao realizar Venda";
		}
		
		
		return mensagemRet;
	}
	
	

}
