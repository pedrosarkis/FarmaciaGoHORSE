package senac.farmacia.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import senac.farmacia.controller.VendaController;
import senac.farmacia.model.bo.ClienteBO;
import senac.farmacia.model.bo.VendaBO;
import senac.farmacia.model.dao.ClienteDAO;
import senac.farmacia.model.dao.EstoqueDAO;
import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.dao.VendaDAO;
import senac.farmacia.model.vo.Cliente;
import senac.farmacia.model.vo.Estoque;
import senac.farmacia.model.vo.Remedio;
import senac.farmacia.model.vo.Venda;

public class ViewVenda extends JInternalFrame {
	private JTextField txHoraTransaction;
	private JTextField txProduto;
	private JTextField txPrecoUnitario;
	private JTextField txQuantidadeDisponivel;
	private JTextField txQuantidade;
	private JTextField txTotal;
	private JTable table;
	private JTextField txPesquisa;
	private JTable carrinho;
	private JTextField txSubtotal;
	private JTextField txDesconto;
	private JTextField txTotalFinal;
	private JTextField txCartão;
	private JTextField txDinheiro;
	private JTextField txTroco;
	private JTextField txNomec;
	private JTextField txIdadec;
	private JTextField txCpfc;
	private VendaController vendacontrol;
	private Venda venda;
	private VendaDAO vendadao;
	private Remedio remedio = null;
	private RemedioDAO remediodao;
	private List<Estoque> listremedioestoque;
	private List<Remedio> listremedio;
	private EstoqueDAO estoquedao;
	private Estoque estoque = null;
	private ViewCadastroCliente v1 = new ViewCadastroCliente();
	private JComboBox comboBox;
	private List<Estoque> carrinhoTable;
	private JTextField txFieldIDProduto;
	private ClienteBO clientebo;
	private ClienteDAO clientedao;
	private Cliente cliente;
	private VendaBO vendabo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewVenda frame = new ViewVenda();
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

