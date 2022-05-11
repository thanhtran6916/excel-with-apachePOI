package com.example.excelwithapachepoi.service;

import com.example.excelwithapachepoi.entity.Option;
import com.example.excelwithapachepoi.repository.OptionRepository;
import com.example.excelwithapachepoi.util.Constant;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements ServiceGeneration<Option> {

    private final OptionRepository optionRepository;

    @Override
    public Option get(String code) {
        return optionRepository.getById(code);
    }

    @Override
    public Option save(Option student) {
        return optionRepository.save(student);
    }

    @Override
    public void delete(String code) {
        optionRepository.deleteById(code);
    }

    public List<Option> saveAllExcel(String url) {
        Map<Integer, List<String>> data = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(url);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            int i = 0;
            while (rowIterator.hasNext()) {
                data.put(i, new ArrayList<String>());
                Row row = rowIterator.next();
                for (int j = 0; j < 8; j++) {
                    Cell cell = row.getCell(j);
                    if (ObjectUtils.isEmpty(cell)) {
                        data.get(i).add(null);
                        continue;
                    }
                    switch (cell.getCellType()) {
                        case STRING: {
                            data.get(i).add(cell.getRichStringCellValue().getString());
                            break;
                        }
                        case NUMERIC: {
                            if (DateUtil.isCellDateFormatted(cell)) {
                                data.get(i).add(cell.getDateCellValue() + "");
                            } else {
                                data.get(i).add(cell.getNumericCellValue() + "");
                            }
                            break;
                        }
                        case BOOLEAN: {
                            data.get(i).add(cell.getBooleanCellValue() + "");
                            break;
                        }
                        case FORMULA: {
                            data.get(i).add(cell.getCellFormula() + "");
                            break;
                        }
                        default:
                            data.get(i).add(null);
                    }
                }
                i++;
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Option> optionList = Constant.mapOptions(data);
        List<Option> optionResult = new ArrayList<>();
        for (Option option : optionList) {
            optionResult.add(optionRepository.save(option));
        }
        return optionResult;
    }

    public String getAllExcel() {
        String url = "C:\\Users\\admin\\Desktop\\option.xlsx";
        List<Option> optionList = optionRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("option");
        Row rowHeader = sheet.createRow(0);

        CellStyle cellStyleHeader = Constant.CellOption.cellHeader(workbook);

        CellStyle style = workbook.createCellStyle();
        style=row.getRowStyle();
        style.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font);
        rowHeader.setRowStyle(style);

        Cell cellHeaderCode = rowHeader.createCell(0);
//        cellHeaderCode.setCellStyle(cellStyleHeader);
        cellHeaderCode.setCellValue("Code");

        for (int i = 0; i < optionList.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Option option = optionList.get(i);
            Cell cellCode = row.createCell(0);
            cellCode.setCellValue(!Constant.isNull(option.getCode()) ? option.getCode() : null);
            Cell cellDescription = row.createCell(1);
            cellDescription.setCellValue(!Constant.isNull(option.getDescription()) ? option.getDescription() : null);
            Cell cellName = row.createCell(2);
            cellName.setCellValue(!Constant.isNull(option.getName()) ? option.getName() : null);
            Cell cellOpOrder = row.createCell(3);
            cellOpOrder.setCellValue(!Constant.isNull(option.getOpOrder()) ? option.getOpOrder() : null);
            Cell cellStatus = row.createCell(4);
            cellStatus.setCellValue(!Constant.isNull(option.getStatus()) ? option.getStatus() : null);
            Cell cellType = row.createCell(5);
            cellType.setCellValue(!Constant.isNull(option.getType()) ? option.getType() : null);
            Cell cellValue = row.createCell(6);
            cellValue.setCellValue(!Constant.isNull(option.getValue()) ? option.getValue() : null);
            Cell cellValuePortal = row.createCell(7);
            cellValuePortal.setCellValue(!Constant.isNull(option.getValuePortal()) ? Integer.valueOf(option.getValuePortal()) : null);
        }
        File file = new File(url);
        try {
            FileOutputStream fileXcel = new FileOutputStream(file);
            workbook.write(fileXcel);
            fileXcel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
