package com.fovsoft.gn.entity.holder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Data
public class Menu {
    private int id;
    private int pid;
    private String mc;
//    private String url;

    // Transient 忽略该属性的ORM映射

    @Transient
    private Boolean checked;
    @Transient
    private Boolean open;

    @Transient
    private List<Menu> children;

}