	public ViewVenda() {
		setClosable(true);
		getContentPane().setForeground(Color.RED);
		setTitle("Venda");
		setBounds(100, 100, 1182, 548);
		getContentPane().setLayout(null);

		JLabel lblHoraDaTransao = new JLabel("Hora da transação :");
		lblHoraDaTransao.setBounds(34, 30, 126, 14);
		getContentPane().add(lblHoraDaTransao);

		txHoraTransaction = new JTextField();
		txHoraTransaction.setText("28/10/2018 11.57");
		txHoraTransaction.setBounds(156, 27, 139, 20);
		getContentPane().add(txHoraTransaction);
		txHoraTransaction.setColumns(10);

		JLabel lblNewLabel = new JLabel("Produto :");
		lblNewLabel.setBounds(34, 72, 74, 14);
		getContentPane().add(lblNewLabel);

		txProduto = new JTextField();
		txProduto.setEditable(false);
		txProduto.setForeground(Color.BLACK);
		txProduto.setBounds(156, 69, 205, 20);
		getContentPane().add(txProduto);
		txProduto.setColumns(10);

		JButton btnAdicionarAoCarrinho = new JButton("Adicionar ao Carrinho");
		btnAdicionarAoCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vendacontrol.preencheCarrinho();
				vendacontrol.pegaritemCarrinho();

			}
		});
		btnAdicionarAoCarrinho.setBounds(386, 69, 135, 23);
		getContentPane().add(btnAdicionarAoCarrinho);

		JLabel lblPreoUnitrio = new JLabel("Preço unitário :");
		lblPreoUnitrio.setBounds(34, 112, 102, 14);
		getContentPane().add(lblPreoUnitrio);

		txPrecoUnitario = new JTextField();
		txPrecoUnitario.setEditable(false);
		txPrecoUnitario.setBounds(154, 109, 86, 20);
		getContentPane().add(txPrecoUnitario);
		txPrecoUnitario.setColumns(10);

		JLabel lblQuantidadeDisponvel = new JLabel("Quantidade disponível :");
		lblQuantidadeDisponvel.setBounds(251, 112, 152, 14);
		getContentPane().add(lblQuantidadeDisponvel);

		txQuantidadeDisponivel = new JTextField();
		txQuantidadeDisponivel.setEditable(false);
		txQuantidadeDisponivel.setBounds(399, 109, 64, 20);
		getContentPane().add(txQuantidadeDisponivel);
		txQuantidadeDisponivel.setColumns(10);

		JLabel lblQuantidade = new JLabel("Quantidade :");
		lblQuantidade.setBounds(34, 151, 102, 14);
		getContentPane().add(lblQuantidade);

		txQuantidade = new JTextField();
		txQuantidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				vendacontrol.calcularTotal();
			}

		});
		txQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (txProduto.getText().isEmpty()) {
					e.consume();

				} else if (Character.isLetter(c) || txQuantidade.getText().equals(',')) {
					e.consume();
				}

			}
		});
		txQuantidade.setBounds(154, 148, 86, 20);
		getContentPane().add(txQuantidade);
		txQuantidade.setColumns(10);

		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setBounds(249, 151, 46, 14);
		getContentPane().add(lblTotal);

		txTotal = new JTextField();
		txTotal.setEditable(false);
		txTotal.setBounds(291, 148, 86, 20);
		getContentPane().add(txTotal);
		txTotal.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		scrollPane.setBounds(10, 246, 453, 261);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id Produto", "Nome ", "Laboratorio", "Pre\u00E7o", "Quantidade em estoque" }));

		JLabel lblPesquisar = new JLabel("Pesquisar  :");
		lblPesquisar.setBounds(10, 218, 70, 14);
		getContentPane().add(lblPesquisar);

		txPesquisa = new JTextField();
		txPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {

				vendacontrol.pesquisarPornome();
			}
		});
		txPesquisa.setBounds(94, 215, 267, 20);
		getContentPane().add(txPesquisa);
		txPesquisa.setColumns(10);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				txQuantidade.setText("");
				txProduto.setText("");
				txPrecoUnitario.setText("");
				txTotal.setText("");
				txQuantidadeDisponivel.setText("");
				txPesquisa.setText("");
				vendacontrol.pesquisarPornome();

			}
		});
		btnLimpar.setBounds(441, 148, 86, 23);
		getContentPane().add(btnLimpar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(539, 60, 402, 250);
		getContentPane().add(scrollPane_1);

		carrinho = new JTable();
		carrinho.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "idProduto", "Nome Comercial", "Pre\u00E7o", "Quantidade" }));
		carrinho.setForeground(Color.DARK_GRAY);
		scrollPane_1.setViewportView(carrinho);

		final JLabel lbFalta = new JLabel("Em falta");
		lbFalta.setForeground(Color.RED);
		lbFalta.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbFalta.setBackground(Color.RED);
		lbFalta.setBounds(463, 113, 58, 14);
		getContentPane().add(lbFalta);

		JLabel lblCarrinho = new JLabel("Carrinho");
		lblCarrinho.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 28));
		lblCarrinho.setBounds(646, 15, 219, 33);
		getContentPane().add(lblCarrinho);

		JLabel lblSubTotal = new JLabel("Sub Total :");
		lblSubTotal.setBounds(539, 328, 73, 14);
		getContentPane().add(lblSubTotal);

		txSubtotal = new JTextField();
		txSubtotal.setEditable(false);
		txSubtotal.setBounds(624, 325, 86, 20);
		getContentPane().add(txSubtotal);
		txSubtotal.setColumns(10);

		JLabel lblDesconto = new JLabel("Desconto :");
		lblDesconto.setBounds(539, 353, 73, 14);
		getContentPane().add(lblDesconto);

		txDesconto = new JTextField();
		txDesconto.setEditable(false);
		txDesconto.setBounds(624, 350, 86, 20);
		getContentPane().add(txDesconto);
		txDesconto.setColumns(10);

		JLabel lblTotal_1 = new JLabel("Total :");
		lblTotal_1.setBounds(539, 381, 46, 14);
		getContentPane().add(lblTotal_1);

		txTotalFinal = new JTextField();
		txTotalFinal.setEditable(false);
		txTotalFinal.setBounds(624, 378, 86, 20);
		getContentPane().add(txTotalFinal);
		txTotalFinal.setColumns(10);

		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
	
		btnFinalizarCompra.setBounds(552, 471, 144, 23);
		getContentPane().add(btnFinalizarCompra);

		JLabel lblCartoFrmacia = new JLabel("Cartão Fármacia");
		lblCartoFrmacia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCartoFrmacia.setBounds(753, 320, 135, 14);
		getContentPane().add(lblCartoFrmacia);

		txCartão = new JTextField();
		txCartão.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c) | c == ',') {
					e.consume();

				}
			}
		});
		txCartão.setBounds(719, 345, 193, 20);
		getContentPane().add(txCartão);
		txCartão.setColumns(10);

		JLabel lblDinheiro = new JLabel("Dinheiro :");
		lblDinheiro.setBounds(539, 406, 73, 14);
		getContentPane().add(lblDinheiro);

		txDinheiro = new JTextField();
		txDinheiro.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c) || c == ',') {
					e.consume();
				}
			}
		});
		txDinheiro.setBounds(624, 403, 86, 20);
		getContentPane().add(txDinheiro);
		txDinheiro.setColumns(10);

		JLabel lblTroco = new JLabel("Troco : ");
		lblTroco.setBounds(539, 433, 73, 14);
		getContentPane().add(lblTroco);

		txTroco = new JTextField();
		txTroco.setEditable(false);
		txTroco.setBounds(624, 430, 86, 20);
		getContentPane().add(txTroco);
		txTroco.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnBuscar.setBounds(916, 344, 74, 23);
		getContentPane().add(btnBuscar);

		JLabel lblNome = new JLabel("Nome : ");
		lblNome.setBounds(719, 376, 58, 14);
		getContentPane().add(lblNome);

		txNomec = new JTextField();
		txNomec.setEditable(false);
		txNomec.setBounds(777, 373, 159, 20);
		getContentPane().add(txNomec);
		txNomec.setColumns(10);

		JLabel lblIdade = new JLabel("Idade : ");
		lblIdade.setBounds(719, 406, 46, 14);
		getContentPane().add(lblIdade);

		txIdadec = new JTextField();
		txIdadec.setEditable(false);
		txIdadec.setBounds(777, 403, 36, 20);
		getContentPane().add(txIdadec);
		txIdadec.setColumns(10);

		JLabel lblCpf = new JLabel("CPF : ");
		lblCpf.setBounds(719, 433, 46, 14);
		getContentPane().add(lblCpf);

		txCpfc = new JTextField();
		txCpfc.setEditable(false);
		txCpfc.setBounds(884, 430, 46, 20);
		getContentPane().add(txCpfc);
		txCpfc.setBounds(753, 430, 177, 20);
		getContentPane().add(txCpfc);
		txCpfc.setColumns(10);

		JButton btnRealizarCadastro = new JButton("Realizar Cadastro");
		btnRealizarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(v1);
				v1.setVisible(true);

			}
		});
		btnRealizarCadastro.setBounds(719, 471, 135, 23);
		getContentPane().add(btnRealizarCadastro);
		lbFalta.setVisible(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(71);
		table.getColumnModel().getColumn(1).setPreferredWidth(45);
		table.getColumnModel().getColumn(2).setPreferredWidth(66);
		table.getColumnModel().getColumn(3).setPreferredWidth(39);
		table.getColumnModel().getColumn(4).setPreferredWidth(128);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {

				vendacontrol.preencherVenda();
				txQuantidade.setText("");
				if (Integer.parseInt(txQuantidadeDisponivel.getText()) == 0) {
					lbFalta.setVisible(true);

				} else {
					lbFalta.setVisible(false);
				}

			}
		});

		JLabel lblFuncionario = new JLabel("Funcionario :");
		lblFuncionario.setBounds(951, 72, 80, 14);
		getContentPane().add(lblFuncionario);

		comboBox = new JComboBox();
		comboBox.setBounds(1041, 69, 115, 20);
		getContentPane().add(comboBox);

		JLabel lblIdProduto = new JLabel("ID Produto :");
		lblIdProduto.setBounds(34, 176, 64, 14);
		getContentPane().add(lblIdProduto);

		txFieldIDProduto = new JTextField();
		txFieldIDProduto.setEditable(false);
		txFieldIDProduto.setBounds(154, 179, 25, 20);
		getContentPane().add(txFieldIDProduto);
		txFieldIDProduto.setColumns(10);
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendacontrol.salvarAction();
			}
		});

		vendacontrol = new VendaController(txHoraTransaction, txProduto, txPrecoUnitario, txQuantidadeDisponivel,
				txQuantidade, txTotal, table, txPesquisa, carrinho, txSubtotal, txDesconto, txTotalFinal, txCartão,
				txDinheiro, txTroco, txNomec, txIdadec, txCpfc, remedio, remediodao, venda, vendadao,
				listremedioestoque, listremedio, estoquedao, estoque, carrinhoTable, txFieldIDProduto, clientebo,
				clientedao, cliente, vendabo);

	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}
