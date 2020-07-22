import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	
	public static void main(String[] args) {
		String kanbanNo =  String.format("%-7s", "200608-6439711");
		System.out.println(kanbanNo);
	}
		
	public static void test1(String[] args) {
		List<domain> list = new ArrayList<domain>();
		File excelFile = new File("D:\\入库实绩.xlsx");
		FileInputStream fis;
		domain dom = null;
		try {
			fis = new FileInputStream(excelFile);
			XSSFWorkbook xss = new XSSFWorkbook(fis);
			for (int numSheet = 0; numSheet <= xss.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xss.getSheetAt(numSheet);
				if(xssfSheet == null){
					continue;
				}
				System.out.println(xssfSheet.getLastRowNum());
				for (int numRow = 1; numRow < xssfSheet.getLastRowNum(); numRow++) {
					XSSFRow xssfRow = xssfSheet.getRow(numRow);
					if(xssfRow!=null){
						dom = new domain();
						XSSFCell cell0 = xssfRow.getCell(0);
						XSSFCell cell1 = xssfRow.getCell(2);
						XSSFCell cell2 = xssfRow.getCell(3);
						dom.setNumber(getValue(cell0));
						dom.setNumber1(getValue(cell1));
						dom.setNumber2(getValue(cell2));
						list.add(dom);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (domain domain : list) {
			System.out.println(domain);
			
		}
		
	}
	
public static String getValue(XSSFCell xssfCell){
        
        if(xssfCell.getCellType()==xssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        }else if(xssfCell.getCellType()==xssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf((int)xssfCell.getNumericCellValue());
        }else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
}
