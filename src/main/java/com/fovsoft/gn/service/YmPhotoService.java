package com.fovsoft.gn.service;

import com.fovsoft.gn.entity.JtcyDO;
import com.fovsoft.gn.entity.YmPhotoDo;
import com.fovsoft.gn.mapper.jtjbqk.JTCYMapper;
import com.fovsoft.gn.mapper.photo.YmPhotoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: by tpc
 * @date: 2020/7/20 17:27
 * @description: 家庭影像资料服务类
 **/

@Service
public class YmPhotoService {

    @Resource
    private JTCYMapper jtcyMapper;

    @Resource
    private YmPhotoMapper ymPhotoMapper;


    public List<JtcyDO> getHouseholder(String name,String sfzhm){
        return jtcyMapper.getHouseholder(name,sfzhm);
    }

    public YmPhotoDo getHouseholderPhoto(Integer familyId){
        return ymPhotoMapper.getHouseholderPhoto(familyId);
    }
}
