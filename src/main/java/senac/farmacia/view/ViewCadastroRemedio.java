package senac.farmacia.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import senac.farmacia.controller.RemedioController;
import senac.farmacia.model.bo.RemedioBO;
import senac.farmacia.model.dao.RemedioDAO;
import senac.farmacia.model.vo.Remedio;

// Tela Ricardo 
public class ViewCadastroRemedio extends JInternalFrame {

	/**
	 * 
	 */
	
	private JTextField txtMarca;
	private JTextField txtNomeComercial;
	private JTextField txtComposicao;
	private JTextField txtMiligrama;
	private JTextField txtQuantidadeComprimido;
	private JTextField txtPreco;
	private RemedioDAO remediodao;
	private RemedioController remediocontrol;
	private Remedio remedio = null;
	private RemedioBO remediobo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewCadastroRemedio frame = new ViewCadastroRemedio();
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
	public ViewCadastroRemedio() {
		setClosable(true);
		setBounds(100, 100, 602, 419);
		getContentPane().setLayout(null);

		JLabel lblMarca = new JLabel("Laboratório : ");
		lblMarca.setBounds(19, 30, 98, 16);
		getContentPane().add(lblMarca);

		txtMarca = new JTextField();
		txtMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
					e.consume();
				} 
			}
		});
		txtMarca.setBounds(210, 25, 351, 26);
		getContentPane().add(txtMarca);
		txtMarca.setColumns(10);

		JLabel nomeComercial = new JLabel("Nome Comercial:");
		nomeComercial.setBounds(19, 68, 121, 16);
		getContentPane().add(nomeComercial);

		txtNomeComercial = new JTextField();
		txtNomeComercial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c) ) {
					e.consume();
				} 
			}
		});
		txtNomeComercial.setBounds(210, 63, 351, 26);
		getContentPane().add(txtNomeComercial);
		txtNomeComercial.setColumns(10);

		JLabel lblComposicao = new JLabel("Composição :");
		lblComposicao.setBounds(19, 108, 98, 16);
		getContentPane().add(lblComposicao);

		txtComposicao = new JTextField();
		txtComposicao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c) ) {
					e.consume();
				} 
			}
		});
		txtComposicao.setBounds(210, 103, 351, 26);
		getContentPane().add(txtComposicao);
		txtComposicao.setColumns(10);

		JLabel lblMiligrama = new JLabel("Miligrama:");
		lblMiligrama.setBounds(19, 152, 98, 16);
		getContentPane().add(lblMiligrama);

		txtMiligrama = new JTextField();
		txtMiligrama.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					e.consume();
				} 
			}
		});
		txtMiligrama.setBounds(210, 147, 130, 26);
		getContentPane().add(txtMiligrama);
		txtMiligrama.setColumns(10);

		JLabel lblQuantidadecomprimidos = new JLabel("Quantidade de Comprimidos:");
		lblQuantidadecomprimidos.setBounds(19, 194, 194, 16);
		getContentPane().add(lblQuantidadecomprimidos);

		txtQuantidadeComprimido = new JTextField();
		txtQuantidadeComprimido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					e.consume();
				}
			}
		});
		txtQuantidadeComprimido.setBounds(210, 189, 130, 26);
		getContentPane().add(txtQuantidadeComprimido);
		txtQuantidadeComprimido.setColumns(10);

		JLabel lblPreco = new JLabel("Preço:");
		lblPreco.setBounds(19, 235, 61, 16);
		getContentPane().add(lblPreco);

		txtPreco = new JTextField();
		txtPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)){
					e.consume();

				}

			}
		});
		txtPreco.setBounds(210, 230, 130, 26);
		getContentPane().add(txtPreco);
		txtPreco.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remediocontrol.SalvarAction();
			}
		});
		btnSalvar.setBounds(208, 312, 117, 55);
		getContentPane().add(btnSalvar);
		
		remediocontrol = new RemedioController(txtMarca, txtNomeComercial, txtComposicao, txtMiligrama, txtQuantidadeComprimido, txtPreco, remediodao, remedio,remediobo);
				
	

	}
}
