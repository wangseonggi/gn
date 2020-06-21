package com.fovsoft.gn.mapper.system;

import com.fovsoft.gn.entity.SystemRoleDO;
import com.fovsoft.gn.entity.holder.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SystemRoleMapper {
    @Select({"<script>" +
            "SELECT id, mc,sm, bz, zt, cjsj, gxsj FROM sys_role WHERE 1 = 1 and sfkbg = 'Y'",
            "<when test='mc!=null'>",
            "and mc like '%${mc}%'",
            "</when>",
            "<when test='sm!=null'>",
            "and sm like '%${sm}%' ",
            "</when>",
            "</script>"})
    public List<SystemRoleDO> list(@Param("mc")String mc, @Param("sm")String sm);

    public SystemRoleDO get(int id);

    public int update();

    @Delete("DELETE FROM sys_role where id = ${id}")
    public int del(@Param("id") Integer roleId);

    @Delete("DELETE FROM sys_role_menu WHERE roleid = ${roleid}")
    public int delFromMidTable(@Param("roleid")Integer roleId);

    @Select("SELECT id,pid,mc from sys_menu where zt = 1 order by px,id")
    public List<Menu> getMenus();

    @Select("SELECT b.url " +
            "FROM sys_role_menu a, sys_menu b " +
            "WHERE a.`menuid` = b.`id` AND a.`roleid` = #{id} ")
    public List<String> getPermitURL(Integer id);

    @Select("SELECT menuid FROM sys_role_menu WHERE roleid = #{id}")
    public List<Integer> getPermitID(Integer id);


    @Insert({
            "<script>",
            "INSERT INTO sys_role_menu (roleid, menuid) VALUES ",
            "<foreach collection='ids' item='item' index='index' separator=','>",
            "(#{roleId}, #{item})",
            "</foreach>",
            "</script>"
    })
    public int addG(@Param(value = "roleId")Integer roleId, @Param(value = "ids") List ids);

    @Delete("DELETE FROM sys_role_menu WHERE roleid = #{roleId}")
    public int delG(@Param(value = "roleId")Integer roleId);


    @Insert("INSERT INTO sys_role (mc,sm,bz,cjsj) values (#{mc}, #{sm}, #{bz}, #{cjsj})")
    public int add(SystemRoleDO systemRoleDO);
}
