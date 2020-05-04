package com.fovsoft.gn.mapper.srdjb;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonMapper {
    int add(@Param(value = "param") List param);

    int delete(@Param("fid") int fid, @Param("nf") String nf);
}
