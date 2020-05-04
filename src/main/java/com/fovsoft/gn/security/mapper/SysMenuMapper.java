package com.fovsoft.gn.security.mapper;

import com.fovsoft.gn.security.bean.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    @Select("SELECT id, pid, mc, px, zt, cjsj, gxsj \n" +
            "FROM sys_menu WHERE url = #{url} and zt = 1")
    List<SysMenu> getSysMenuByURL(String url);
}
