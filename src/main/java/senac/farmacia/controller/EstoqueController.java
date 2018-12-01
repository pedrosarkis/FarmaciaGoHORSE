package senac.farmacia.controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.farmacia.model.dao.EstoqueDAO;
import senac.farmacia.model.vo.Estoque;

public class EstoqueController {
	
	private JTextField pesquisa;
	private JTable estoque;
	private Estoque estoqueObjeto;
	private EstoqueDAO estoquedao;
	private List<Estoque> remediosEmEstoque;
	
	public EstoqueController(JTextField pesquisa, JTable estoque, Estoque estoqueObjeto, EstoqueDAO estoquedao) {
		super();
		this.pesquisa = pesquisa;
		this.estoque = estoque;
		this.estoqueObjeto = new Estoque();
		this.estoquedao = new EstoqueDAO();
	}
	
	public void listarRemediosEmEstoque() {
		String pesquisaTexto = pesquisa.getText();
		
		remediosEmEstoque = estoquedao.pesquisarPorNomeTESTE(pesquisaTexto);
		DefaultTableModel model = (DefaultTableModel) estoque.getModel();
		model.setNumRows(0);
		
		for(Estoque e : remediosEmEstoque) {
			model.addRow(new Object[] {e.getRemedio().getIdRemedio(), e.getRemedio().getNomecomercial(),e.getQuantidade()});
		}
		
		
	}
	
	
	
	
	
	

}
