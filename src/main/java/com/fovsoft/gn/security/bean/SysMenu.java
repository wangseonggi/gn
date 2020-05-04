package com.fovsoft.gn.security.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SysMenu {
    private int id;
    private int pid;
    private String mc;
    private String url;
    private int px;
    private int zt;
    private Date cjsj;
    private Date gxsj;
}
