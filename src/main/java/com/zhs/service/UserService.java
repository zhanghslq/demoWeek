package com.zhs.service;

import com.zhs.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-02-16 14:22:44
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    /**
     * 注册用户，需要对密码进行加盐
     * @param user
     */
    Boolean register(User user);

    /**
     * @param user
     */
    Boolean login(User user);

    /**
     * 查询用户总条数
     * @return
     */
    Integer queryAllCount(User user);

    /**
     * 修改密码
     * @param user
     */
    void updatePassword(User user);

    void deleteByIds(List<Integer> ids);
}