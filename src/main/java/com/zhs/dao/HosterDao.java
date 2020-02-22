package com.zhs.dao;

import com.zhs.entity.Hoster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 主持人信息表(Hoster)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-16 14:21:19
 */
public interface HosterDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Hoster queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Hoster> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param hoster 实例对象
     * @return 对象列表
     */
    List<Hoster> queryAll(Hoster hoster);

    /**
     * 新增数据
     *
     * @param hoster 实例对象
     * @return 影响行数
     */
    int insert(Hoster hoster);

    /**
     * 修改数据
     *
     * @param hoster 实例对象
     * @return 影响行数
     */
    int update(Hoster hoster);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    /**
     * @param hoster 条件
     * @return 查询总条数
     */
    Integer count(Hoster hoster);

    void batchInsert(@Param("hosters") List<Hoster> hosters);
}