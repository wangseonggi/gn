package com.fovsoft.gn.security.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    private int id;
    private String username;
    private String password;
    private String nc;
    private int xb;
    private String dh;
    private String dzyx;
    private Date zhyxq;
    private Date mmyxq;
    private int zt;
    private String zjdlip;
}
