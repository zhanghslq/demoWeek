package com.zhs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhs.entity.Performer;
import com.zhs.dao.PerformerDao;
import com.zhs.service.PerformerService;
import com.zhs.util.Bizfiz;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 演员信息表(Performer)表服务实现类
 *
 * @author makejava
 * @since 2020-02-16 14:22:17
 */
@Service("performerService")
public class PerformerServiceImpl implements PerformerService {
    @Resource
    private PerformerDao performerDao;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Performer queryById(Integer id) {
        String key = String.format(Bizfiz.PERFORMER, id);
        String s = stringRedisTemplate.opsForValue().get(key);
        if(Objects.isNull(s)){
            Performer performer = this.performerDao.queryById(id);
            stringRedisTemplate.opsForValue().set(key,JSONObject.toJSONString(performer));
            return performer;
        }else {
            return JSONObject.parseObject(s, Performer.class);
        }
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Performer> queryAllByLimit(int offset, int limit,Performer performer) {
        return this.performerDao.queryAllByLimit(offset, limit,performer);
    }

    /**
     * 新增数据
     *
     * @param performer 实例对象
     * @return 实例对象
     */
    @Override
    public Performer insert(Performer performer) {
        this.performerDao.insert(performer);
        return performer;
    }

    /**
     * 修改数据
     *
     * @param performer 实例对象
     * @return 实例对象
     */
    @Override
    public Performer update(Performer performer) {
        this.performerDao.update(performer);
        String key = String.format(Bizfiz.PERFORMER,performer.getId());
        stringRedisTemplate.delete(key);
        return this.queryById(performer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        stringRedisTemplate.delete(String.format(Bizfiz.PERFORMER,id));
        return this.performerDao.deleteById(id) > 0;
    }

    @Override
    public Integer queryAllCount(Performer performer) {
        return performerDao.queryAllCount(performer);
    }

    @Override
    public void batchInsert(List<Performer> performers) {
        performerDao.batchInsert(performers);
    }
}