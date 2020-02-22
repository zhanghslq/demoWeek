package com.zhs.service.impl;

import com.zhs.entity.PerformProgram;
import com.zhs.dao.PerformProgramDao;
import com.zhs.entity.Performer;
import com.zhs.service.PerformProgramService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 演员-节目关系表(PerformProgram)表服务实现类
 *
 * @author makejava
 * @since 2020-02-16 14:22:07
 */
@Service("performProgramService")
public class PerformProgramServiceImpl implements PerformProgramService {
    @Resource
    private PerformProgramDao performProgramDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PerformProgram queryById(Integer id) {
        return this.performProgramDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<PerformProgram> queryAllByLimit(int offset, int limit) {
        return this.performProgramDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param performProgram 实例对象
     * @return 实例对象
     */
    @Override
    public PerformProgram insert(PerformProgram performProgram) {
        PerformProgram performProgram1 = performProgramDao.selectByPerIdAndProId(performProgram.getPerformId(), performProgram.getProgramId());
        if(!Objects.isNull(performProgram1)){
            return performProgram1;
        }
        this.performProgramDao.insert(performProgram);
        return performProgram;
    }

    /**
     * 修改数据
     *
     * @param performProgram 实例对象
     * @return 实例对象
     */
    @Override
    public PerformProgram update(PerformProgram performProgram) {
        this.performProgramDao.update(performProgram);
        return this.queryById(performProgram.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.performProgramDao.deleteById(id) > 0;
    }

    @Override
    public List<Performer> selectAllByProgramId(Integer proId,  int offset, int limit) {
        return performProgramDao.selectAllByProgramId(proId,offset,limit);
    }

    @Override
    public Integer selectCountByProgramId(Integer proId) {
        return performProgramDao.selectCountByProgramId(proId);
    }

    @Override
    public void deleteByProIdAndPerId(Integer proId, Integer perId) {
        performProgramDao.deleteByProIdAndPerId(proId,perId);
    }
}