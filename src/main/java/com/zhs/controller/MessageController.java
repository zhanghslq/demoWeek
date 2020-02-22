package com.zhs.controller;

import com.zhs.entity.Message;
import com.zhs.entity.PerformProgram;
import com.zhs.entity.Performer;
import com.zhs.service.MessageService;
import com.zhs.service.PerformProgramService;
import com.zhs.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 节目信息表(Message)表控制层
 *
 * @author makejava
 * @since 2020-02-16 14:31:34
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;
    @Resource
    private PerformProgramService performProgramService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Message selectOne(Integer id) {

        return this.messageService.queryById(id);
    }

    @RequestMapping("delete")
    public void delete(Integer id){
        messageService.deleteById(id);

    }
    @RequestMapping("update")
    public void update(Message message){
        messageService.update(message);
    }
    @RequestMapping("addPerformer")
    public void addPerformer(PerformProgram performProgram){
        //添加演员节目关系
        performProgramService.insert(performProgram);
    }

    @RequestMapping("queryAllByLimit")
    public Result<List<Message>> queryAllByLimit(int page,int limit,Message message){
        //这里的查询所有需要把节目关联的演员也查出来
        int start = (page-1)*limit;
        //根据条件查询指定的数据看是否需要
        List<Message> messages = messageService.queryAllByLimit(start, limit);
        for (Message message1 : messages) {
            message1.setPerformerNumber(message1.getPerformers().size());
        }
        Integer count = messageService.queryAllCount(message);
        return Result.success(count,messages);
    }
    @RequestMapping("add")
    public void add(Message message){
        messageService.insert(message);
    }

    @RequestMapping("queryPerformer")
    public Result<List<Performer>> queryPerformer(Integer messageId,int page,int limit){
        int start =(page-1)*limit;
        List<Performer> performers = performProgramService.selectAllByProgramId(messageId,start,limit);
        Integer count = performProgramService.selectCountByProgramId(messageId);
        return Result.success(count,performers);
    }
}