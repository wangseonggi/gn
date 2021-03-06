package com.fovsoft.gn.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 生产经营性收支
 */
@Data
@Entity
public class Sr1DO {

    @Id
    private int id;
    private int fid;
    private String lb1;
    private String lb2;
    private String lb3;
    private String nf;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String item6;
    private String item7;
    private String item8;
    private String item9;
    private String item10;
    private String item11;
    private String item12;
}
