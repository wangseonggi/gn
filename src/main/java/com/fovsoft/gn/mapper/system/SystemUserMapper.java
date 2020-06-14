package com.fovsoft.gn.mapper.system;

import com.fovsoft.gn.entity.SystemUserDO;
import com.fovsoft.gn.entity.holder.RoleHasHolder;
import com.fovsoft.gn.entity.holder.SystemUserHolder;
import org.apache.ibatis.annotations.*;

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

    @Select("SELECT username, nc, xb, dh, dzyx, zt, zhyxq, mmyxq FROM sys_user WHERE id = ${id}")
    public SystemUserDO get(int id);

    @Update({"<script>",
            "UPDATE sys_user SET nc=#{nc},dh=#{dh},dzyx=#{dzyx},zhyxq=#{zhyxq},mmyxq=#{mmyxq},zt=#{zt} ",
            "<when test='password!=null'>",
            ",password=#{password} ",
            "</when>",
            "WHERE id=#{id}",
            "</script>"})
    public int update(SystemUserHolder systemUserHolder);

    @Insert("INSERT INTO sys_user (username, password, nc,dh,dzyx,zhyxq,mmyxq,zt) VALUES" +
            " (#{username},#{password},#{nc},#{dh},#{dzyx},#{zhyxq},#{mmyxq},#{zt})")
    public int add(SystemUserHolder systemUserHolder);

    public int del(int id);

    @Select("SELECT a.id,mc,sm,b.`userid` " +
            "FROM (" +
            "SELECT * FROM sys_role " +
            ") a LEFT JOIN (SELECT userid,roleid FROM sys_user_role b WHERE b.`userid` = #{id}) b " +
            "ON a.id = b.`roleid`")
    public List<RoleHasHolder> listRole(Integer id);


    @Insert("INSERT INTO sys_user_role (userid,roleid) values (${userid},${roleid})")
    public int setRole(@Param("roleid") Integer roleid, @Param("userid")Integer userid);

    @Delete("DELETE FROM sys_user_role where roleid = ${roleid} and userid = ${userid}")
    public int removeRole(@Param("roleid")Integer roleid, @Param("userid")Integer userid);
}
