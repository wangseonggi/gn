package com.fovsoft.gn.entity.holder;

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
    private String fpnd;
    private int sfydbqh;
    private String pkhsx;
    private Integer jhtpnd;
    private String hzxm;
    private String zjhm;
}
