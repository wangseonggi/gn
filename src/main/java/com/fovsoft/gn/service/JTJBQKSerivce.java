package com.fovsoft.gn.service;

import com.fovsoft.gn.entity.*;
import com.fovsoft.gn.entity.holder.JbxxHzHolder;
import com.fovsoft.gn.mapper.jtjbqk.*;
import com.fovsoft.gn.mapper.photo.YmPhotoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    @Resource
    private ZPYYMapper zpyyMapper;
    @Resource
    private SCSHTJMapper scshtjMapper;
    @Resource
    private BGQKSMMapper bgqksmMapper;
    @Resource
    private YmPhotoMapper ymPhotoMapper;

    public PageInfo getList(Integer page, Integer limit, String name, String sfzhm) {
        PageHelper.startPage(page, limit);

        String name1 = name != null && !name.equals("") ? name : null;
        String sfzhm1 = sfzhm != null && !sfzhm.equals("") ? sfzhm : null;

        List<JbxxHzHolder> list =  jtjbxxMapper.list(name1, sfzhm1);
        return new PageInfo(list);
    }

    public int del(Integer id) {
        return jtjbxxMapper.delete(id);
    }

    public int delMember(Integer id) {
        return jtcyMapper.delete(id);
    }

    public JtcyDO getMember(int id) {
        return jtcyMapper.get(id);
    }

    public int addOrUpdateFamilyBase(JtjbxxDO jtjbxxDO) {
        int id = 0;
        if(jtjbxxDO.getId() == 0) {
            jtjbxxDO.setLrrq(new Date());
            jtjbxxMapper.add(jtjbxxDO);
            id = jtjbxxDO.getId();
        }
        else {
            jtjbxxDO.setXgrq(new Date());
            jtjbxxMapper.update(jtjbxxDO);
            id = jtjbxxDO.getId();
        }
        return id;
    }

    public JtjbxxDO get(int id) {
        return jtjbxxMapper.get(id);
    }

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
        if(jtcyDO.getId() == 0) {
            jtcyMapper.add(jtcyDO);
        }
        else {
            jtcyMapper.update(jtcyDO);
        }
        int id = jtcyDO.getId();
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

    public int updateQTXX(JtjbxxDO jtjbxxDO) {
        return jtjbxxMapper.updateQTXX(jtjbxxDO);
    }

    public int addBgqksm(BgqksmDO bgqksmDO) {

        if(bgqksmDO.getId() == 0) {
            bgqksmDO.setLrrq(new Date());
            bgqksmMapper.add(bgqksmDO);
        }
        else {
            bgqksmDO.setLrrq(new Date());
            bgqksmMapper.update(bgqksmDO);
        }
        int id = bgqksmDO.getId();
        return id;
    }

    public BgqksmDO getBgqksm(Integer fid) {
        BgqksmDO bgqksmDO = bgqksmMapper.get(fid);
        return bgqksmDO;
    }

    public List<YmYXZLDo> getImgList(Integer fid, String type) {
        return ymPhotoMapper.getImgList(fid, type);
    }

    public Integer delImg(Integer id) {
        return ymPhotoMapper.delImg(id);
    }
}
