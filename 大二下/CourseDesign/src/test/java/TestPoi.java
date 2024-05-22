import com.dto.EmpAllDTO;
import com.dto.EmpBasicDTO;
import com.pojo.EmpBasic;
import com.service.EmpService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: xuzifan
 * @Date: 2024/5/15 - 05 - 15 - 10:14
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
@SpringJUnitConfig(locations = "classpath:ApplicationContext.xml")
public class TestPoi {
    @Autowired
    EmpService empService;
    @Test
    public void test(){
/*        List<EmpAllDTO> list = empService.getDocument();
        System.out.println(list);*/
    }
    @Test
    public void createExcel() {
       /* List<EmpAllDTO> list = empService.getDocument();

        // 检查返回值是否为null
        if (list == null) {
            System.out.println("员工列表为空");
            return;
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("员工表");
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 10);
        sheet.addMergedRegion(region);

        HSSFRow hssfRow = sheet.createRow(0);
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("员工信息");

        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        headCell.setCellStyle(cellStyle);

        hssfRow = sheet.createRow(1);
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("员工编号");

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("姓名");

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("性别");

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("年龄");

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("职务");

        headCell = hssfRow.createCell(5);
        headCell.setCellValue("手机号");

        headCell = hssfRow.createCell(6);
        headCell.setCellValue("入职日期");

        headCell = hssfRow.createCell(7);
        headCell.setCellValue("领导编号");

        headCell = hssfRow.createCell(8);
        headCell.setCellValue("部门编号");

        headCell = hssfRow.createCell(9);
        headCell.setCellValue("照片");

        headCell = hssfRow.createCell(10);
        headCell.setCellValue("描述");

        try {
            for (int i = 0; i < list.size(); i++) {
                hssfRow = sheet.createRow(i + 2);
                EmpAllDTO empAllDTO = list.get(i);

                HSSFCell cell = hssfRow.createCell(0);
                cell.setCellValue(empAllDTO.getEmpno());
                cell.setCellStyle(cellStyle);

                cell = hssfRow.createCell(1);
                cell.setCellValue(empAllDTO.getEname());
                cell.setCellStyle(cellStyle);

                cell = hssfRow.createCell(2);
                cell.setCellValue(empAllDTO.getGender());
                cell.setCellStyle(cellStyle);

                cell = hssfRow.createCell(3);
                cell.setCellValue(empAllDTO.getAge() == null ? 0 : empAllDTO.getAge());
                cell.setCellStyle(cellStyle);

                cell = hssfRow.createCell(4);
                cell.setCellValue(empAllDTO.getJob());
                cell.setCellStyle(cellStyle);

                cell = hssfRow.createCell(5);
                cell.setCellValue(empAllDTO.getPhone());
                cell.setCellStyle(cellStyle);

                cell = hssfRow.createCell(6);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // 将日期格式化为指定格式的字符串
                String formattedDate = empAllDTO.getHireDate() != null ? sdf.format(empAllDTO.getHireDate()) : "";
                cell.setCellValue(formattedDate);
                cell.setCellStyle(cellStyle);

                cell = hssfRow.createCell(7);
                cell.setCellValue(empAllDTO.getMgr() == null ? "" : Integer.toString(empAllDTO.getMgr()));
                cell.setCellStyle(cellStyle);

                cell = hssfRow.createCell(8);
                cell.setCellValue(empAllDTO.getDeptno());
                cell.setCellStyle(cellStyle);

                // 处理图片
                String photoUrl = empAllDTO.getPhoto();
                if (photoUrl != null && !photoUrl.isEmpty()) {
                    URL url = new URL(photoUrl);
                    InputStream is = url.openStream();
                    byte[] bytes = IOUtils.toByteArray(is);
                    is.close();

                    int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG); // 图片类型为JPEG
                    CreationHelper helper = workbook.getCreationHelper();
                    Drawing<?> drawing = sheet.createDrawingPatriarch();
                    ClientAnchor anchor = helper.createClientAnchor();
                    anchor.setCol1(9); // 设置图片的起始列
                    anchor.setRow1(hssfRow.getRowNum()); // 设置图片的起始行
                    Picture pict = drawing.createPicture(anchor, pictureIdx);
                    pict.resize(0.5, 1); // 调整图片大小
                } else {
                    // 如果没有图片，则在单元格中留空
                    cell = hssfRow.createCell(9);
                    cell.setCellValue("");
                }

                cell = hssfRow.createCell(10);
                cell.setCellValue(empAllDTO.getDescription());
                cell.setCellStyle(cellStyle);
            }

            OutputStream outputStream = new FileOutputStream("D:/员工表.xls");
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
