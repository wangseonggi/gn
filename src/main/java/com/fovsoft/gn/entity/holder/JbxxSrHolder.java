package com.fovsoft.gn.entity.holder;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 基本信息及收入的数据对象
 */
@Data
public class JbxxSrHolder {

    @Column(name = "memberid")
    private int id;
    private String pkhsx;   // 贫困户属性
    private String hzxm;    // 户主姓名
    private String zjhm;    // 证件号码
    private String nf;      // 收入登记表所属年份，半角逗号分隔
}
