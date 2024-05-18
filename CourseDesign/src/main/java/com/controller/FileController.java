package com.controller;

import com.dto.EmpAllDTO;
import com.service.EmpService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @Author: xuzifan
 * @Date: 2024/5/7 - 05 - 07 - 18:31
 * @Description: com.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/root")
public class FileController {
    private static final Logger logger = Logger.getLogger(FileController.class.getName());
    //文件存储位置
    private String filesServer="http://保密:8090/upload/";

    @Autowired
    private EmpService empService;

    /**
     * @param avatar 必须与前端FormData对象中的文件名称一致
     * @return
     */
    @ResponseBody
    @RequestMapping("fileUpload.do")
    public Map<String,String> fileUpload(MultipartFile avatar, HttpServletRequest req){
        Map<String,String> map=new HashMap<>();

        //控制文件大小（推荐）
        if(avatar.getSize()>10*1024*1024){
            map.put("alert","文件大小不能超过10MB");
            return map;
        }


        //获取文件名
        String originalFilename = avatar.getOriginalFilename();
        //避免文件名冲突，使用UUID替换文件名
        String uuid = UUID.randomUUID().toString();
        //获取拓展名
        String extendsName = originalFilename.substring(originalFilename.lastIndexOf("."));
        if(!extendsName.equals(".jpg")&&!extendsName.equals(".png")){
            map.put("alert","文件类型必须为图片");
            return map;
        }

        //拼接拓展名得到新文件名
        String newFileName=uuid.concat(extendsName);

        //创建sun公司提供的jersey包中的client对象
        Client client=Client.create();
        WebResource resource = client.resource(filesServer + newFileName);

        //文件保存到另一个服务器
        try {
            resource.put(String.class,avatar.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            map.put("warn","上传失败");
            logger.warning("上传识别");
            return map;
        }



        map.put("message","上传成功");
        //为了文件回显和下载，需要把文件新名字和类型返回给浏览器
        map.put("newFileName",filesServer+newFileName);
        map.put("fileType",avatar.getContentType());
        return map;
    }



    @RequestMapping("fileDownload.do")
    public void fileDownload(String photo, String fileType, HttpServletResponse resp) throws IOException {
        String s = photo.substring(photo.lastIndexOf("/") + 1);
        //设置响应头
        //告诉浏览器要将数据保存到磁盘上，不在浏览器上直接解析
        resp.setHeader("Content-Disposition","attachment;filename="+s);
        //告诉下载的文件类型
        resp.setContentType(fileType);
        //获取文件输入流
        InputStream inputStream = new URL(photo).openStream();
        //获取一个指向浏览器的输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        //向浏览器响应文件
        IOUtils.copy(inputStream,outputStream);
    }

    /**
     * 删除/upload/中名为name的文件
     * @param name 公网ip:端口号/位置/文件名
     */
    @RequestMapping("fileDelete.do")
    public void fileDelete(String name){
        logger.info(name);
        Client client = Client.create();
        WebResource webResource = client.resource(name);
        ClientResponse response = webResource.delete(ClientResponse.class);

        if (response.getStatus() == 204) {
            logger.info("文件删除成功");
        } else {
            logger.warning("文件删除失败，状态码：" + response.getStatus());
        }
    }


    /**
     * 导出员工的详细信息
     * @throws IOException
     */
    @RequestMapping("createExcel.action")
    public void createExcel(HttpServletResponse resp) throws IOException {
        System.out.println("执行");
        List<EmpAllDTO> list = empService.getDocument();

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


            ServletOutputStream outputStream = resp.getOutputStream();
            String filename = "员工表.xls";
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1"); // 编码文件名
            resp.setHeader("Content-Disposition", "attachment;filename=" + filename);
            resp.setContentType("application/vnd.ms-excel"); // 设置文件类型为Excel
            // 将Excel文件写入到输出流中
            workbook.write(outputStream);
            outputStream.close();
            logger.info("文件导出成功");
        } catch (IOException e) {
            logger.warning("文件导出失败");
            e.printStackTrace();
        }

    }
}
