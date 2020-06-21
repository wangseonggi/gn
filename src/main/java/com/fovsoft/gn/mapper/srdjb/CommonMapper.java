package com.fovsoft.gn.mapper.srdjb;

import com.fovsoft.gn.entity.holder.JbxxSrHolder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收入登记表的公共mapper
 */
public interface CommonMapper {
    int add(@Param(value = "param") List param);

    int delete(@Param("fid") int fid, @Param("nf") String nf);
}
