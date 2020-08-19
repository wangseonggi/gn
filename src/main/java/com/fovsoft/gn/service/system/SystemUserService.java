package com.fovsoft.gn.service.system;

import com.fovsoft.gn.entity.SystemUserDO;
import com.fovsoft.gn.entity.holder.RoleHasHolder;
import com.fovsoft.gn.entity.holder.SystemUserHolder;
import com.fovsoft.gn.mapper.system.SystemUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

    public PageInfo list(Integer page, Integer limit, String username) {
        PageHelper.startPage(page, limit);
        List<SystemUserDO> list = systemUserMapper.list(username);

        return new PageInfo(list);
    }

    public int update(SystemUserHolder systemUserHolders) {
        return systemUserMapper.update(systemUserHolders);
    }

    public int add(SystemUserHolder systemUserHolder) {
        return systemUserMapper.add(systemUserHolder);
    }

    public SystemUserDO get(Integer id) {
        return systemUserMapper.get(id);
    }

    public List<RoleHasHolder> listRole(Integer id) {
        return systemUserMapper.listRole(id);
    }

    public int setRole(Integer roleid, Boolean status, Integer userid) {
        int result = 0;
        if(status) {
            result = systemUserMapper.setRole(roleid,userid);
        }
        else {
            result = systemUserMapper.removeRole(roleid,userid);
        }
        return result;
    }

    @Transactional
    public int del(int id) {
        int a = systemUserMapper.del(id);
        int b = systemUserMapper.delUserRole(id);
        return a > 0 && b > 0 ? 1 : 0;
    }
}
