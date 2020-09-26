package com.fovsoft.gn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 影像资料
 */
@Data
public class YmYXZLDo implements Serializable {

    @Id
    private Integer id;
    @JsonIgnore
    private Integer fid;
    @JsonIgnore
    private String type;
    private String url;
    @JsonIgnore
    private Date lrrq;
}
