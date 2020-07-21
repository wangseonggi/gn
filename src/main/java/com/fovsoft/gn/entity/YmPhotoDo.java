package com.fovsoft.gn.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: by tpc
 * @date: 2020/7/17 19:54
 * @description: 家庭影像资料
 **/

@Data
public class YmPhotoDo implements Serializable {


    private Integer id    ;
    private Integer fid   ;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String photo5;
    private String photo6;
    private String gxsj  ;
}
