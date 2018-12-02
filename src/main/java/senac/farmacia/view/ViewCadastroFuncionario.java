package senac.farmacia.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import senac.farmacia.controller.FuncionarioController;
import senac.farmacia.model.bo.FuncionarioBO;
import senac.farmacia.model.dao.FuncionarioDAO;
import senac.farmacia.model.vo.Funcionario;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ViewCadastroFuncionario extends JInternalFrame {
	/**
	 * 
	 */

	private JTextField txtNome;
	private JFormattedTextField txtCPF;
	private JTextField txtDataNascimento;
	private JTextField txtDtAdmissao;
	private FuncionarioController funcionarioControl;
	private Funcionario funcionario = null;
	private FuncionarioDAO funcionarioDao;
	private FuncionarioBO funcionarioBo;
	private JComboBox<Object> comboBox;
	private JTextField txPesquisaFuncionarioNome;
	private JTable tableFuncionarios;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ViewCadastroFuncionario() {
		setClosable(true);
		setBounds(100, 100, 1154, 807);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(18, 18, 61, 16);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c) || c == ',') {
					e.consume();
				}

			}
		});
		txtNome.setBounds(148, 13, 272, 26);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(18, 56, 61, 16);
		getContentPane().add(lblCpf);

		txtCPF = new JFormattedTextField();
		txtCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c) || c == ',' || c == '.') {
					e.consume();
				}
			}
		});
		txtCPF.setBounds(148, 51, 130, 26);
		getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		try {
			txtCPF.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));

		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel lblDataNascimento = new JLabel("Data Nascimento:");
		lblDataNascimento.setBounds(18, 96, 122, 16);
		getContentPane().add(lblDataNascimento);

		txtDataNascimento = new JTextField();
		txtDataNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					e.consume();
				}
			}
		});
		txtDataNascimento.setBounds(148, 91, 130, 26);
		getContentPane().add(txtDataNascimento);
		txtDataNascimento.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				funcionarioControl.salvarAction();

			}
		});
		btnSalvar.setBounds(162, 184, 117, 52);
		getContentPane().add(btnSalvar);

		JLabel lblDataAdmisso = new JLabel("Data Admissão : ");
		lblDataAdmisso.setBounds(18, 132, 97, 14);
		getContentPane().add(lblDataAdmisso);

		txtDtAdmissao = new JTextField();
		txtDtAdmissao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c) || c == ',' || c == '.') {
					e.consume();
				}

			}
		});
		txtDtAdmissao.setBounds(148, 128, 130, 20);
		getContentPane().add(txtDtAdmissao);
		txtDtAdmissao.setColumns(10);

		JLabel lblPesquisarFuncionario = new JLabel("Pesquisar Funcionario : ");
		lblPesquisarFuncionario.setBounds(515, 19, 135, 14);
		getContentPane().add(lblPesquisarFuncionario);

		txPesquisaFuncionarioNome = new JTextField();
		txPesquisaFuncionarioNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				funcionarioControl.ListarFuncionarioPorNome();
			}
		});
		txPesquisaFuncionarioNome.setBounds(641, 16, 291, 20);
		getContentPane().add(txPesquisaFuncionarioNome);
		txPesquisaFuncionarioNome.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(453, 96, 586, 313);
		getContentPane().add(scrollPane);

		tableFuncionarios = new JTable();
		tableFuncionarios.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "idFuncionario", "Nome", "CPF", "Data Nascimento" }));
		scrollPane.setViewportView(tableFuncionarios);
		funcionarioControl = new FuncionarioController(txtNome, txtCPF, txtDataNascimento, txtDtAdmissao, funcionario,
				funcionarioDao, funcionarioBo, comboBox, txPesquisaFuncionarioNome, tableFuncionarios);
	}
}
