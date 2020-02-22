package com.zhs.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;
import java.io.Serializable;

/**
 * 主持人信息表(Hoster)实体类
 *
 * @author makejava
 * @since 2020-02-16 14:21:17
 */
public class Hoster implements Serializable {
    private static final long serialVersionUID = -17647336496490490L;
    
    private Integer id;
    /**
    * 姓名
    */
    @ExcelProperty(index = 0)
    private String name;
    /**
    * 手机号
    */
    @ExcelProperty(index = 1)
    private String mobile;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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