package senac.farmacia.controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import senac.farmacia.model.bo.RemedioBO;
import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.vo.Remedio;

public class RemedioController {

	private JTextField txtMarca;
	private JTextField txtNomeComercial;
	private JTextField txtComposicao;
	private JTextField txtMiligrama;
	private JTextField txtQuantidadeComprimido;
	private JTextField txtPreco;
	private RemedioDAO remediodao;
	private Remedio remedio;
	private RemedioBO remediobo;

	public RemedioController(JTextField txtMarca, JTextField txtNomeComercial, JTextField txtComposicao,
			JTextField txtMiligrama, JTextField txtQuantidadeComprimido, JTextField txtPreco, RemedioDAO remediodao,
			Remedio remedio,RemedioBO remediobo) {
		super();
		this.txtMarca = txtMarca;
		this.txtNomeComercial = txtNomeComercial;
		this.txtComposicao = txtComposicao;
		this.txtMiligrama = txtMiligrama;
		this.txtQuantidadeComprimido = txtQuantidadeComprimido;
		this.txtPreco = txtPreco;
		this.remediodao = new RemedioDAO();
		this.remedio = new Remedio();
		this.remediobo= new RemedioBO();
	}

	public void SalvarAction() {

		if (txtComposicao.getText().trim().isEmpty() || txtMarca.getText().trim().isEmpty()
				|| txtMiligrama.getText().trim().isEmpty() || txtNomeComercial.getText().trim().isEmpty()
				|| txtPreco.getText().trim().isEmpty() || txtQuantidadeComprimido.getText().trim().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Para cadastrar Remédio é necessário preencher todos os campos");
		} else {

		remedio.setComposiçao(txtComposicao.getText());
		remedio.setLaboratorio(txtMarca.getText());
		remedio.setConcentraçao(txtMiligrama.getText());
		remedio.setNomecomercial(txtNomeComercial.getText());
		remedio.setPrecounitario(Double.parseDouble(txtPreco.getText()));
		remedio.setQdtecomprimidos(Integer.parseInt(txtQuantidadeComprimido.getText()));
		
		
		String resultado = remediobo.inserir(remedio);
		JOptionPane.showMessageDialog(null, resultado);

	}

	}
}
