package com.example.springBootTechlead.service.core4;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class Core4Service {
    public static Workbook openFile(String filePath) {
        FileInputStream file = null;
        Workbook workbook;
        try {
            file = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(file);
            return workbook;
        } catch (IOException e) {
            System.out.println("Lỗi khi mở file: " + e.getMessage());
        } finally {
            try {
                if (file != null) file.close(); // Đóng FileInputStream
            } catch (IOException e) {
                System.out.println("Lỗi khi đóng file: " + e.getMessage());
            }
        }
        return null;
    }

    public  LinkedHashMap<String, Double> layTenCaLamViec(Workbook workbook){
        LinkedHashMap<String, Double> workShifts = new LinkedHashMap<>();
        Sheet sheet = workbook.getSheetAt(0);
        List<String> nameWorkShift = new ArrayList<>();

        for(int colindex = 3; colindex <= 16; colindex++){
            Row row = sheet.getRow(5);
            String name = String.valueOf(row.getCell(colindex));
            if (name.isEmpty()) continue;
            if(!name.equals("$")){
                nameWorkShift.add(name);
            }else{
                for(String s: nameWorkShift){
                    workShifts.put(s,0.0);
                }
            }
        }
        //System.out.println(workShifts);
        return workShifts;
    }
    private static String getCellValue(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue().toString();
                }
                yield String.valueOf(cell.getNumericCellValue()); // Nếu là ngày tháng
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> String.valueOf(evaluator.evaluate(cell).getNumberValue()); // Lấy giá trị thực của công thức
            case BLANK -> "";
            default -> "UNKNOWN";
        };
    }

    public LinkedHashMap<String, LinkedHashMap<String, Double>> layTienLuongCuaMoiCa(Workbook workbook){
        LinkedHashMap<String, LinkedHashMap<String, Double>> tienLuongMoiNgay = new LinkedHashMap<>();
        LinkedHashMap<String, Double> salary;
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(0);

        List<String> nameWorkShift = new ArrayList<>();

        for(int rowIndex = 6 ; rowIndex <= sheet.getLastRowNum(); rowIndex++){
            salary = layTenCaLamViec(workbook);
            Row row = sheet.getRow(rowIndex);
            //System.out.println(row.getCell(2));
            String index = String.valueOf(row.getCell(1));
            if(index.trim().isEmpty()) break;
            //System.out.println(index);
            for(int colindex = 3; colindex <= 15; colindex++){
//                    String cellName = String.valueOf(row.getCell(colindex));
//                    System.out.println(getCellValue(row.getCell(colindex), evaluator));
                String valueString = getCellValue(row.getCell(colindex), evaluator);
                Double value = 0.0;
                if(!valueString.isEmpty()){
                    value = Double.parseDouble(valueString);
                }
                Row rowName = sheet.getRow(5);
                String name = String.valueOf(rowName.getCell(colindex));
                //System.out.println(name);
                if(name.isEmpty()) continue;
                if(!name.equals("$") ){
                    nameWorkShift.add(name);
                    //System.out.println(name);
                }
                else{
                    for(String s: nameWorkShift){
                        //System.out.println(s+" " + value);
                        salary.put(s,value);
                    }
                    nameWorkShift = new ArrayList<>();
                }
            }
            //System.out.println(salary);
            String maNV = String.valueOf(row.getCell(1));
//                System.out.println(maNV);
            if(!maNV.trim().isEmpty()){
//                    System.out.println("null");
                tienLuongMoiNgay.put(maNV, salary);
            }
        }
        //System.out.println(tienLuongMoiNgay);
        return tienLuongMoiNgay;
    }

    public LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap<String, Double>>>> laySoGioLamViec(Workbook workbook) {
        LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap<String, Double>>>> gioCongTrongThang = new LinkedHashMap<>();

        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(0);
        List<String> ngayCong = getNgayCong(sheet, evaluator);
        //System.out.println(ngayCong);
        Row row = sheet.getRow(3);
        for (int rowIndex = 6; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            String manv = String.valueOf(row.getCell(1));
            if(manv.trim().isEmpty()) break;

            LinkedHashMap<String, List<LinkedHashMap<String, Double>>> cacCaTrongNgay = new LinkedHashMap<>();
            int colIndex = 17;
            List<LinkedHashMap<String, Double>> danhSachCa = new ArrayList<>();
            //System.out.println(ngayCong);
            for (int index = 0; index < ngayCong.size(); index++) {
                LinkedHashMap<String, Double> gioCongTrongCa = getGioCongTrongCa(sheet, evaluator, rowIndex, colIndex++);
                danhSachCa.add(gioCongTrongCa);

                // Nếu ngày sau khác ngày hiện tại hoặc là ngày cuối cùng trong danh sách
                if (index == ngayCong.size() - 1 || !ngayCong.get(index).equals(ngayCong.get(index + 1))) {
                    cacCaTrongNgay.put(ngayCong.get(index), new ArrayList<>(danhSachCa));
                    danhSachCa.clear();
                }
            }

            //System.out.println(cacCaTrongNgay);
            String maNV = String.valueOf(sheet.getRow(rowIndex).getCell(1));
            if(!maNV.trim().isEmpty()){
                gioCongTrongThang.put(maNV, cacCaTrongNgay);
            }
        }
        return gioCongTrongThang;
    }
    public static LinkedHashMap<String, Double> getTongLuong(Workbook workbook){
        LinkedHashMap<String, Double> totalSalary = new LinkedHashMap<>();
        Sheet sheet = workbook.getSheetAt(0);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        for(int rowIndex = 6; rowIndex <= sheet.getLastRowNum(); rowIndex++){
            Row row = sheet.getRow(rowIndex);
            String manv = String.valueOf(row.getCell(1));
            double salary;

            if(!manv.trim().isEmpty()){
//                    DecimalFormat df = new DecimalFormat("#");
                String salaryString = getCellValue(row.getCell(16), evaluator);
                salary =  Double.parseDouble(salaryString);
                //System.out.println(df.format(salary));
                totalSalary.put(manv, salary);
            }
        }
        return totalSalary;
    }

    private List<String> getNgayCong(Sheet sheet, FormulaEvaluator evaluator) {
        Row row= sheet.getRow(3);
        List<String> ngayCong = new ArrayList<>();
        for(int colIndex = 17; colIndex < row.getLastCellNum(); colIndex++){
            Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            // kiểm tra xem ô có thuộc vùng gộp không
            CellRangeAddress mergedRegion = getMergedRegion(sheet, cell);
            if (mergedRegion != null){
                // Nếu ô thuộc vùng gộp, lấy giá trị từ ô đầu tiên của vùng gộp
                Row firstRow = sheet.getRow(mergedRegion.getFirstRow());
                Cell firstCell = firstRow.getCell(mergedRegion.getFirstColumn());
                if(getCellValue(firstCell, evaluator) != ""){
                    ngayCong.add(getCellValue(firstCell,evaluator));
                }

                //System.out.println("Ô gộp tại [" + (row.getRowNum() + 1) + ", " + (colIndex + 1) + "]: " + getCellValueAsString(firstCell));
            } else {
                if(getCellValue(cell,evaluator) != "") {
                    ngayCong.add(getCellValue(cell, evaluator));
                }
                // Nếu không phải ô gộp, xử lý bình thường
                //System.out.println("Ô tại [" + (row.getRowNum() + 1) + ", " + (colIndex + 1) + "]: " + getCellValueAsString(cell));
            }
        }
        return ngayCong;
    }

    private static LinkedHashMap<String, Double> getGioCongTrongCa(Sheet sheet, FormulaEvaluator evaluator, int rowIndex, int colIndex) {
        LinkedHashMap<String, Double> gioCongTrongCa = new LinkedHashMap<>();
        Row shiftName = sheet.getRow(5);
        String shiftNameString = getCellValue(shiftName.getCell(colIndex), evaluator);

        Row hourWorkingRow = sheet.getRow(rowIndex);
        String hourWorkingString = getCellValue(hourWorkingRow.getCell(colIndex), evaluator);

        if (hourWorkingString.isEmpty() || hourWorkingString.equals("-")) {
            hourWorkingString = "0.0";
        }
        Double hourWorkingDouble = Double.parseDouble(hourWorkingString);
        //System.out.println(hourWorkingDouble);
        gioCongTrongCa.put(shiftNameString, hourWorkingDouble);
        return gioCongTrongCa;
    }


    // Phương thức kiểm tra xem ô có thuộc vùng gộp không
    private static CellRangeAddress getMergedRegion(Sheet sheet, Cell cell) {
        for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
            if (mergedRegion.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
                return mergedRegion;
            }
        }
        return null;
    }

    public LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap<String, Double>>>> printInformation(String filePath){
        Workbook workbook = openFile(filePath);
        if (workbook == null){
            return null;
        }
        LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap<String, Double>>>> gioCongTrongThang = laySoGioLamViec(workbook);
        LinkedHashMap<String, LinkedHashMap<String, Double>> tienLamTrongCa = layTienLuongCuaMoiCa(workbook);

        DecimalFormat df = new DecimalFormat("#");

        LinkedHashMap<String, Double> luong = getTongLuong(workbook);
        //System.out.println(luong);
        // Duyệt qua từng nhân viên trong dữ liệu
        for (Map.Entry<String, LinkedHashMap<String, List<LinkedHashMap<String, Double>>>> entry : gioCongTrongThang.entrySet()) {
            String employee = entry.getKey(); // "employee1"
            double totalMoneyInMonth = 0.0;
            double totalTimeWorkingInMonth = 0.0;
            LinkedHashMap<String, List<LinkedHashMap<String, Double>>> shifts = entry.getValue();

            System.out.println("Employee: " + employee);  // In tên nhân viên

            // Duyệt qua các ngày làm việc của nhân viên
            boolean hasWorkedInAnyDay = false;  // Biến này để kiểm tra có ngày nào làm việc không

            for (Map.Entry<String, List<LinkedHashMap<String, Double>>> shiftEntry : shifts.entrySet()) {
                String shiftDate = shiftEntry.getKey(); // "day1"
                List<LinkedHashMap<String, Double>> shiftList = shiftEntry.getValue();

                double totalTimeInDay = 0.0;
                double totalMoneyInDay = 0.0;

                // Kiểm tra xem có ca làm việc nào trong ngày không
                boolean hasWorked = false;

                // Duyệt qua danh sách các ca làm việc trong ngày
                for (LinkedHashMap<String, Double> shiftDetails : shiftList) {
                    // Duyệt qua từng ca làm việc và tính tổng giờ và tiền
                    for (Map.Entry<String, Double> detail : shiftDetails.entrySet()) {
                        String key = detail.getKey();
                        Double value = detail.getValue(); // số giờ làm việc trong ca

                        if (value > 0) {
                            hasWorked = true;  // Nếu có giờ làm việc, đánh dấu là có làm việc

                            // Cập nhật tổng số giờ làm việc trong ngày
                            totalTimeInDay += value;
                            totalTimeWorkingInMonth += value;

                            // Tính số tiền theo ca làm việc
                            Double money = tienLamTrongCa.get(employee).get(key);
                            if (money != null) {
                                Double tienLinh = money * value;
                                totalMoneyInDay += tienLinh;
                                totalMoneyInMonth += tienLinh;

                                System.out.println("    " + key + ": " + value + " giờ, được " + df.format(tienLinh) + " VND");
                            } else {
                                System.out.println("    " + key + ": " + value + " giờ, không có thông tin tiền lương.");
                                System.out.println("    Date: " + df.format(Double.parseDouble(shiftDate)));
                                hasWorked = false;
                                System.out.println();
                            }
                        }
                    }
                }

                // Nếu có làm việc trong ngày, in ra tổng số giờ và số tiền
                if (hasWorked) {
                    hasWorkedInAnyDay = true;  // Đánh dấu có ngày làm việc
                    System.out.println("    Date: " + df.format(Double.parseDouble(shiftDate)));  // In ngày làm việc
                    System.out.println("    Total time worked: " + totalTimeInDay + " hours");
                    System.out.println("    Total money earned: " + df.format(totalMoneyInDay) + " VND");
                    System.out.println();  // Dòng trống sau mỗi ngày
                }
            }
            System.out.println("Total time working in month is: "+ totalTimeWorkingInMonth);
            System.out.println("Total money earned in month is: "+ df.format(totalMoneyInMonth) +" compare to total salary is "+ df.format(luong.get(employee)));
            System.out.println();

            // Nếu nhân viên không làm việc ngày nào, thông báo rõ ràng
            if (!hasWorkedInAnyDay) {
                System.out.println("    No work days for this employee.");
            }
        }
        return gioCongTrongThang;
    }

}
