package senac.farmacia.view;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import senac.farmacia.controller.ClienteController;
import senac.farmacia.model.bo.ClienteBO;
import senac.farmacia.model.dao.ClienteDAO;
import senac.farmacia.model.vo.Cliente;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ViewCadastroCliente extends JInternalFrame {
	/**
	 * 
	 */

	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNascimento;
	private JTextField txCartaoGerado;
	private ClienteDAO clienteDao = null;
	private Cliente cliente;
	private ClienteController clienteControl =null;
	private ClienteBO clienteBo ;
	private JTextField txCartão;
	private JFormattedTextField  txCpfc;
	private JTextField txNomec;
	private JTextField txId;
	private JTextField txPesquisaCliente;
	private JTable clientesTabela;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewCadastroCliente frame = new ViewCadastroCliente();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ViewCadastroCliente() {
		setClosable(true);
		setBounds(100, 100, 1153, 796);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(18, 18, 61, 16);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
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

		txtCPF = new  JFormattedTextField();
		
		
		txtCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					e.consume();
				}
			}
		});
		txtCPF.setBounds(148, 51, 130, 26);
		getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		try {
			((JFormattedTextField) txtCPF).setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
			
			
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
				
				clienteControl.salvarAction();
				
				

			}
		});
		btnSalvar.setBounds(151, 195, 117, 52);
		getContentPane().add(btnSalvar);

		JLabel lblCartoGerado = new JLabel("Cartão Gerado :");
		lblCartoGerado.setBounds(18, 142, 87, 14);
		getContentPane().add(lblCartoGerado);

		txCartaoGerado = new JTextField();
		txCartaoGerado.setEditable(false);
		txCartaoGerado.setBounds(151, 139, 173, 20);
		getContentPane().add(txCartaoGerado);
		txCartaoGerado.setColumns(10);

		JButton btnGerarCartao = new JButton("Gerar Cartão");
		btnGerarCartao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGerarCartao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Random Cartao = new Random();
				int CartaoNovo = Cartao.nextInt();
				if (CartaoNovo < 0) {
					CartaoNovo = CartaoNovo * (-1);
				}
				txCartaoGerado.setText(String.valueOf(CartaoNovo));

			}
			
		});
		btnGerarCartao.setBounds(18, 195, 117, 52);
		getContentPane().add(btnGerarCartao);
		
		
		
		JLabel lblProcurarCliente = new JLabel("Procurar Cliente : ");
		lblProcurarCliente.setBounds(550, 19, 148, 14);
		getContentPane().add(lblProcurarCliente);
		
		txPesquisaCliente = new JTextField();
		txPesquisaCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				clienteControl.PesquisarClientePorNome();
			}
		});
		txPesquisaCliente.setBounds(740, 16, 309, 20);
		getContentPane().add(txPesquisaCliente);
		txPesquisaCliente.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(605, 96, 475, 250);
		getContentPane().add(scrollPane);
		
		clientesTabela = new JTable();
		clientesTabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				clienteControl.preencherCliente();
			}
		});
		clientesTabela.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IdCliente", "Nome", "CPf", "Data"
			}
		));
		scrollPane.setViewportView(clientesTabela);
		
		clienteControl = new ClienteController(txtNome, txtCPF, txtDataNascimento, txCartaoGerado, clienteDao, cliente,clienteBo,txCartão,txCpfc,txNomec,txId,txPesquisaCliente,clientesTabela);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clienteControl.excluirCliente();
			}
		});
		btnExcluir.setBounds(283, 195, 117, 52);
		getContentPane().add(btnExcluir);

	}
}
