package com.zhs.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 演员-节目关系表(PerformProgram)实体类
 *
 * @author makejava
 * @since 2020-02-16 14:22:07
 */
public class PerformProgram implements Serializable {
    private static final long serialVersionUID = -79045966729445884L;
    
    private Integer id;
    
    private Integer performId;
    
    private Integer programId;
    
    private Date createTime;
    
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerformId() {
        return performId;
    }

    public void setPerformId(Integer performId) {
        this.performId = performId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}