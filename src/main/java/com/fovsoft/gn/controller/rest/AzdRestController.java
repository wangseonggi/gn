package com.fovsoft.gn.controller.rest;

import com.fovsoft.common.JsonResult;
import com.fovsoft.gn.entity.AzdDo;
import com.fovsoft.gn.entity.AzdFwxxDo;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import com.fovsoft.gn.service.AzdSerivce;
import com.fovsoft.gn.util.UUIDUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/yw/azd")
public class AzdRestController {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${webappfile.uploadPath}")
    private String uploadRootPath;

    @Autowired
    private AzdSerivce azdSerivce;

    @RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List getList() {
        return azdSerivce.getList();
    }

    @RequestMapping(value = "/getFwxxList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object getFwxxList(Integer page, Integer limit, Integer aid, String ld ,String dy ,String fh ,Double mjgt ,Double mjlt) {

        if(ld != null) {
            ld = ld.equals("") ? null : ld;
        }
        if(dy != null) {
            dy = dy.equals("") ? null : dy;
        }
        if(fh != null) {
            fh = fh.equals("") ? null : fh;
        }

        PageInfo pageInfo = azdSerivce.getFwxxList(page, limit, aid, ld, dy, fh, mjgt, mjlt);
        Map result = new HashMap();
        result.put("data", pageInfo.getList());
        result.put("msg", "");
        result.put("count", pageInfo.getTotal());
        result.put("code", 0);

        return result;
    }

    @RequestMapping(value = "/fwxxGet",  produces = "application/json;charset=UTF-8")
    public JsonResult fwxxGet(int id) {
        AzdFwxxDo azdFwxxDo = azdSerivce.fwxxGet(id);
        return new JsonResult(azdFwxxDo);
    }

    @RequestMapping(value = "/fwxxAdd",  produces = "application/json;charset=UTF-8")
    public JsonResult fwxxAdd(@RequestBody AzdFwxxDo azdFwxxDo) {
        String dy = (azdFwxxDo.getDy().equals("") || Integer.parseInt(azdFwxxDo.getDy())==0)? "0" : azdFwxxDo.getDy();
        azdFwxxDo.setMc("#" + azdFwxxDo.getLd() + "-" + dy + "-" + azdFwxxDo.getFh());
        int affectRow = azdSerivce.fwxxAddOrUpdate(azdFwxxDo);
        return new JsonResult(affectRow);
    }


    /**
     * 获取关联住户信息
     *
     * 该方法根据住房id返回当前住房的住户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGLZHXX",  produces = "application/json;charset=UTF-8")
    public JsonResult getGLZHXX(int id) {
        BfgxXXHolder bfgxXXHolder = azdSerivce.getGLZHXX(id);
        return new JsonResult(bfgxXXHolder);
    }

    /**
     * 入住
     *
     * 用于关联住房与家庭的关系
     *
     * @return
     */
    @RequestMapping(value = "/rz", produces = "application/json;charset=UTF-8")
    public JsonResult rz(Integer fwid, Integer fid) {
        int affectRow = azdSerivce.rz(fwid, fid);
        return new JsonResult(affectRow);
    }

    /**
     * 批量删除
     *
     * 多表批量删除，没加事务
     *
     * @return
     */
    @RequestMapping(value = "/delAll",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult delAll(@RequestBody ArrayList<Integer> ids) {
        String inStr = "";
        for (int i:
                ids) {
            inStr += i + ",";
        }
        inStr = inStr.substring(0, inStr.length() - 1);
        azdSerivce.delAll(inStr);
        return new JsonResult();
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public JsonResult del(Integer id) {
        int returnId = azdSerivce.del(id);

        return new JsonResult(Integer.valueOf(returnId));
    }

    /**
     * 根据id查询安置点
     * @return
     */
    @RequestMapping(value = "/getAzd", produces = "application/json;charset=UTF-8")
    public JsonResult addAzd(@RequestParam Integer id) {
        AzdDo azdDo = azdSerivce.getAzd(id);
        return new JsonResult(azdDo);
    }

    /**
     * 新增/编辑安置点
     * @return
     */
    @RequestMapping(value = "/addAzd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResult addAzd(@RequestBody AzdDo azdDo) {
        Integer count;
        azdDo.setLrrq(new Date());
        if(azdDo.getId() > 0) {
            count = azdSerivce.updateAzd(azdDo);
        }
        else {
            count = azdSerivce.addAzd(azdDo);
        }
        return new JsonResult(count);
    }

    /**
     * 删除安置点
     *
     * 删除前先判断是否存在住房信息，存在则禁止删除并提示
     *
     * @return
     */
    @RequestMapping(value = "/delAzd", produces = "application/json;charset=UTF-8")
    public JsonResult delAzd(@RequestParam Integer id) {

        PageInfo pageInfo = azdSerivce.getFwxxList(0, 10, id, null, null, null, null, null);
        Integer size = pageInfo.getSize();
        if(size != 0) {
            return new JsonResult(-1, "请先删除该安置点下的住房信息");
        }
        else {
            Integer affectNum = azdSerivce.delAzd(id);
            return new JsonResult(affectNum);
        }

    }


    /**
     * 上传安置点主题图片
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new JsonResult(-1, "上传失败，请选择文件。");
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUIDUtil.getUUID() + suffix;
        String ywPath = "/azd/";
        File dest = new File(uploadRootPath + ywPath + fileName);
        try {
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return new JsonResult(ywPath + fileName);
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return new JsonResult(-1, "上传失败");
    }
}