package senac.farmacia.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.farmacia.controller.EntradaController;
import senac.farmacia.model.bo.EntradaBO;
import senac.farmacia.model.dao.EntradaDAO;
import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.vo.Entrada;
import senac.farmacia.model.vo.Remedio;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewEntrada extends JInternalFrame {
	private JTextField txPesquisa;
	private JTable table;
	private JTextField txNomeComercial;
	private JTextField txComposicao;
	private JTextField txValorComprado;
	private JTextField txQuantidadeComprimidos;
	private JTextField txtInsiraAQuantidade;
	private JTextField txidProduto;
	private EntradaController entradacontrol;
	private RemedioDAO remediodao;
	private Remedio remedio = null;
	private Entrada entrada = null;
	private EntradaDAO entradadao;
	private List<Remedio> remediosemestoque;
	private EntradaBO entradabo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewEntrada frame = new ViewEntrada();
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
	public ViewEntrada() {
		setClosable(true);
		setBounds(100, 100, 744, 603);
		getContentPane().setLayout(null);

		JLabel lblRemdioAInserir = new JLabel("Remédio a inserir :");
		lblRemdioAInserir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRemdioAInserir.setBounds(10, 24, 130, 23);
		getContentPane().add(lblRemdioAInserir);

		txPesquisa = new JTextField();
		txPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				entradacontrol.pesquisarPornome();
				
			}
		});
		txPesquisa.setBounds(133, 27, 348, 20);
		getContentPane().add(txPesquisa);
		txPesquisa.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 612, 275);
		getContentPane().add(scrollPane);

		table = new JTable();
		
		
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "idProduto", "Nome Comercial", "Composi\u00E7\u00E3o", "Qtde.Comprimidos" }));
		scrollPane.setViewportView(table);

		JLabel lblNomeComercial = new JLabel("Nome Comercial :");
		lblNomeComercial.setBounds(10, 398, 130, 14);
		getContentPane().add(lblNomeComercial);

		txNomeComercial = new JTextField();
		txNomeComercial.setEditable(false);
		txNomeComercial.setBounds(151, 395, 141, 20);
		getContentPane().add(txNomeComercial);
		txNomeComercial.setColumns(10);

		JLabel lblComposio = new JLabel("Composição :");
		lblComposio.setBounds(304, 384, 119, 14);
		getContentPane().add(lblComposio);

		txComposicao = new JTextField();
		txComposicao.setEditable(false);
		txComposicao.setBounds(487, 381, 86, 20);
		getContentPane().add(txComposicao);
		txComposicao.setColumns(10);

		JLabel lblValorComprado = new JLabel("Valor Unitário de Compra: ");
		lblValorComprado.setBounds(10, 439, 163, 14);
		getContentPane().add(lblValorComprado);

		txValorComprado = new JTextField();
		txValorComprado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c) || c == ',') {
					e.consume();
				}
			}
		});
		txValorComprado.setBounds(183, 436, 86, 20);
		getContentPane().add(txValorComprado);
		txValorComprado.setColumns(10);

		JLabel lblQuantidade = new JLabel("Quantidade :");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblQuantidade.setBounds(10, 479, 96, 19);
		getContentPane().add(lblQuantidade);

		JLabel lblQuantidadeComprimidos = new JLabel("Quantidade Comprimidos : ");
		lblQuantidadeComprimidos.setBounds(304, 422, 177, 14);
		getContentPane().add(lblQuantidadeComprimidos);

		txQuantidadeComprimidos = new JTextField();
		txQuantidadeComprimidos.setEditable(false);
		txQuantidadeComprimidos.setBounds(497, 419, 86, 20);
		getContentPane().add(txQuantidadeComprimidos);
		txQuantidadeComprimidos.setColumns(10);

		txtInsiraAQuantidade = new JTextField();
		txtInsiraAQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c) || c ==',' || c =='.') {
					e.consume();
				}
			}
		});
		txtInsiraAQuantidade.setBounds(109, 478, 86, 20);
		getContentPane().add(txtInsiraAQuantidade);
		txtInsiraAQuantidade.setColumns(10);

		JButton btnSalvarCompra = new JButton("Salvar Compra");
		
		btnSalvarCompra.setBounds(272, 479, 119, 23);
		getContentPane().add(btnSalvarCompra);

		JLabel lblIdproduto = new JLabel("idProduto : ");
		lblIdproduto.setBounds(10, 373, 90, 14);
		getContentPane().add(lblIdproduto);

		txidProduto = new JTextField();
		txidProduto.setEditable(false);
		txidProduto.setBounds(151, 369, 44, 20);
		getContentPane().add(txidProduto);
		txidProduto.setColumns(10);
		btnSalvarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entradacontrol.SalvarAction();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				entradacontrol.preencherEntrada();
			}
		});

		entradacontrol = new EntradaController(txPesquisa, table, txNomeComercial, txComposicao, txValorComprado,
				txQuantidadeComprimidos, txtInsiraAQuantidade, txidProduto, entrada, entradadao, remediosemestoque,
				remediodao,remedio,entradabo);

	}

}
