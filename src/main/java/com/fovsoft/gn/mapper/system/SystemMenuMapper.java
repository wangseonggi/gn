package com.fovsoft.gn.mapper.system;

import com.fovsoft.gn.entity.SystemMenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SystemMenuMapper {

    @Select({"<script>" +
            "SELECT id, pid, mc, url, px, zt,cjsj, gxsj FROM sys_menu WHERE 1 = 1 ",
            "<when test='pid!=null'>",
            "and pid = ${pid} ",
            "</when>",
            "<when test='mc!=null'>",
            "and mc like '%${mc}%' ",
            "</when>",
            "<when test='zt!=null'>",
            "and zt = ${zt} ",
            "</when>",
            "ORDER BY url",
            "</script>"})
    public List<SystemMenuDO> list(@Param("pid")Integer pid, @Param("mc")String mc, @Param("zt")Integer zt);

    public SystemMenuDO get(int id);

    public int update();

    public int del(int id);
}
