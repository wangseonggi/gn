package com.fovsoft.gn.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 致贫原因实体类do
 */
@Data
@Entity
public class ZpyyDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty(value = "additionid")
    private int id;
    private int fid;                    //贫困户基本信息表id
    private int zpyy1;                     // 致贫原因1
    private int zpyy2;                     // 致贫原因2
    private int zpyy3;                     // 致贫原因3
    private int ncjtrks;                   // 年初家庭人口数
    private int nmjtrks;                   // 年末家庭人口数
    private int scbz;           // 删除标志

}
