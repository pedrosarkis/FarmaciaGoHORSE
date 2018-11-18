package senac.farmacia.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import senac.farmacia.controller.FuncionarioController;
import senac.farmacia.model.bo.FuncionarioBO;
import senac.farmacia.model.dao.FuncionarioDAO;
import senac.farmacia.model.vo.Funcionario;

public class ViewCadastroFuncionario extends JInternalFrame {
	/**
	 * 
	 */

	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNascimento;
	private JTextField txtDtAdmissao;
	private FuncionarioController funcionariocontrol;
	private Funcionario funcionario = null;
	private FuncionarioDAO funcionariodao ;
	private FuncionarioBO funcionariobo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ViewCadastroFuncionario() {
		setClosable(true);
		setBounds(100, 100, 450, 300);
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

		txtCPF = new JTextField();
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
				funcionariocontrol.salvarAction();

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
		
		funcionariocontrol = new FuncionarioController(txtNome, txtCPF, txtDataNascimento, txtDtAdmissao, funcionario, funcionariodao, funcionariobo);

	}
}
