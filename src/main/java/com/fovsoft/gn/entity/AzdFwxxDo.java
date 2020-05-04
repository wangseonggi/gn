package com.fovsoft.gn.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 安置点详情
 */
@Data
@Entity
public class AzdFwxxDo {
    @Id
    private int id;
    private int aid;
    private String ld;
    private String dy;
    private String fh;
    private double mj;
    private String hx;
    private String mc;
}
