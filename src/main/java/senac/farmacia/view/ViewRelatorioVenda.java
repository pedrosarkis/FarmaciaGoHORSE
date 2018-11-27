package senac.farmacia.view;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import senac.farmacia.model.dao.VendaDAO;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;


public class ViewRelatorioVenda extends JInternalFrame{
	private JTable table;
	public ViewRelatorioVenda() throws Exception {
		setClosable(true);
		setTitle("Relatório Vendas");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 107, 610, 189);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(16, 21, 85, 16);
		getContentPane().add(lblDataInicial);
		
		JLabel lblDatafinal = new JLabel("DataFinal");
		lblDatafinal.setBounds(346, 21, 61, 16);
		getContentPane().add(lblDatafinal);
		
		JButton btnGerarXml = new JButton("Gerar XML");
		btnGerarXml.setBounds(237, 301, 117, 29);
		getContentPane().add(btnGerarXml);
		
		final JFormattedTextField txtDataInicial = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataInicial.setBounds(98, 16, 127, 26);
		getContentPane().add(txtDataInicial);
		
		final JFormattedTextField txtDataFinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataFinal.setBounds(425, 16, 127, 26);
		getContentPane().add(txtDataFinal);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				Date dataInicio = null;
				Date dataFim = null;
				
				try {
					dataInicio = simpleDateFormat.parse(txtDataInicial.getText());
					dataFim = simpleDateFormat.parse(txtDataFinal.getText());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				 
				Date dataHoje = new Date();
				
				if (dataFim.after(dataHoje)) {
					JOptionPane.showMessageDialog(null, "Data final não pode ser superior a data de hoje");
					return;
				}
				
				VendaDAO vendaDAO = new VendaDAO();
				vendaDAO.listarTodosDeAcordoComData(dataInicio, dataFim);
				
			}
		});
		
		btnPesquisar.setBounds(237, 66, 117, 29);
		getContentPane().add(btnPesquisar);
	}
}
