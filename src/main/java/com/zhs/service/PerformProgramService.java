package com.zhs.service;

import com.zhs.entity.PerformProgram;
import com.zhs.entity.Performer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 演员-节目关系表(PerformProgram)表服务接口
 *
 * @author makejava
 * @since 2020-02-16 14:22:07
 */
public interface PerformProgramService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PerformProgram queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PerformProgram> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param performProgram 实例对象
     * @return 实例对象
     */
    PerformProgram insert(PerformProgram performProgram);

    /**
     * 修改数据
     *
     * @param performProgram 实例对象
     * @return 实例对象
     */
    PerformProgram update(PerformProgram performProgram);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据节目查询演员
     * @param proId  节目id
     * @return
     */
    List<Performer> selectAllByProgramId(Integer proId,int offset,int limit);

    /**
     * @param proId 节目id
     * @return
     */
    Integer selectCountByProgramId(Integer proId);

    /**
     * 解除关系
     * @param proId 节目id
     * @param perId 演员id
     */
    void deleteByProIdAndPerId(Integer proId,Integer perId);
}