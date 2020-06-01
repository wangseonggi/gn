package com.fovsoft.gn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

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
    private String sfjrnmzyhzs;    // 是否加入农民专业合作社
    private String sfyltqybz;      // 是否有龙头企业带动
    private String sfycyzfdtrdd;   // 是否有创业致富带头人带动
    private String sftshyd;        // 是否通生活用电
    private String sftgbds;        // 是否通广播电视
    private String sfjjaqyys;      // 是否解决安全饮用水
    private String sfywscs;        // 是否有卫生厕所
    private double yczgljl;        // 与村主干路距离（公里）
    private String rhllx;          // 入户路类型
    private double zfmj;           // 住房面积（平方米）
    private String sfwf;           // 是否危房
    private String wfjb;           // 危房等级
    private String wgnd;           // 危改年度
    private String zyrllx;         // 主要燃料类型
    private String jtrlmc;         // 具体燃料名称
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lrrq;           // 录入日期
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gxrq;           // 更新日期
    private String scbz;           // 删除标志
}
