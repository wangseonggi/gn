package com.fovsoft.gn.entity.holder;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 安置点户主
 */
@Data
@Entity
public class AzdFwxxHzHolder {
    @Id
    private int id;
    private int aid;
    private String ld;
    private String dy;
    private String fh;
    private double mj;
    private String hx;
    private String mc;
    private String xm;
    private String zjhm;
}
