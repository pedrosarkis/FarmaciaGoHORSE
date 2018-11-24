package senac.farmacia.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ViewVenda viewVenda = new ViewVenda();
	ViewEntrada viewEntrada = new ViewEntrada();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrincipal frame = new ViewPrincipal();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public ViewPrincipal() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				setContentPane(viewVenda);
				viewVenda.show();

			}
		});

		setBackground(SystemColor.textHighlight);
		setTitle("FARMÁCIA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 448);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnVenda = new JMenu("Venda         ");
		mnVenda.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/icons8-comprar.png")));
		menuBar.add(mnVenda);

		JMenuItem mntmVenda = new JMenuItem("Venda");
		mntmVenda.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/icons8-comprar.png")));
		mntmVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setContentPane(viewVenda);
				viewVenda.setVisible(true);
			}
		});

		mnVenda.add(mntmVenda);

		JMenu mnMedicamento = new JMenu("Medicamento                           ");
		mnMedicamento.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/icons8-pílula-48.png")));
		menuBar.add(mnMedicamento);

		JMenuItem mntmCadastrarMedicamento = new JMenuItem("Cadastrar Medicamento");
		mntmCadastrarMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCadastroRemedio cadastroRemedio = new ViewCadastroRemedio();
				setContentPane(cadastroRemedio);
				cadastroRemedio.setVisible(true);
			}
		});
		mntmCadastrarMedicamento
				.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/icons8-pílula-48.png")));
		mnMedicamento.add(mntmCadastrarMedicamento);

		JMenuItem mntmPopularEstoque = new JMenuItem("Popular Estoque");
		mntmPopularEstoque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				setContentPane(viewEntrada);
				viewEntrada.show();

			}
		});
		mntmPopularEstoque
				.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/icons8-comprimidos-64.png")));
		mnMedicamento.add(mntmPopularEstoque);

		JMenu mnCliente = new JMenu("Cliente                            ");
		mnCliente.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/icons8-usuário.png")));
		menuBar.add(mnCliente);

		JMenuItem mntmCadastrarCliente = new JMenuItem("Cadastrar Cliente");
		mntmCadastrarCliente.setIcon(
				new ImageIcon(ViewPrincipal.class.getResource("/icones/icons8-adicionar-usuário-masculino.png")));
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCadastroCliente cadastroCliente = new ViewCadastroCliente();
				setContentPane(cadastroCliente);
				cadastroCliente.setVisible(true);

			}
		});
		mnCliente.add(mntmCadastrarCliente);

		JMenu mnFuncionrio = new JMenu("Funcionário");
		mnFuncionrio.setIcon(
				new ImageIcon(ViewPrincipal.class.getResource("/icones/icons8-suporte-on-line-filled.png")));
		menuBar.add(mnFuncionrio);

		JMenuItem mntmCadastrarFuncionrio = new JMenuItem("Cadastrar Funcionário");
		mntmCadastrarFuncionrio.setIcon(
				new ImageIcon(ViewPrincipal.class.getResource("/icones/icons8-suporte-on-line-filled.png")));
		mntmCadastrarFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCadastroFuncionario cadastroFuncionario = new ViewCadastroFuncionario();
				setContentPane(cadastroFuncionario);
				cadastroFuncionario.setVisible(true);

			}
		});
		mnFuncionrio.add(mntmCadastrarFuncionrio);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
