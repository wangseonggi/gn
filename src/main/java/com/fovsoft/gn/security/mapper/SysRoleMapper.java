package com.fovsoft.gn.security.mapper;

import com.fovsoft.gn.security.bean.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    @Select("select distinct(a.mc) as mc\n" +
            "from sys_role a, sys_role_menu b, sys_menu c \n" +
            "where a.id = b.roleid AND b.`menuid` = c.`id` AND c.`zt` = 1 and b.menuid = #{menuid}")
    List<SysRole> selectRolesByMenuId(int menuid);
}
