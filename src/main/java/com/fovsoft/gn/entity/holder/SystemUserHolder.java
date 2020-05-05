package com.fovsoft.gn.entity.holder;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SystemUserHolder {
    @Id
    private int id;
    private String username;
    private String password;
    private String opassword;
    private String nc;
    private String dh;
    private String dzyx;
}
