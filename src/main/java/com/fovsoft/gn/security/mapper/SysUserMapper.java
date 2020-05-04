package com.fovsoft.gn.security.mapper;

import com.fovsoft.gn.security.bean.SysRole;
import com.fovsoft.gn.security.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserMapper {

    @Select("select id, username, password, nc, xb, dh, dzyx, zhyxq, mmyxq, zt, zjdlip from sys_user s where s.username=#{userName}")
    SysUser findSysUserByName(String username);


    @Select("select a.*\n" +
            "from sys_role a, sys_user_role b\n" +
            "where a.`id` = b.`roleid`\n" +
            "and a.`zt` = 1 \n" +
            "and b.`userid` = #{id}")
    List<SysRole> getSysRoleByUserId(@Param("id") int sysUserId);
}
