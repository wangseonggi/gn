package com.fovsoft.gn.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 家庭成员实体类do
 */
@Data
@Entity
public class JtcyDO implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @JsonProperty(value = "memberid")
//    private int id;
//    private int fid;                    //贫困户基本信息表id
//    private String xm;                  // 姓名
//    private int xb;                     // 性别1男0女
//    private String zjhm;                // 证件号码
//    private int yhzgx;                  // 与户主关系
//    private int mz;                     // 民族
//    private int zzmm;                   // 政治面貌
//    private int whcd;                   // 文化程度
//    private int zxszk;                  // 在校生状况
//    private int sxyy;                   // 失学原因
//    private int jkzk;                   // 健康状况
//    private int ldjn;                   // 劳动技能
//    private int sfhjpth;                // 是否会讲普通话
//    private int sfcjcxjmjbytlbx;        // 是否参加城乡居民基本医疗保险
//    private int sfcjsybcylbx;           // 是否参加商业补充医疗保险
//    private int sfxsncjmzjshbz;         // 是否享受农村居民最低生活保障
//    private int sfcjcxjmybyanglbx;      // 是否参加城乡居民基本养老保险
//    private int sfxsrsywbxbt;           // 是否享受人身意外保险补贴
//    private String lxdh;                // 联系电话
//    private String bz;                  // 备注
//    private int scbz;                   // 删除标志


    @Id
    @JsonProperty(value = "memberid")
    private int id;
    private int fid;
    private String xm;
    private String xb;
    private String zjhm;
    private String yhzgx;
    private String mz;
    private String zzmm;
    private String whcd;
    private int zxszk;
    private int sxyy;
    private String jkzk;
    private int ldjn;
    private int sfhjpth;
    private int sfcjcxjmjbytlbx;
    private int sfcjsybcylbx;
    private int sfxsncjmzjshbz;
    private int sfcjcxjmybyanglbx;
    private int sfxsrsywbxbt;
    private String lxdh;
    private String bz;
    private int scbz;
    private String shzt;
    private Date sgny;
    private int rklx;
    private int zdqk;
    private String zxjdxx;
    private String cjzh;
    private int cjdj;
    private String djbhdmxbk;
    private String mxbsm;
    private String dbjz;
    private String sfxszcdd;
    private String jyyy;
    private String jyqk;
    private String jycydw;
    private int wdjycydy;
    private String ylbxlx;
}
