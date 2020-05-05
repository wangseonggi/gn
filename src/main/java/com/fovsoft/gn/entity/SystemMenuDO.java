package com.fovsoft.gn.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class SystemMenuDO {
    @Id
    private int id;
    private int pid;
    private String mc;
    private String url;
    private String px;
    private int zt;
    private Date cjsj;
    private Date gxsj;
}
