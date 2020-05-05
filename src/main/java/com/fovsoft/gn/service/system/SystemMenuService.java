package com.fovsoft.gn.service.system;

import com.fovsoft.gn.entity.SystemMenuDO;
import com.fovsoft.gn.entity.SystemRoleDO;
import com.fovsoft.gn.mapper.system.SystemMenuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemMenuService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    public PageInfo list(Integer page, Integer limit, Integer pid, String mc, Integer zt) {
        PageHelper.startPage(page, limit);
        List<SystemMenuDO> list = systemMenuMapper.list(pid, mc, zt);

        return new PageInfo(list);
    }
}
