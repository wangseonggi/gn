package com.fovsoft.gn.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class SystemRoleDO {
    @Id
    private int id;
    private String mc;
    private String sm;
    private String bz;
    private int zt;
    private Date cjsj;
    private Date gxsj;
}
