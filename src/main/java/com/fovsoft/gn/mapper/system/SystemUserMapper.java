package com.fovsoft.gn.mapper.system;

import com.fovsoft.gn.entity.SystemUserDO;
import com.fovsoft.gn.entity.holder.SystemUserHolder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SystemUserMapper {

    @Select({"<script>",
            "SELECT id, username, nc, xb, dh, dzyx, zhyxq, mmyxq, zt FROM sys_user WHERE 1 = 1 ",
            "<when test='username!=null'>",
            "and username like '%${username}%'",
            "</when>",
            "</script>"})
    public List<SystemUserDO> list(@Param("username") String username);

    @Select("SELECT username, nc, xb, dh, dzyx FROM sys_user WHERE id = ${id}")
    public SystemUserDO get(int id);

    @Update({"<script>",
            "UPDATE sys_user SET nc=#{nc},dh=#{dh},dzyx=#{dzyx} ",
            "<when test='password!=null'>",
            ",password=#{password} ",
            "</when>",
            "WHERE id=#{id}",
            "</script>"})
    public int update(SystemUserHolder systemUserHolder);

    public int del(int id);
}
