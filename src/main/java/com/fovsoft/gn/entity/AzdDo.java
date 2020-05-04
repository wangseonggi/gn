package com.fovsoft.gn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class AzdDo {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String mc;
    private String jc;
    private String dz;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lrrq;
}
