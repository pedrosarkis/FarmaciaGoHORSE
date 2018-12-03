package senac.farmacia.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.farmacia.model.bo.RemedioBO;
import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.vo.Estoque;
import senac.farmacia.model.vo.Remedio;

public class RemedioController {

	private JTextField txtMarca;
	private JTextField txtNomeComercial;
	private JTextField txtComposicao;
	private JTextField txtMiligrama;
	private JTextField txtQuantidadeComprimido;
	private JTextField txtPreco;
	private RemedioDAO remedioDao;
	private Remedio remedio;
	private RemedioBO remedioBo;
	private JTable medicamentos;
	private List<Remedio> medicamentosCadastrados;
	private Remedio remedioSelecionado;
	private JTextField txPesquisaRemedio;

	public RemedioController(JTextField txtMarca, JTextField txtNomeComercial, JTextField txtComposicao,
			JTextField txtMiligrama, JTextField txtQuantidadeComprimido, JTextField txtPreco, RemedioDAO remediodao,
			Remedio remedio, RemedioBO remediobo, JTable medicamentos, List<Remedio> medicamentosCadastrados,
			Remedio remedioSelecionado, JTextField txPesquisaRemedio) {
		super();
		this.txtMarca = txtMarca;
		this.txtNomeComercial = txtNomeComercial;
		this.txtComposicao = txtComposicao;
		this.txtMiligrama = txtMiligrama;
		this.txtQuantidadeComprimido = txtQuantidadeComprimido;
		this.txtPreco = txtPreco;
		this.remedioDao = new RemedioDAO();
		this.remedio = new Remedio();
		this.remedioBo = new RemedioBO();
		this.medicamentos = medicamentos;
		this.medicamentosCadastrados = medicamentosCadastrados;
		this.remedioSelecionado = remedioSelecionado;
		this.txPesquisaRemedio = txPesquisaRemedio;
	}

	public void salvarAction() {

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

			String resultado = remedioBo.inserir(remedio);
			JOptionPane.showMessageDialog(null, resultado);
			

			
			DefaultTableModel model = (DefaultTableModel) medicamentos.getModel();
			model.addRow(
					new Object[] { remedio.getNomecomercial(), remedio.getLaboratorio(), remedio.getPrecounitario(),
							remedio.getQdtecomprimidos(), remedio.getComposiçao(), remedio.getConcentraçao() });
			txtComposicao.setText("");
			txtNomeComercial.setText("");
			txtMarca.setText("");
			txtMiligrama.setText("");
			txtPreco.setText("");
			txtQuantidadeComprimido.setText("");
			

		}

	}

	public void pesquisarRemedioPorNome() {
		String nome = txPesquisaRemedio.getText();
		medicamentosCadastrados = remedioDao.PesquisarRemedioPorNome(nome);
		DefaultTableModel model = (DefaultTableModel) medicamentos.getModel();
		model.setNumRows(0);
		for (Remedio r : medicamentosCadastrados) {
			model.addRow(new Object[] { r.getNomecomercial(), r.getLaboratorio(), r.getPrecounitario(),
					r.getQdtecomprimidos(), r.getComposiçao(), r.getConcentraçao() });
		}

	}

	public void preencherRetornoRemedio() {
		remedioSelecionado = pegarItemTableRemedio();

		txtComposicao.setText(remedioSelecionado.getComposiçao());
		txtMarca.setText(remedioSelecionado.getLaboratorio());
		txtMiligrama.setText(remedioSelecionado.getConcentraçao());
		txtNomeComercial.setText(remedioSelecionado.getNomecomercial());
		txtPreco.setText(String.valueOf(remedioSelecionado.getPrecounitario()));
		txtQuantidadeComprimido.setText(String.valueOf(remedioSelecionado.getQdtecomprimidos()));

	}

	public Remedio pegarItemTableRemedio() {
		int linha = medicamentos.getSelectedRow();
		return medicamentosCadastrados.get(linha);

	}

	public void alterarRemedio() {

		Remedio r = new Remedio();
		remedioSelecionado = pegarItemTableRemedio();

		r.setComposiçao(txtComposicao.getText());
		r.setConcentraçao(txtMiligrama.getText());
		r.setLaboratorio(txtMarca.getText());
		r.setNomecomercial(txtNomeComercial.getText());
		r.setIdRemedio(remedioSelecionado.getIdRemedio());
		r.setPrecounitario(Double.parseDouble(txtPreco.getText()));
		r.setQdtecomprimidos(Integer.parseInt(txtQuantidadeComprimido.getText()));

		String resultado = remedioBo.alterar(r);
		JOptionPane.showMessageDialog(null, resultado);

	}

	public void excluirMedicamento() {

		int dialogResult = JOptionPane.showConfirmDialog(null, "Confirmar Exclusão?", "Confirmação de Exclusão",
				JOptionPane.YES_NO_OPTION);

		if (dialogResult == JOptionPane.YES_OPTION) {

			remedioSelecionado = pegarItemTableRemedio();

			String resultado = remedioBo.excluir(remedioSelecionado);
			JOptionPane.showMessageDialog(null, resultado);

		}

	}
}
