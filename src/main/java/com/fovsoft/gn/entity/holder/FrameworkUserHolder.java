package com.fovsoft.gn.entity.holder;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FrameworkUserHolder {
    private int id;
    private String username;
    private String nickname;
    private String tel;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date zhyxq;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date mmyxq;
    private int zt;
}
