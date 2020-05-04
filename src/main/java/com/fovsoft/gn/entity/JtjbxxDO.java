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
    private int pkhsx;                  // 贫困户属性
    private String jhtpnd;                 // 计划脱贫年度
    private String fpnd;                   // 返贫年度
    private String fpyy;                // 返贫原因
    private int sfjls;                  // 是否军烈属,1是0否
    private int sfbqh;                  // 是否搬迁户,1是0否
    private String bqfs;                // 搬迁方式
    private String bqdz;                // 搬迁地址
    private Date lrrq;                  // 录入日期
    private Date xgrq;                  // 修改日期
    private int lrrdm;                  // 录入人代码id
    private int scbz;                   // 删除标志

    private String azd;
    private String ldfh;
    private String ndbqrw;
    private String qcdxxdz;
    private String qcdlx;
    private int jtsyrk;
    private double zfmj;
    private double zczj;

    @JsonFormat(pattern="yyyy.MM",timezone = "GMT+8")
    private Date bqrzsj;
    @JsonFormat(pattern="yyyy.MM",timezone = "GMT+8")
    private Date sjrzsj;
    private String yyzf;
    private double yyzfmj;
    private String ccjf;
    private int bhzfzl;
    @JsonFormat(pattern="yyyy.MM",timezone = "GMT+8")
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
