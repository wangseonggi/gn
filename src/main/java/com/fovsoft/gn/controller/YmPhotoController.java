package com.fovsoft.gn.controller;

import com.fovsoft.gn.entity.JtcyDO;
import com.fovsoft.gn.entity.YmPhotoDo;
import com.fovsoft.gn.service.YmPhotoService;
import com.fovsoft.gn.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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


    @RequestMapping(value = "/index")
    public String goIndex(){
        return "photo/index";
    }


    @RequestMapping(value="/editIndex")
    public ModelAndView goPhotoEdit(){
        ModelAndView  modelAndView = new ModelAndView();
        modelAndView.setViewName("photo/photo-edit");
        YmPhotoDo ymPhotoDo = ymPhotoService.getHouseholderPhoto(1);
        modelAndView.addObject("ymPhotoDo",ymPhotoDo);
        return modelAndView;
    }




    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> getHouseHolder(Integer page, Integer limit, String name, String sfzmhm){

        //分页获取到所有用户信息
        PageHelper.startPage(page,limit);
        List<JtcyDO> ymFamilyMemberList = ymPhotoService.getHouseholder(name,sfzmhm);
        PageInfo<JtcyDO> pageInfo = new PageInfo<JtcyDO>(ymFamilyMemberList);
        //获得总记录数
        long records = pageInfo.getTotal();
        //默认当前页码
        int pageNum = pageInfo.getPageNum();
        //获得总页数
        int total = pageInfo.getPages();
        Map<String,Object> map = new HashMap<>();
        map.put("data",ymFamilyMemberList);
        map.put("count",records);
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }


    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String,Object> uploadPhoto(@RequestParam(value = "file", required = false)MultipartFile[] files,String fid, HttpServletRequest request){


        System.out.println(fid);
        int index = 0;

        //先查看家庭影像信息是否采集


        for(MultipartFile file:files){

            if(file !=null){
                index++;
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                System.out.println(file.getOriginalFilename());
                System.out.println(suffix);
                String fileName = UUIDUtil.getUUID()+"_"+index+suffix;
                File filePath = null;
                try {
                    String newUrl = ResourceUtils.getURL("classpath:").getPath() + "/static/familyPhoto/"+fid+"/"+fileName;
                    filePath = new File(newUrl);
                    if(!filePath.getParentFile().exists()){
                        filePath.getParentFile().mkdirs();
                    }
                    try {
                        file.transferTo(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        Map<String,Object> map = new HashMap<>();

        map.put("code", 0);
        map.put("msg", "");
        return map;
    }

}
