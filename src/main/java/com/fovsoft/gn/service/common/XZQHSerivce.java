package com.fovsoft.gn.service.common;

import com.fovsoft.gn.entity.XzqhDO;
import com.fovsoft.gn.mapper.common.XZQHMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class XZQHSerivce {

    @Resource
    private XZQHMapper xzqhMapper;

    public List<XzqhDO> list(String sjxzqhdm) {
        return xzqhMapper.get(sjxzqhdm);
    }
}
