package com.fovsoft.gn.service;


import com.fovsoft.gn.entity.AzdDo;
import com.fovsoft.gn.entity.AzdFwxxDo;
import com.fovsoft.gn.entity.holder.AzdFwxxHzHolder;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import com.fovsoft.gn.mapper.azd.AzdMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
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

    public Integer addAzd(AzdDo azdDo) {
        Integer affectRowCount = azdMapper.addAzd(azdDo);
        return affectRowCount;
    }

    public List getList(){
        List<AzdDo> list = azdMapper.list();
        return list;
    }

    public PageInfo getFwxxList(Integer page, Integer limit, Integer aid, String ld ,String dy ,String fh ,Double mjgt ,Double mjlt) {
        PageHelper.startPage(page, limit);
        List<AzdFwxxHzHolder> list = azdMapper.fwxxList(aid, ld, dy, fh);
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

    public int rz(int fwid, int fid) {
        Object id = azdMapper.getRz(fwid);
        if(id != null && Integer.parseInt(id.toString()) > 0) {
            // 更新
            return azdMapper.updateRz(Integer.parseInt(id.toString()), fwid, fid);
        }
        else {
            return azdMapper.rz(fwid, fid);
        }
    }

    @Transactional
    public int delAll(String ids) {
        int fwid = azdMapper.delAll(ids);
        int rzid = azdMapper.delAllRz(ids);
        return rzid;
    }

    @Transactional
    public int del(int id) {
        int fwid = azdMapper.del(id);
        int rzid = azdMapper.delRz(id);

        return rzid;
    }
}
