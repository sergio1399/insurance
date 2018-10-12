package sergio.ru.insurancetest.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.service.IntegrationService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class IntegrationServiceImpl implements IntegrationService {

    private static final Logger logger = LoggerFactory.getLogger(InsuranceServiceImpl.class);

    private static String[] columns = {"Серия/Номер", "Тип договора", "Дата подписания", "Начало действия", "Конец действия",
                                        "Сумма без НДС, руб", "Ставка НДС, %", "Сумма НДС, руб", "Сумма с НДС, руб", "Соответствие мин. сумме", "Номер ТС"};

    @Value("${excel.file-name}")
    private String fileName;

    @Override
    public void loadToExcel(List<ContractDto> contractList) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat,
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Contracts");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(ContractDto contractDto: contractList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(contractDto.getSerie() + "-" + contractDto.getNumber());

            row.createCell(1)
                    .setCellValue(contractDto.getType());

            Cell signDate = row.createCell(2);
            signDate.setCellValue(contractDto.getSignDate().toString());
            Cell openDate = row.createCell(3);
            openDate.setCellValue(contractDto.getOpenDate().toString());
            Cell expirationDate = row.createCell(4);
            expirationDate.setCellValue(contractDto.getExpirationDate().toString());
            row.createCell(5)
                    .setCellValue(contractDto.getSumNoNds());
            row.createCell(6)
                    .setCellValue(contractDto.getNdsRate());
            row.createCell(7)
                    .setCellValue(contractDto.getNdsSum());
            row.createCell(8)
                    .setCellValue(contractDto.getSumWithNds());
            row.createCell(9)
                    .setCellValue(contractDto.getMinSumAccord());
            row.createCell(10)
                    .setCellValue(contractDto.getVehicleNumber());
        }

        // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(fileName);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();

    }
}
