package FileHandlers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class fileMethods {
    public void inputDataIntoFile(Workbook workbook, String date, DefaultTableModel scheduleValues, FooterDTO footer) {
        Sheet sheet = workbook.getSheetAt(0);
        Cell monthReference = sheet.getRow(3).getCell(0);
        try {
            monthReference.setCellValue(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao processar a data de referência",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }

        for (int row = 0; row < scheduleValues.getRowCount(); row++) {
            for (int col = 0; col < scheduleValues.getColumnCount(); col++) {
                sheet.getRow(row + 6).getCell(col).setCellValue(scheduleValues.getValueAt(row, col).toString());
            }
        }

        Cell normalEH = sheet.getRow(43).getCell(1);
        normalEH.setCellValue(footer.getNormalEH());
        Cell specialEH = sheet.getRow(44).getCell(1);
        specialEH.setCellValue(footer.getSpecialEH());
        Cell warningHours = sheet.getRow(45).getCell(1);
        warningHours.setCellValue(footer.getWarningHours());
        Cell nightlyHours = sheet.getRow(46).getCell(1);
        nightlyHours.setCellValue(footer.getNightlyHours());
    }
}
