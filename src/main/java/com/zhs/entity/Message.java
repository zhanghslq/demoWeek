package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 节目信息表(Message)实体类
 *
 * @author makejava
 * @since 2020-02-16 14:31:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private static final long serialVersionUID = 982666221388107625L;
    
    private Integer id;
    /**
    * 节目名称
    */
    private String name;
    /**
    * 节目顺序
    */
    private Integer seqNumber;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;

    /**
     * 节目对应的演员列表
     */
    private List<Performer> performers = new ArrayList<>();
    /**
     * 演员个数
     */
    private Integer performerNumber;



}