package com.zhs.controller;

import com.zhs.entity.Performer;
import com.zhs.service.PerformProgramService;
import com.zhs.service.PerformerService;
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
 * 演员信息表(Performer)表控制层
 *
 * @author makejava
 * @since 2020-02-16 14:22:17
 */
@Slf4j
@RestController
@RequestMapping("performer")
public class PerformerController {
    /**
     * 服务对象
     */
    @Resource
    private PerformerService performerService;
    @Resource
    private PerformProgramService performProgramService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Performer selectOne(Integer id) {

        return this.performerService.queryById(id);
    }
    @RequestMapping("queryAllByLimit")
    public Result<List<Performer>> queryAllByLimit(Performer performer,int page,int limit){
        int start = (page-1)*limit;
        List<Performer> performers = performerService.queryAllByLimit(start, limit,performer);
        //暂时没用到条件
        Integer count = performerService.queryAllCount(performer);
        return Result.success(count,performers);
    }
    @RequestMapping("update")
    public void update(Performer performer){
        performerService.update(performer);
    }
    @RequestMapping("delete")
    public void delete(Integer id){
        performerService.deleteById(id);
    }
    @RequestMapping("deleteByIds")
    public void deleteByIds(@RequestParam(value = "ids[]")List<Integer> ids){
        for (Integer id : ids) {
            performerService.deleteById(id);
        }
    }

    @RequestMapping("add")
    public void add(Performer performer){
        performerService.insert(performer);
    }
    @RequestMapping("getTemplate")
    public void getTemplate(HttpServletResponse response){
        try {
            InputStream fis = new BufferedInputStream(new FileInputStream("演员批量导入模板.xlsx"));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 设置response的Header
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String("演员批量导入模板".getBytes(StandardCharsets.UTF_8), "iso8859-1")
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
        // 获取文件名
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
        List<Performer> readBooks = EasyExcelUtil.readListFrom(fileInputStream, Performer.class);
        excelFile.delete();
        performerService.batchInsert(readBooks);
        return Result.success("");
    }
    @RequestMapping("selectAllByProgramId")
    public Result<List<Performer>> selectAllByProgramId(Integer proId,int page,int limit){
        int start = (page-1)*limit;
        Integer count = performProgramService.selectCountByProgramId(proId);
        List<Performer> performers = performProgramService.selectAllByProgramId(proId, start, limit);
        return Result.success(count,performers);
    }
}