package com.example.excelwithapachepoi.util;

import com.example.excelwithapachepoi.entity.Option;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Constant {
    
    public static List<Option> mapOptions(Map<Integer, List<String>> data) {
        List<Option> optionList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
            List<String> optionString = entry.getValue();
            Option option = Option.builder()
                    .code(!ObjectUtils.isEmpty(optionString.get(0)) ? optionString.get(0) : null)
                    .name(!ObjectUtils.isEmpty(optionString.get(1)) ? optionString.get(1) : null)
                    .type(!ObjectUtils.isEmpty(optionString.get(2)) ? optionString.get(2) : null)
                    .status(!ObjectUtils.isEmpty(optionString.get(3)) ?
                            Integer.valueOf(optionString.get(3).replaceAll("\\..+", "")) : null)
                    .opOrder(!ObjectUtils.isEmpty(optionString.get(4)) ?
                            Integer.valueOf(optionString.get(4).replaceAll("\\..+", "")) : null)
                    .value(!ObjectUtils.isEmpty(optionString.get(5)) ?
                            Integer.valueOf(optionString.get(5).replaceAll("\\..+", "")) : null)
                    .description(!ObjectUtils.isEmpty(optionString.get(6)) ? optionString.get(6) : null)
                    .valuePortal(!ObjectUtils.isEmpty(optionString.get(7)) ? optionString.get(7) : null)
                    .build();
            optionList.add(option);
        }
        return optionList;
    }

    public static boolean isNull(Object obj) {
       return obj == null;
    }

    public static class CellOption {
        public static final CellStyle cellHeader(Workbook workbook) {
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            return style;
        }


    }

}
