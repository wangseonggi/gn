package com.fovsoft.gn.security.service;

import com.fovsoft.gn.security.bean.SysRole;
import com.fovsoft.gn.security.bean.SysUser;
import com.fovsoft.gn.security.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public SysUser findUserByName(String username) {
        return sysUserMapper.findSysUserByName(username);
    }

    public List<SysRole> getRoles(int userId) {
        return sysUserMapper.getSysRoleByUserId(userId);
    }
}
