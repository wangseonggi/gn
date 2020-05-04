package com.fovsoft.gn.security.service;

import com.fovsoft.gn.security.bean.SysMenu;
import com.fovsoft.gn.security.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    public List<SysMenu> getMenus(String url) {
        return sysMenuMapper.getSysMenuByURL(url);
    }
}
