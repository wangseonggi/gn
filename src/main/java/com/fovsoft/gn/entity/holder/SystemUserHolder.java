package com.fovsoft.gn.entity.holder;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class SystemUserHolder {
    @Id
    private int id;
    private int aid;
    private String username;
    private String password;
    private String opassword;
    private String nc;
    private String dh;
    private Integer zt;
    private String dzyx;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date zhyxq;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date mmyxq;
}
