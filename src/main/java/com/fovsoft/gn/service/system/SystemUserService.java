package com.fovsoft.gn.service.system;

import com.fovsoft.gn.entity.SystemUserDO;
import com.fovsoft.gn.entity.holder.SystemUserHolder;
import com.fovsoft.gn.mapper.system.SystemUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;

    public PageInfo list(Integer page, Integer limit, String username) {
        PageHelper.startPage(page, limit);
        List<SystemUserDO> list = systemUserMapper.list(username);

        return new PageInfo(list);
    }

    public int update(SystemUserHolder systemUserHolders) {
        return systemUserMapper.update(systemUserHolders);
    }


    public SystemUserDO get(Integer id) {
        return systemUserMapper.get(id);
    }
}