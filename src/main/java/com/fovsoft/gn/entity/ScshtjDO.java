package com.fovsoft.gn.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 生产生活条件DO
 */
@Data
@Entity
public class ScshtjDO {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty(value = "conditionid")
    private int id;
    private int fid;            // 贫困户基本信息表id
    private double gdmj;           // 耕地面积（亩）
    private double ldmj;           // 林地面积（亩）
    private double tghlmj;         // 退耕还林面积（亩）
    private double lgmj;           // 林果面积（亩）
    private double mcdmj;          // 牧草地面积（亩)
    private double smmj;           // 水面面积（亩）
    private int sfjrnmzyhzs;    // 是否加入农民专业合作社
    private int sfyltqybz;      // 是否有龙头企业带动
    private int sfycyzfdtrdd;   // 是否有创业致富带头人带动
    private int sftshyd;        // 是否通生活用电
    private int sftgbds;        // 是否通广播电视
    private int sfjjaqyys;      // 是否解决安全饮用水
    private int sfywscs;        // 是否有卫生厕所
    private double yczgljl;        // 与村主干路距离（公里）
    private int rhllx;          // 入户路类型
    private double zfmj;           // 住房面积（平方米）
    private int sfwf;           // 是否危房
    private int wfdj;           // 危房等级
    private int wgnd;           // 危改年度
    private int zyrllx;         // 主要燃料类型
    private String jtrlmc;         // 具体燃料名称
    private int lrrq;           // 录入日期
    private int gxrq;           // 更新日期
    private int scbz;           // 删除标志
}
