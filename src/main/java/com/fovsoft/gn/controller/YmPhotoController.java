package com.fovsoft.gn.controller;

import com.fovsoft.gn.entity.JtcyDO;
import com.fovsoft.gn.entity.YmPhotoDo;
import com.fovsoft.gn.service.YmPhotoService;
import com.fovsoft.gn.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: by tpc
 * @date: 2020/7/20 17:34
 * @description: 家庭影像资料控制类
 **/

@Controller
@RequestMapping(value = "/yw/yxh")
public class YmPhotoController {

    @Autowired
    private YmPhotoService ymPhotoService;

    @Value("${webappfile.uploadPath}")
    private String uploadPath;

    @RequestMapping(value = "/index")
    public String goIndex() {
        return "photo/index";
    }

    @RequestMapping(value = "/delPhoto")
    @ResponseBody
    public Map<String, Object> delPhotoById(Integer id) {

        ymPhotoService.delHouseholderPhotoById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }

    @RequestMapping(value = "/editIndex")
    public ModelAndView goPhotoEdit(String fid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("photo/photo-edit");
        List<YmPhotoDo> ymPhotoList = ymPhotoService.getHouseholderPhoto(Integer.parseInt(fid));
        modelAndView.addObject("ymPhotoList", ymPhotoList);
        return modelAndView;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getHouseHolder(Integer page, Integer limit, String name, String sfzmhm) {

        //分页获取到所有用户信息
        PageHelper.startPage(page, limit);
        List<JtcyDO> ymFamilyMemberList = ymPhotoService.getHouseholder(name, sfzmhm);
        PageInfo<JtcyDO> pageInfo = new PageInfo<JtcyDO>(ymFamilyMemberList);
        //获得总记录数
        long records = pageInfo.getTotal();
        //默认当前页码
        int pageNum = pageInfo.getPageNum();
        //获得总页数
        int total = pageInfo.getPages();
        Map<String, Object> map = new HashMap<>();
        map.put("data", ymFamilyMemberList);
        map.put("count", records);
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }

    /*
     * 功能描述: 家庭影像资料上传，利用layui upload多文件上传，多文件上传实际上是传一个文件请求一次后端
     * @author by tpc
     * @date 2020/7/22 15:17
     * @param
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String, Object> uploadPhoto(@RequestParam(value = "file", required = false) MultipartFile file, String fid, HttpServletRequest request) {
        int index = 0;
        Map<String, String> fileNameMap = new HashMap();

        //获取上传的图片后缀格式名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //生产新的文件名
        String fileName = UUIDUtil.getUUID() + "_" + index + suffix;
        //写入数据库中的文件相对路径
        String relativePath = "/imgs/" + fid + "/" + fileName;

        //写入文件对象
        File writeFile = null;
        try {
            //文件写入路径
            String filePath = uploadPath + "/imgs/" + fid + "/" + fileName;
            writeFile = new File(filePath);
            if(!writeFile.getParentFile().exists()){
                writeFile.getParentFile().mkdirs();
            }
            file.transferTo(writeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //先查看家庭影像信息是否采集
        YmPhotoDo ymPhotoDo = ymPhotoService.getHouseholderPhotoMax(Integer.parseInt(fid));

        //如果为空，则进行新增
        if (ymPhotoDo == null) {
            ymPhotoDo = new YmPhotoDo();
            ymPhotoDo.setFid(Integer.parseInt(fid));
            ymPhotoDo.setSequence(1);
            ymPhotoDo.setPhoto1(relativePath);
            ymPhotoService.addHouseHolderPhoto(ymPhotoDo);
        } else {//不为空则增加sequence的值，进行新增
            Integer seq = ymPhotoDo.getSequence() + 1;
            ymPhotoDo.setSequence(seq);
            ymPhotoDo.setPhoto1(relativePath);
            ymPhotoService.addHouseHolderPhoto(ymPhotoDo);
        }

        List<YmPhotoDo> ymPhotoDoList = ymPhotoService.getHouseholderPhoto(Integer.parseInt(fid));
        Map<String, Object> map = new HashMap<>();
        map.put("ymPhotoList", ymPhotoDoList);
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }



}
