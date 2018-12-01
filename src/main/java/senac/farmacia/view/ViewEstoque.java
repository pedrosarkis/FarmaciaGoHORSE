package senac.farmacia.view;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.farmacia.controller.EstoqueController;
import senac.farmacia.model.dao.EstoqueDAO;
import senac.farmacia.model.vo.Estoque;

public class ViewEstoque extends JInternalFrame {
	private JTextField pesquisa;
	private JTable estoque;
	private EstoqueDAO estoquedao;
	private Estoque estoqueObjeto;
	private EstoqueController estoquecontrol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEstoque frame = new ViewEstoque();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewEstoque() {
		setBounds(100, 100, 909, 575);
		getContentPane().setLayout(null);
		
		JLabel lblPesquisarRemediosEm = new JLabel("Pesquisar Remedios em Estoque : \r\n");
		lblPesquisarRemediosEm.setBounds(26, 24, 168, 14);
		getContentPane().add(lblPesquisarRemediosEm);
		
		pesquisa = new JTextField();
		pesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				estoquecontrol.listarRemediosEmEstoque();
				
			}
		});
		pesquisa.setBounds(211, 21, 347, 20);
		getContentPane().add(pesquisa);
		pesquisa.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 106, 528, 250);
		getContentPane().add(scrollPane);
		
		estoque = new JTable();
		estoque.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"idRemedio", "NomeComercial", "Quantidade"
			}
		));
		scrollPane.setViewportView(estoque);
		
		estoquecontrol = new EstoqueController(pesquisa, estoque, estoqueObjeto, estoquedao);

	}
}
