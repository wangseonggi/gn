package com.fovsoft.gn.entity.holder;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * fid和nf的数据对象
 */
@Data
public class FidNFHolder {
    private int fid;
    private String nf;

}
