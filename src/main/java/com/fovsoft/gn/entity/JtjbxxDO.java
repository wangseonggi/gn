package com.fovsoft.gn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 家庭基本信息DO
 */
@Data
@Entity
public class JtjbxxDO {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty(value = "baseid")
    private int id;
    private String shi;                    // 市
    private String xian;                   // 县（市、区）
    private String zhen;                   // 镇（乡、街道）
    private String xzc;                 // 行政村
    private String zrct;                // 自然村（屯）
    private String lxdh;                // 联系电话
    private String khyh;                // 开户银行
    private String yhkh;                // 银行卡号
    private String pkhsx;                  // 贫困户属性
    private String jhtpnd;                 // 计划脱贫年度
    private String fpnd;                   // 返贫年度
    private String fpyy;                // 返贫原因
    private String sfjls;                  // 是否军烈属
    private String sfbqh;                  // 是否搬迁户
    private String bqfs;                // 搬迁方式
    private String bqdz;                // 搬迁地址
    private Date lrrq;                  // 录入日期
    private Date xgrq;                  // 修改日期
    private int lrrdm;                  // 录入人代码id
    private String scbz;                   // 删除标志

    private String azd;                 // 安置点
    private String ldfh;// 楼栋房号
    private String ndbqrw;  // 年度搬迁任务
    private String qcdxxdz; // 迁出地详细地址
    private String qcdlx;  // 迁出地类型
    private int jtsyrk;      // 家庭受益人口
    private double zfmj; // 住房面积㎡+
    private double zczj; // 自筹资金（元）+

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date bqrzsj;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date sjrzsj;
    private String yyzf;
    private double yyzfmj;
    private String ccjf;
    private String bhzfzl;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date ccsj;
    private double ccjfmj;
    private double dxcfjl;
    private String fkflqk;
    private String cyfcxm;
    private double nnhdcyfczj;
    private String tpqk;
    private String hkqy;
    private String fczblhq;
}
