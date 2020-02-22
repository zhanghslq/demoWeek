package com.zhs.service;

import com.zhs.entity.Performer;
import java.util.List;

/**
 * 演员信息表(Performer)表服务接口
 *
 * @author makejava
 * @since 2020-02-16 14:22:17
 */
public interface PerformerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Performer queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Performer> queryAllByLimit(int offset, int limit,Performer performer);

    /**
     * 新增数据
     *
     * @param performer 实例对象
     * @return 实例对象
     */
    Performer insert(Performer performer);

    /**
     * 修改数据
     *
     * @param performer 实例对象
     * @return 实例对象
     */
    Performer update(Performer performer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @param performer 条件
     * @return 总数
     */
    Integer queryAllCount(Performer performer);

    /**
     * 批量添加演员
     * @param performers 演员
     */
    void batchInsert(List<Performer> performers);

}