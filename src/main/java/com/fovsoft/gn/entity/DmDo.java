package com.fovsoft.gn.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 代码
 * 返回各种代码的名称，编号等信息
 */
@Data
@Entity
public class DmDo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String name;
    private String dm;
}
