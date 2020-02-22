package com.zhs.controller;

import com.zhs.entity.PerformProgram;
import com.zhs.service.PerformProgramService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 演员-节目关系表(PerformProgram)表控制层
 *
 * @author makejava
 * @since 2020-02-16 14:22:07
 */
@RestController
@RequestMapping("performProgram")
public class PerformProgramController {
    /**
     * 服务对象
     */
    @Resource
    private PerformProgramService performProgramService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public PerformProgram selectOne(Integer id) {
        return this.performProgramService.queryById(id);
    }

    @RequestMapping("add")
    public void add(PerformProgram performProgram){
        performProgramService.insert(performProgram);
    }

    @RequestMapping("deleteByProIdAndPerId")
    public void deleteByProIdAndPerId(Integer proId,Integer perId){
        performProgramService.deleteByProIdAndPerId(proId,perId);
    }
}