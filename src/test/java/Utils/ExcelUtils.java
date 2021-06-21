package Utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelUtils {
	
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public ExcelUtils(XSSFWorkbook _workbook,XSSFSheet _worksheet)
	{
		this.workbook=_workbook;
		this.worksheet=_worksheet;
	}
	
	public static int GetRowCount() 
	{
		return worksheet.getPhysicalNumberOfRows();
	}
	private static String GetCellValue(int rowIndex, int columnIndex) throws IOException
	{
		return worksheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
	}
	
	private static int GetColumnIndex(String columnName)
	{
		Row row = worksheet.getRow(0);
		int cellCount = row.getPhysicalNumberOfCells();
		for(int i=0;i<cellCount;i++)
		{
			if(row.getCell(i).toString().equals(columnName))
			{
				return i;				
			}			
		}
		return 9999999;
	}
	
	public static String GetCredentials(int rowIndex, String credentialType) throws IOException
	{
		int columnIndex =GetColumnIndex(credentialType);
		return GetCellValue(rowIndex,columnIndex);
	}
}
