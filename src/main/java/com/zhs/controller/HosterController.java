package com.zhs.controller;

import com.zhs.entity.Hoster;
import com.zhs.entity.Performer;
import com.zhs.service.HosterService;
import com.zhs.util.EasyExcelUtil;
import com.zhs.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 主持人信息表(Hoster)表控制层
 *
 * @author makejava
 * @since 2020-02-16 14:21:20
 */
@Slf4j
@RestController
@RequestMapping("hoster")
public class HosterController {
    /**
     * 服务对象
     */
    @Resource
    private HosterService hosterService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Hoster selectOne(Integer id) {
        return this.hosterService.queryById(id);
    }

    @RequestMapping("queryAll")
    public Result<List<Hoster>> queryAll(int page, int limit,Hoster hoster){
       int  start =  (page-1)*limit;
        List<Hoster> hosters = hosterService.queryAllByLimit(start, limit);
        Integer count = hosterService.count(hoster);
        return Result.success(count,hosters);
    }
    @RequestMapping("add")
    public void add(Hoster hoster){
        hosterService.insert(hoster);
    }
    @RequestMapping("delete")
    public void delete(Integer id){
        hosterService.deleteById(id);
    }
    @RequestMapping("update")
    public Result<Hoster> update(Hoster hoster){
        Hoster update = hosterService.update(hoster);
        return Result.success(update);
    }
    @RequestMapping("deleteByIds")
    public void deleteByIds(@RequestParam(value = "ids[]")List<Integer> ids){
        for (Integer id : ids) {
            hosterService.deleteById(id);
        }
    }
    @RequestMapping("getTemplate")
    public void getTemplate(HttpServletResponse response){
        try {
            InputStream fis = new BufferedInputStream(new FileInputStream("主持人批量导入模板.xlsx"));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 设置response的Header
            response.setCharacterEncoding("utf-8");

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String("主持人批量导入模板".getBytes(StandardCharsets.UTF_8), "iso8859-1")
                    + ".xlsx");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("下载失败");
        }
    }


    @RequestMapping("listUpload")
    public Result<String> listUpload(@RequestParam("file") MultipartFile file){
        //批量添加
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)){
            return Result.fail(500,"文件不能为空","");
        }
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        if (!prefix.toLowerCase().contains("xls") && !prefix.toLowerCase().contains("xlsx") ){
            return Result.fail(500,"文件格式异常，请上传Excel文件格式","");
        }
        // 防止生成的临时文件重复-建议使用UUID
        final File excelFile;
        try {
            excelFile = File.createTempFile(System.currentTimeMillis()+"", prefix);
            file.transferTo(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail(500,"文件上传失败","");
        }

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(excelFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Result.fail(500,"文件上传失败","");
        }
        List<Hoster> readBooks = EasyExcelUtil.readListFrom(fileInputStream, Hoster.class);
        excelFile.delete();
        hosterService.batchInsert(readBooks);
        return Result.success("");
    }
}