package com.fovsoft.gn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 变更情况说明DO
 */
@Data
@Entity
public class BgqksmDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty(value = "changeid")
    private int id;
    private int fid;
    private String nr;
    private Date lrrq;
    private String scbz;
}
