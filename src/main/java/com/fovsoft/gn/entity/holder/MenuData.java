package com.fovsoft.gn.entity.holder;

import lombok.Data;


@Data
public class MenuData {
    private  int roleId;
    private int id;
    private boolean checked;
    private Integer[] ids;
}
