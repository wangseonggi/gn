package com.fovsoft.gn.service;

import com.fovsoft.gn.entity.JtcyDO;
import com.fovsoft.gn.entity.JtjbxxDO;
import com.fovsoft.gn.entity.ScshtjDO;
import com.fovsoft.gn.entity.ZpyyDO;
import com.fovsoft.gn.entity.holder.JbxxHzHolder;
import com.fovsoft.gn.mapper.jtjbqk.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 家庭基本情况service
 *
 *
 */
@Service
public class JTJBQKSerivce {

    @Resource
    private JTJBXXMapper jtjbxxMapper;

    @Resource
    private JTCYMapper jtcyMapper;

    /**
     * 致贫原因
     */
    @Resource
    private ZPYYMapper zpyyMapper;

    @Resource
    private SCSHTJMapper scshtjMapper;

    /**
     * 变更情况说明
     */
    @Resource
    private BGQKSMMapper bgqksmMapper;

    /**
     *
     * @return
     */
    public PageInfo getList(Integer page, Integer limit, String name, String sfzhm) {
        PageHelper.startPage(page, limit);
        List<JbxxHzHolder> list =  jtjbxxMapper.list(name, sfzhm);
        return new PageInfo(list);
    }

    /**
     *
     * @param id
     * @return
     */
    public int del(Integer id) {
        return jtjbxxMapper.delete(id);
    }

    public int delMember(Integer id) {
        return jtcyMapper.delete(id);
    }

    public JtcyDO getMember(int id) {
        return jtcyMapper.get(id);
    }

    /**
     * 新增或更新家庭基本信息表
     *
     * @param
     * @return
     */
    public int addOrUpdateFamilyBase(JtjbxxDO jtjbxxDO) {
        int id = 0;
        if(jtjbxxDO.getId() == 0) {
            jtjbxxDO.setLrrq(new Date());
            jtjbxxMapper.add(jtjbxxDO);
            id = jtjbxxDO.getId();
        }
        else {
            jtjbxxMapper.update(jtjbxxDO);
            id = jtjbxxDO.getId();
        }
        return id;
    }

    public JtjbxxDO get(int id) {
        return jtjbxxMapper.get(id);
    }

    /**
     *
     *
     * @return
     */
    public int addOrUpdateFamilyBaseAddition(ZpyyDO zpyyDO) {
        int id = 0;
        if(zpyyDO.getId() == 0) {
//            ymFamilyBaseAddition.setAddTime(new Timestamp(new Date().getTime()));
            zpyyMapper.add(zpyyDO);
            id = zpyyDO.getId();
        }
        else {
            zpyyMapper.update(zpyyDO);
            id = zpyyDO.getId();
        }
        return id;
    }



    public int addOrUpdateFamilyBaseCondition(ScshtjDO scshtjDO) {
        int id = 0;
        if(scshtjDO.getId() == 0) {
            // 插入
            scshtjMapper.add(scshtjDO);
            id = scshtjDO.getId();
        }
        else {
            // 更新
            scshtjMapper.update(scshtjDO);
            id = scshtjDO.getId();
        }
        return id;
    }

    public int addOrUpdateFamilyBaseMember(JtcyDO jtcyDO) {
        int id = 0;
        if(jtcyDO.getId() == 0) {
            // 插入
            jtcyMapper.add(jtcyDO);
            id = jtcyDO.getId();
        }
        else {
            // 更新
            jtcyMapper.update(jtcyDO);
            id = jtcyDO.getId();
        }
        return id;
    }

    public List<JtcyDO> getMemberList(Integer fid) {
        List<JtcyDO> list =  jtcyMapper.list(fid);
        return list;
    }

    public int delAll(String inStr) {
        int affectRows = jtjbxxMapper.delAll(inStr);
        return affectRows;
     }

     public ZpyyDO getAddiction(int fid) {
        return zpyyMapper.get(fid);
     }

    public ScshtjDO getCondition(int fid) {
        return scshtjMapper.get(fid);
    }

    public JtjbxxDO getNew(int id) {
        return jtjbxxMapper.getNew(id);
    }

    public int getHz(int fid) {
        return jtjbxxMapper.getHz(fid);
    }
}