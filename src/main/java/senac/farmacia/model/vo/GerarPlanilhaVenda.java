package senac.farmacia.model.vo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class GerarPlanilhaVenda {
	
	public void gerarPlanilhaVendas(List<Venda> vendas, String caminho) {
		String[] colunasPlanilha = { "id", "Laboratório", "NomeComercial", "Composicao", "Concentracao", "ValorVenda ", "ValorVendido","quantidade"};
		
		HSSFWorkbook planilha = new HSSFWorkbook();
		
		HSSFSheet abaPlanilha = planilha.createSheet("Venda");
		
		Row headerRow = abaPlanilha.createRow(0);
		
		for(int i = 0; i < colunasPlanilha.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(colunasPlanilha[i]);
		}
		int rowNum = 1;
		for (Venda venda : vendas) {
			Row novaLinha =abaPlanilha.createRow(rowNum++);
			
			novaLinha.createCell(0).setCellValue(venda.getIdVenda());
			novaLinha.createCell(1).setCellValue(venda.getRemedio().getLaboratorio());
			novaLinha.createCell(2).setCellValue(venda.getRemedio().getNomecomercial());
			novaLinha.createCell(3).setCellValue(venda.getRemedio().getComposiçao());
			novaLinha.createCell(4).setCellValue(venda.getRemedio().getConcentraçao());
			novaLinha.createCell(5).setCellValue(venda.getValorVenda());
			novaLinha.createCell(6).setCellValue(venda.getValorVendido());
			novaLinha.createCell(7).setCellValue(venda.getQuantidade());
			
		}
		
		for(int i = 0; i < colunasPlanilha.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}
		
		FileOutputStream fileOut = null;
		
		try {
			fileOut = new FileOutputStream(caminho + ".xls");
			planilha.write(fileOut);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if (fileOut != null) {
				try {
					fileOut.close();
					planilha.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
