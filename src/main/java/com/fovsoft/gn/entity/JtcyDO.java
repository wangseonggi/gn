package com.fovsoft.gn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String zxsqk;
    private String sxhcxyy;
    private String jkzk;
    private String ldjn;
    private String sfhjpth;
    private String sfcjcxjmjbytlbx;
    private String sfcjsybcylbx;
    private String sfxsncjmzjshbz;
    private String sfcjcxjmybyanglbx;
    private String sfxsrsywbxbt;
    private String lxdh;
    private String bz;
    private String scbz;
    private String shzt;
    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date sgny;
    private String rklx;
    private String zdqk;
    private String zxjdxx;
    private String cjzh;
    private String cjdj;
    private String djbhdmxbk;
    private String mxbsm;
    private String dbjz;
    private String sfxszcdd;
    private String jyyy;
    private String jyqk;
    private String jycydw;
    private String wdjycydy;
    private String ylbxlx;
}
