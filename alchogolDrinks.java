package SR.venueSR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JComboBox;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class alchogolDrinks {
	
	public static ArrayList<String> alchoList = new ArrayList<>();
	public static String cellValue = "";
	public static String alchoEntry = "";
	public static String [] alchoString;
	
	public static XSSFSheet xlSheet;
	
	public static Workbook workbook;
	
	public static JComboBox<String> alchoDrinksBox;
	
	public static  ArrayList<String> alchogolList() throws FileNotFoundException {
		System.out.println("alchogolList");
		try {
			
			final File fl = new File(".\\Databases\\AlchogolDrinks.xlsx");
			final FileInputStream flStream = new FileInputStream(fl);
			 workbook = new XSSFWorkbook(flStream);
			 xlSheet = (XSSFSheet) workbook.getSheetAt(0);
			
		} catch (Exception e) {
			System.out.println("404");
		}
		
		int cn = 0;
		
		Iterator<Row> rowIterator = xlSheet.rowIterator();
		
			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();
				
				if (row.getRowNum() == 0) {
					continue;
				}
				
				Iterator<Cell> cellIterator = row.cellIterator();
				
					while (cellIterator.hasNext()) {
						cn++;
						if (cn > 2) {
							cn = 1;
						}
						Cell cellContents = (Cell) cellIterator.next();
						
							if (cellContents.getRowIndex() != 0  && cellContents.getCellType() != CellType.BLANK) {
								
								switch (cellContents.getCellType()) {
								case NUMERIC:
									DataFormatter formatter = new DataFormatter();
									cellValue = formatter.formatCellValue(cellContents).toString();
									break;
									
								case STRING:
									cellValue = cellContents.toString();
									break;

								default:
									break;
								} // eo switch
							} else {
								cellValue = null;
							}
							
							alchoEntry += cellValue + " ";
							if (cn == 2) {
								alchoList.add(alchoEntry);
								alchoEntry = "";
							}
					}
			}
			alchoList.toArray();
			alchoDrinksArray();
	return alchoList;
	}
	
	public static String [] alchoDrinksArray() {
		alchoString = new String[alchoList.size()];
			for (int i = 0; i < alchoString.length; i++) {
				alchoString[i] = alchoList.get(i);
			}
			Panel3.getComboArrays( "alcho" );
			return alchoString;
	}
	
	

	public static void main(String[] args) throws FileNotFoundException {
	}

}
