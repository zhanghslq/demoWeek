package com.zhs.service;

import com.zhs.entity.Hoster;
import java.util.List;

/**
 * 主持人信息表(Hoster)表服务接口
 *
 * @author makejava
 * @since 2020-02-16 14:21:19
 */
public interface HosterService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Hoster queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Hoster> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param hoster 实例对象
     * @return 实例对象
     */
    Hoster insert(Hoster hoster);

    /**
     * 修改数据
     *
     * @param hoster 实例对象
     * @return 实例对象
     */
    Hoster update(Hoster hoster);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Integer count(Hoster hoster);

    /**
     * 批量添加
     * @param readBooks
     */
    void batchInsert(List<Hoster> readBooks);
}