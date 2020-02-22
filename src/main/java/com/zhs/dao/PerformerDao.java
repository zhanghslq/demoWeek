package com.zhs.dao;

import com.zhs.entity.Performer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 演员信息表(Performer)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-16 14:22:17
 */
public interface PerformerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Performer queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Performer> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit, @Param("performer") Performer performer);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param performer 实例对象
     * @return 对象列表
     */
    List<Performer> queryAll(Performer performer);

    /**
     * 新增数据
     *
     * @param performer 实例对象
     * @return 影响行数
     */
    int insert(Performer performer);

    /**
     * 修改数据
     *
     * @param performer 实例对象
     * @return 影响行数
     */
    int update(Performer performer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询总数
     *
     * @param performer
     * @return
     */
    Integer queryAllCount(Performer performer);

    /**
     * 批量添加
     *
     * @param performers 演员列表
     */
    void batchInsert(@Param("performers") List<Performer> performers);
}