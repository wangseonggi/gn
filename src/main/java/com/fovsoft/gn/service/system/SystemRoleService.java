package com.fovsoft.gn.service.system;

import com.fovsoft.gn.entity.SystemRoleDO;
import com.fovsoft.gn.mapper.system.SystemRoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemRoleService {
    @Autowired
    private SystemRoleMapper systemRoleMapper;

    public PageInfo list(Integer page, Integer limit, String mc, String sm) {
        PageHelper.startPage(page, limit);
        List<SystemRoleDO> list = systemRoleMapper.list(mc, sm);

        return new PageInfo(list);
    }
}

