package com.fovsoft.gn.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 行政区划DO
 */
@Data
@Entity
public class XzqhDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String xzqhdm;
    private String xzqhmz;
    private String xxxzqhmz;
    private String sjxzqhdm;
    private String xzqhcj;
    private String fzjg;
    private String xzqhlb;
    private String yzbm;
    private Date cjsj;
    private Date gxsj;
}
