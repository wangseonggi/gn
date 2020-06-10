package com.fovsoft.gn.entity.holder;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 基本信息及户主的数据对象
 */
@Data
@Entity
public class JbxxHzHolder {

    @Id
    @Column(name = "memberid")
    private int id;
    private String pkhsx;   // 贫困户属性
    private String hzxm;    // 户主姓名
    private String zjhm;    // 证件号码
    private String tpqk;    // 脱贫情况
    private String ndbqrw;  // 年度搬迁任务
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String sjrzsj;  // 实际入住时间
    private Double zfmj;    // 住房面积
    private String syrk;    // 收益人口
}
