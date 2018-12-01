package senac.farmacia.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.farmacia.model.bo.FuncionarioBO;
import senac.farmacia.model.dao.FuncionarioDAO;
import senac.farmacia.model.vo.Funcionario;

public class FuncionarioController {

	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNascimento;
	private JTextField txtDtAdmissao;
	private Funcionario funcionario = null;
	private FuncionarioDAO funcionariodao;
	private FuncionarioBO funcionariobo;
	private List<Funcionario> listfunc = null;
	private JComboBox<Object> comboBox;
	private JTextField txPesquisaFuncionarioNome;
	private JTable tableFuncionarios;

	public FuncionarioController(JTextField txtNome, JTextField txtCPF, JTextField txtDataNascimento,
			JTextField txtDtAdmissao, Funcionario funcionario, FuncionarioDAO funcionariodao,
			FuncionarioBO funcionariobo, JComboBox<Object> comboBox, JTextField txPesquisaFuncionarioNome,
			JTable tableFuncionarios) {
		super();
		this.txtNome = txtNome;
		this.txtCPF = txtCPF;
		this.txtDataNascimento = txtDataNascimento;
		this.txtDtAdmissao = txtDtAdmissao;
		this.funcionario = new Funcionario();
		this.funcionariodao = new FuncionarioDAO();
		this.funcionariobo = new FuncionarioBO();
		this.comboBox = comboBox;
		this.txPesquisaFuncionarioNome = txPesquisaFuncionarioNome;
		this.tableFuncionarios = tableFuncionarios;
	}

	public void salvarAction() {

		String novocpf = txtCPF.getText().replaceAll("[.-]", "");
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		if (!txtNome.getText().trim().isEmpty()) {
			funcionario.setNome(txtNome.getText());
			if (novocpf.length() == 11) {
				funcionario.setCpf(txtCPF.getText());
				if (!txtDtAdmissao.getText().trim().isEmpty()) {
					try {
						funcionario.setDtAdmissao(formatador.parse(txtDtAdmissao.getText()));

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Data no formato Errado");
					}
					if (!txtDataNascimento.getText().trim().isEmpty()) {
						try {
							funcionario.setDtNascimento(formatador.parse(txtDataNascimento.getText()));
							String resultado = funcionariobo.inserir(funcionario);
							JOptionPane.showMessageDialog(null, resultado);

							DefaultTableModel model = (DefaultTableModel) tableFuncionarios.getModel();
							model.addRow(new Object[] { funcionario.getIdFuncionario(), funcionario.getNome(),
									funcionario.getCpf(), funcionario.getCpf() });

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Data no Formato Errado");

						}

					} else {
						JOptionPane.showMessageDialog(null, "Preencha a Data de Nascimento");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Preencha a Data de Admissão");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Cpf No formato Inválido");
			}

		} else

		{
			JOptionPane.showMessageDialog(null, "O Nome não pode ficar vazio");
		}

	}

	public void popularComboBoxFuncionarioAction() {
		DefaultComboBoxModel<Object> comboModel = (DefaultComboBoxModel<Object>) comboBox.getModel();
		comboBox.removeAllItems();
		listfunc = funcionariodao.popularComboNomeFuncionario();
		for (int i = 0; i < listfunc.size(); i++) {
			listfunc.get(i);
			comboBox.addItem(listfunc.get(i));

		}

	}

	public void ListarFuncionarioPorNome() {
		String nome = txPesquisaFuncionarioNome.getText();
		listfunc = funcionariodao.listarFuncionarioPorNome(nome);
		DefaultTableModel model = (DefaultTableModel) tableFuncionarios.getModel();

		model.setNumRows(0);
		for (Funcionario f : listfunc) {
			model.addRow(new Object[] { f.getIdFuncionario(), f.getNome(), f.getCpf(), f.getDtNascimento() });
		}

	}

}
