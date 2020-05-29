package com.fovsoft.gn.mapper.common;

import com.fovsoft.gn.entity.DmDo;
import com.fovsoft.gn.entity.XzqhDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 代码mapper
 */
@Mapper
public interface DmMapper {

    @Select("SELECT NAME,dm FROM ${table} WHERE zt = 'Y' AND xy = 'Y'")
    List<DmDo> getDm(@Param("table") String table);

}

