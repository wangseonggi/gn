package com.fovsoft.gn.service;


import com.fovsoft.gn.entity.AzdDo;
import com.fovsoft.gn.entity.AzdFwxxDo;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import com.fovsoft.gn.entity.holder.BfrBqhHolder;
import com.fovsoft.gn.mapper.azd.AzdMapper;
import com.fovsoft.gn.mapper.bfr.BfrMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 安置点service
 *
 *
 */
@Service
public class AzdSerivce {

    @Resource
    private AzdMapper azdMapper;

    public List getList(){
        List<AzdDo> list = azdMapper.list();
        return list;
    }

    public PageInfo getFwxxList(Integer page, Integer limit, Integer aid, String ld ,String dy ,String fh ,Double mjgt ,Double mjlt) {
        PageHelper.startPage(page, limit);
        List<AzdFwxxDo> list = azdMapper.fwxxList(aid, ld, dy, fh, mjgt, mjlt);
        return new PageInfo(list);
    }


    public AzdFwxxDo fwxxGet(int id) {
        return azdMapper.fwxxGet(id);
    }

    public int fwxxAddOrUpdate(AzdFwxxDo azdFwxxDo) {
        int id = 0;
        if(azdFwxxDo.getId() == 0) { // 新增
            id = azdMapper.fwxxAdd(azdFwxxDo);
        }
        else {                  // 更新
            id = azdMapper.fwxxUpdate(azdFwxxDo);
        }

        return id;
    }

    public BfgxXXHolder getGLZHXX(int id) {
        return azdMapper.getGLZHXX(id);
    }
}
