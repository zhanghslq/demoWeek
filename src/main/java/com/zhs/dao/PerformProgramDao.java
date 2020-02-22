package com.zhs.dao;

import com.zhs.entity.Message;
import com.zhs.entity.PerformProgram;
import com.zhs.entity.Performer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 演员-节目关系表(PerformProgram)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-16 14:22:07
 */
public interface PerformProgramDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PerformProgram queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PerformProgram> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param performProgram 实例对象
     * @return 对象列表
     */
    List<PerformProgram> queryAll(PerformProgram performProgram);

    /**
     * 新增数据
     *
     * @param performProgram 实例对象
     * @return 影响行数
     */
    int insert(PerformProgram performProgram);

    /**
     * 修改数据
     *
     * @param performProgram 实例对象
     * @return 影响行数
     */
    int update(PerformProgram performProgram);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据演员id删除响应的节目-演员关系表
     *
     * @param perId 演员id
     */
    void deleteByPerformId(@Param("perId") Integer perId);

    /**
     * 根据节目删除响应的节目-演员关系表的数据
     *
     * @param proId 节目id
     */
    void deleteByProgramId(@Param("proId") Integer proId);


    /**
     * 根据演员id查询到他对应的节目列表
     *
     * @param perId 演员id
     * @return 对应的节目列表
     */
    List<Message> selectAllByPerformId(@Param("perId") Integer perId);

    /**
     * 根据节目id，查询到对应的演员
     *
     * @param proId 节目id
     * @return 演员列表
     */
    List<Performer> selectAllByProgramId(@Param("proId") Integer proId, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * @param proId
     * @return
     */
    List<Performer> selectAllByProgramIdNoLimit(@Param("proId") Integer proId);

    /**
     * 根据节目id查询演员个数
     *
     * @param proId 节目id
     * @return
     */
    Integer selectCountByProgramId(Integer proId);

    PerformProgram selectByPerIdAndProId(@Param("perId") Integer perId, @Param("proId") Integer proId);

    void deleteByProIdAndPerId(@Param("proId") Integer proId, @Param("perId") Integer perId);
}