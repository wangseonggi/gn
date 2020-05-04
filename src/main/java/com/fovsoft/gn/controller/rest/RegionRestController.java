package com.fovsoft.gn.controller.rest;

import com.fovsoft.gn.entity.XzqhDO;
import com.fovsoft.gn.service.common.XZQHSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionRestController {

    @Autowired
    XZQHSerivce xzqhSerivce;

    private static final Logger logger = LoggerFactory.getLogger(RegionRestController.class);

    @RequestMapping(value = "/xzqhdm/get")
    public List list(@RequestParam(name = "sjxzqhdm", required = false)String sjxzqhdm) {
        List<XzqhDO> list = xzqhSerivce.list(sjxzqhdm);
        return list;
    }

}
