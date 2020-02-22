package com.zhs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 演员信息表(Performer)实体类
 *
 * @author makejava
 * @since 2020-02-16 14:22:17
 */
@ToString
public class Performer implements Serializable {
    private static final long serialVersionUID = 110188824118072887L;
    
    private Integer id;
    @ExcelProperty(index = 0)
    private String name;
    @ExcelProperty(index = 1)
    private String mobile;
    
    private Date createTime;
    
    private Date updateTime;

    private List<Message> messageList;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

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