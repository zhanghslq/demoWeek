package com.zhs.service.impl;

import com.zhs.entity.Hoster;
import com.zhs.dao.HosterDao;
import com.zhs.service.HosterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 主持人信息表(Hoster)表服务实现类
 *
 * @author makejava
 * @since 2020-02-16 14:21:20
 */
@Service("hosterService")
public class HosterServiceImpl implements HosterService {
    @Resource
    private HosterDao hosterDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Hoster queryById(Integer id) {
        return this.hosterDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Hoster> queryAllByLimit(int offset, int limit) {
        return this.hosterDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hoster 实例对象
     * @return 实例对象
     */
    @Override
    public Hoster insert(Hoster hoster) {
        this.hosterDao.insert(hoster);
        return hoster;
    }

    /**
     * 修改数据
     *
     * @param hoster 实例对象
     * @return 实例对象
     */
    @Override
    public Hoster update(Hoster hoster) {
        this.hosterDao.update(hoster);
        return this.queryById(hoster.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.hosterDao.deleteById(id) > 0;
    }

    @Override
    public Integer count(Hoster hoster) {
        return hosterDao.count(hoster);
    }

    @Override
    public void batchInsert(List<Hoster> readBooks) {
        hosterDao.batchInsert(readBooks);
    }
}