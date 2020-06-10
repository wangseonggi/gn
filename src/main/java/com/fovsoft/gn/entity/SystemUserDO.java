package com.fovsoft.gn.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class SystemUserDO {
    @Id
    private int id;
    private String username;
    private String password;
    private String nc;
    private String xb;
    private String dh;
    private String dzyx;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date zhyxq;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date mmyxq;
    private int zt;
    private String zjdlip;
}
