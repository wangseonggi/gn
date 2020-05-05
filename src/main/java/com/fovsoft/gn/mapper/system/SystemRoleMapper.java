package com.fovsoft.gn.mapper.system;

import com.fovsoft.gn.entity.SystemRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SystemRoleMapper {


    @Select({"<script>" +
            "SELECT id, mc,sm, bz, zt, cjsj, gxsj FROM sys_role WHERE 1 = 1 ",
            "<when test='mc!=null'>",
            "and mc like '%${mc}%'",
            "</when>",
            "<when test='sm!=null'>",
            "and sm like '%${sm}%'",
            "</when>",
            "</script>"})
    public List<SystemRoleDO> list(@Param("mc")String mc, @Param("sm")String sm);

    public SystemRoleDO get(int id);

    public int update();

    public int del(int id);
}
