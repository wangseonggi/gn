package com.fovsoft.gn.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class BfrDo {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String xm;
    private String xb;
    private String sfzhm;
    private String gzdw;
    private String zw;
    private String lxdh;
    private Date lrrq;
    private Date bgrq;
    private int zt;
}
