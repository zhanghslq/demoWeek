package com.zhs.controller;

import com.zhs.entity.User;
import com.zhs.service.UserService;
import com.zhs.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-02-16 14:22:44
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {

        return this.userService.queryById(id);
    }

    @PostMapping("login")
    public Boolean login(User user){
        log.info("有调用登陆{}，",user.getUsername());
        return userService.login(user);
    }
    @PostMapping("register")
    public Boolean register(User user){
        return userService.register(user);
    }
    @PostMapping("queryAllByLimit")
    public Result<List<User>> queryAllByLimit(int page,int limit,User user){
        int start = (page-1)*limit;
        List<User> users = userService.queryAllByLimit(start, limit);
        Integer count = userService.queryAllCount(user);
        return Result.success(count,users);
    }
    @PostMapping("delete")
    public void delete(Integer id){
        userService.deleteById(id);
    }
    @PostMapping("updatePassword")
    public void updatePassword(User user){
        userService.updatePassword(user);
    }
    @PostMapping("deleteByIds")
    public void deleteByIds(@RequestParam(value = "ids[]") List<Integer> ids){
        userService.deleteByIds(ids);
    }
}