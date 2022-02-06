package SR.venueSR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class menuListHomePage {
	
	public static String cellToString;
	
	public static  void menuList() throws FileNotFoundException {
		
		int cn = 0;
		String [] separator = {"\n","\n","\n\n\n"};
		
		try {
			final File theFile = new File(".\\Databases\\menuCourses.xlsx");
			final FileInputStream fileInputStream = new FileInputStream(theFile);
			Workbook workbookExcel = new XSSFWorkbook(fileInputStream);
			XSSFSheet excelSheet = (XSSFSheet) workbookExcel.getSheetAt(0);
			
			Iterator<Row> rowIterator = excelSheet.rowIterator();
					
				while (rowIterator.hasNext()) {
					
					Row searchRow = rowIterator.next();
					
					if (searchRow.getRowNum() == 0) {
						continue;
					}
					
				Iterator<Cell> cellIterator = searchRow.cellIterator();
					
						while (cellIterator.hasNext()) {
							
							Cell searchCell = cellIterator.next();
							
								if ( searchCell.getRowIndex() != 0 && searchCell.getCellType() != CellType.BLANK ) {
									switch (searchCell.getCellType()) {
									case NUMERIC:
										DataFormatter dtFormatter = new DataFormatter();
										cellToString = dtFormatter.formatCellValue(searchCell).toString();
									//	System.out.println("\t" +  + "\t");
										System.out.println("numeric");
										cellToString += cellToString + separator[cn];
										break;
									case STRING:
										cellToString += searchCell.getStringCellValue() + separator[cn];
								//		System.out.println("\t" + searchCell.getStringCellValue().toString() + "\t");
										break;
									default:
										System.out.println("");
										break;
									}
									cn++;
									if (cn == 3) {
										cn = 0;
								}
								
								}
						} // while 2
						
				System.out.println("");
					
				} // while 1
				
				fileInputStream.close();
			
		} catch (Exception e) {
			System.out.println("File not found. Please locate the file");
			e.printStackTrace();
		}
			
			
	}
	

	public static void main(String[] args) {
//		try {
//			menuList();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
	}

}
