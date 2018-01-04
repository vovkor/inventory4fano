package ru.nw.vir.excel;

//import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Iterator;

import ru.nw.vir.dao.AccRepository;
import org.springframework.beans.factory.annotation.Autowired;

// надо сделать DI
//import org.springframework.stereotype.Component;
//@Component 

public class ExcelParser {

    //@Autowired
    private final AccRepository accRepository;
	
    //@Autowired
    public ExcelParser(AccRepository accRepository) {
        this.accRepository = accRepository;
    }

	// убрал static
    public String parse(String fileName) throws SQLException {
    //инициализируем потоки
        String result = "";
        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
		Integer rowCounter = 0;
		Integer cellCounter = 0;

		String accenumb = "";
		String collmunb = "";
		String collcode = "";
		String expedition = "";
		String cropname = "";
		String genus = "";
		String species = "";
		String spauthor = "";
		String subtaxa = "";
		String subtauthor = "";
		String accename = "";
		String accename_rus = "";
		String acqdate = "";
		String origcty = "";
		String collsite = "";
		String collsite_rus = "";
		String latitude = "";
		String longitude = "";
		String elevation = "";
		String colldate = "";
		String bredcode = "";
		String sampstat = "";
		String ancest = "";
		String ancest_rus = "";
		String collsrc = "";
		String doncty = "";
		String donorcode = "";
		String donornumb = "";
		String othernumb = "";
		String duplsite = "";
		String storage = "";
		String lifform = "";
		String intrnumb = "";
		String remarks = "";
		String owner = "serhl";
		
		int MY_MINIMUM_COLUMN_COUNT = 33;

		
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		DataFormatter objDefaultFormat = new DataFormatter();
		//FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workBook);		
		
     //разбираем первый лист входного файла на объектную модель
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
     //проходим по всему листу
        while (it.hasNext()) {
            Row row = it.next();
            //Iterator<Cell> cells = row.iterator();
			rowCounter++;
			// первая строка - заголовок
			if (rowCounter == 1)	{ continue; }
			
			cellCounter = 0;
			cellCounter++;
			int lastColumn = Math.max(row.getLastCellNum(), MY_MINIMUM_COLUMN_COUNT);

			for (int cn = 0; cn < lastColumn; cn++) {
				Cell cell = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
				if (cell == null) {
				// The spreadsheet is empty in this cell
				result = "";
				}
				else{
					int cellType = cell.getCellType();
					//перебираем возможные типы ячеек
					switch (cellType) {
						case Cell.CELL_TYPE_STRING:
							result = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							// для числовых значение добавляет в конце .0
							// поэтому отформатируем
							result = objDefaultFormat.formatCellValue(cell); // Returns the formatted value of a cell as a String regardless of the cell type.
							break;
						default:
							result = "";
							break;
					}
				}
                switch (cn+1) {
                    case 1:
                        accenumb = result;
                        break;
                    case 2:
                        collmunb = result;
                        break;
                    case 3:
                        collcode = result;
                        break;
                    case 4:
                        expedition = result;
                        break;
                    case 5:
                        cropname = result;
                        break;
                    case 6:
                        genus = result;
                        break;						
                    case 7:
                        species = result;
                        break;
                    case 8:
                        spauthor = result;
                        break;
                    case 9:
                        subtaxa = result;
                        break;
                    case 10:
                        subtauthor = result;
                        break;
                    case 11:
                        accename = result;
                        break;
                    case 12:
                        accename_rus = result;
                        break;
                    case 13:
                        acqdate = result;
                        break;
                    case 14:
                        origcty = result;
                        break;
                    case 15:
                        collsite = result;
                        break;
                    case 16:
                        collsite_rus = result;
                        break;
                    case 17:
                        latitude = result;
                        break;
                    case 18:
                        longitude = result;
                        break;
                    case 19:
                        elevation = result;
                        break;
                    case 20:
                        colldate = result;
                        break;
                    case 21:
                        bredcode = result;
                        break;
                    case 22:
                        sampstat = result;
                        break;
                    case 23:
                        ancest = result;
                        break;
                    case 24:
                        ancest_rus = result;
                        break;
                    case 25:
                        collsrc = result;
                        break;
                    case 26:
                        doncty = result;
                        break;
                    case 27:
                        donorcode = result;
                        break;
                    case 28:
                        donornumb = result;
                        break;
                    case 29:
                        othernumb = result;
                        break;
                    case 30:
                        duplsite = result;
                        break;
                    case 31:
                        storage = result;
                        break;
                    case 32:
                        lifform = result;
                        break;
                    case 33:
                        intrnumb = result;
                        break;
                    case 34:
                        remarks = result;
                        break;
						
                    default:
                        result = "";
                        break;
                }				
				
            }
			
			//accRepository.addAccTest("Test", "test1");
			accRepository.addAcc(accenumb,collmunb,collcode,expedition,cropname,genus,species,spauthor,subtaxa,subtauthor,accename,accename_rus,acqdate,origcty,collsite,collsite_rus,latitude,longitude,elevation,colldate,bredcode,sampstat,ancest,ancest_rus,collsrc,doncty,donorcode,donornumb,othernumb,duplsite,storage,lifform,intrnumb,remarks,owner);
            //result += "\n";
        }
		//inputStream.close();
        //return "{\"id\":1}";
		return "parseFinished";
    }
	
	//------------------------ не используется --------------------------//
    public static String parse_orginal(String fileName) {
    //инициализируем потоки
        String result = "";
        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
     //разбираем первый лист входного файла на объектную модель
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
     //проходим по всему листу
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
      //перебираем возможные типы ячеек
                switch (cellType) {
                    case Cell.CELL_TYPE_STRING:
                        result += cell.getStringCellValue() + "=";
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        result += "[" + cell.getNumericCellValue() + "]";
                        break;
 
                    case Cell.CELL_TYPE_FORMULA:
                        result += "[" + cell.getNumericCellValue() + "]";
                        break;
                    default:
                        result += "|";
                        break;
                }
            }
            result += "\n";
        }
 
        return result;
    }	
 
}
