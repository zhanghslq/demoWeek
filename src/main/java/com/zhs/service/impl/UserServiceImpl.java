package com.zhs.service.impl;

import com.zhs.entity.User;
import com.zhs.dao.UserDao;
import com.zhs.service.UserService;
import com.zhs.util.Bizfiz;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-02-16 14:22:44
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        log.info("收到查询");
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public Boolean register(User user) {
        User byUserName = userDao.queryByUserName(user.getUsername());
        if(!Objects.isNull(byUserName)){
            return false;
        }
        String password = user.getPassword();
        String salt = RandomStringUtils.randomAlphanumeric(6);
        String newPassword = DigestUtils.md5Hex(StringUtils.trimToEmpty(password) + salt);
        user.setSalt(salt);
        user.setPassword(newPassword);
        insert(user);
        return true;
    }

    @Override
    public Boolean login(User user) {
        User user1 = userDao.queryByUserName(user.getUsername());
        if(Objects.isNull(user1)){
            return false;
        }
        String md5Hex = DigestUtils.md5Hex(user.getPassword() + user1.getSalt());
        return md5Hex.equals(user1.getPassword());
    }

    @Override
    public Integer queryAllCount(User user) {
        return userDao.queryAllCount(user);
    }

    @Override
    public void updatePassword(User user) {
        User user1 = userDao.queryById(user.getId());
        String salt = user1.getSalt();
        String md5Hex = DigestUtils.md5Hex(user.getPassword() + salt);
        user1.setPassword(md5Hex);
        userDao.update(user1);

    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        userDao.deleteByIds(ids);
    }
}