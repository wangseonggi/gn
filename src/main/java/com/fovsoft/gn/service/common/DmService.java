package com.fovsoft.gn.service.common;

import com.fovsoft.gn.entity.DmDo;
import com.fovsoft.gn.mapper.common.DmMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码Serivce
 */
@Service
public class DmService {

    private final String FPYY = "dm_ym_fpyy";
    private final String JKZK = "dm_ym_jkzk";
    private final String LDJN = "dm_ym_ldjn";
    private final String PKHSX = "dm_ym_pkhsx";
    private final String RHLLX = "dm_ym_rhllx";
    private final String SXHCXYY = "dm_ym_sxhcxyy";
    private final String WFJB = "dm_ym_wfjb";
    private final String YHZGX = "dm_ym_yhzgx";
    private final String ZXSQK = "dm_ym_zxsqk";
    private final String ZYRLLX = "dm_ym_zyrllx";
    private final String XB = "dm_ym_xb";
    private final String MZ = "dm_ym_mz";
    private final String ZZMM = "dm_ym_zzmm";
    private final String WHCD = "dm_ym_whcd";
    private final String SHZT = "dm_ym_shzt";
    private final String RKLX = "dm_ym_rklx";
    private final String ZDQK = "dm_ym_zdqk";
    private final String JYQK = "dm_ym_jyqk";
    private final String WDJYCYDY = "dm_ym_wdjycydy";
    private final String YLBXLX = "dm_ym_ylbxlx";
    private final String CJDJ = "dm_ym_cjdj";

    private final String[] tableNames = new String[]{"dm_ym_fpyy", "dm_ym_jkzk", "dm_ym_ldjn", "dm_ym_pkhsx", "dm_ym_rhllx", "dm_ym_sxhcxyy", "dm_ym_wfjb", "dm_ym_yhzgx", "dm_ym_zxsqk", "dm_ym_zyrllx", "dm_ym_xb", "dm_ym_mz", "dm_ym_zzmm", "dm_ym_whcd", "dm_ym_shzt", "dm_ym_rklx", "dm_ym_zdqk", "dm_ym_jyqk", "dm_ym_wdjycydy", "dm_ym_ylbxlx", "dm_ym_cjdj"};


    @Resource
    private DmMapper dmMapper;

    public Map getDmByNames(List<String> param) {
        Map result = new HashMap();
        for (String dmType :
                param) {
            String currentTableName = "dm_ym_" + dmType;
            if(Arrays.asList(tableNames).contains(currentTableName)) {
                result.put(dmType, dmMapper.getDm(currentTableName));
            }
        }
        return result;
    }

    /**
     * 返贫原因
     *
     * @return
     */
    public List<DmDo> fpyy() {
        return dmMapper.getDm(FPYY);
    }

    /**
     * 健康状况
     *
     * @return
     */
    public List<DmDo> jkzk() {
        return dmMapper.getDm(JKZK);
    }

    /**
     * 劳动技能
     *
     * @return
     */
    public List<DmDo> ldjn() {
        return dmMapper.getDm(LDJN);
    }

    /**
     * 贫困户属性
     *
     * @return
     */
    public List<DmDo> pkhsx() {
        return dmMapper.getDm(PKHSX);
    }

    /**
     * 入户路类型
     *
     * @return
     */
    public List<DmDo> rhllx() {
        return dmMapper.getDm(RHLLX);
    }

    /**
     * 失学或辍学原因
     *
     * @return
     */
    public List<DmDo> sxhcxyy() {
        return dmMapper.getDm(SXHCXYY);
    }

    /**
     * 危房级别
     *
     * @return
     */
    public List<DmDo> wfjb() {
        return dmMapper.getDm(WFJB);
    }

    /**
     * 与户主关系
     *
     * @return
     */
    public List<DmDo> yhzgx() {
        return dmMapper.getDm(YHZGX);
    }

    /**
     * 在校生情况
     *
     * @return
     */
    public List<DmDo> zxsqk() {
        return dmMapper.getDm(ZXSQK);
    }

    /**
     * 主要燃料类型
     *
     * @return
     */
    public List<DmDo> zyrllx() {
        return dmMapper.getDm(ZYRLLX);
    }

    /**
     * 性别
     *
     * @return
     */
    public List<DmDo> xb() {
        return dmMapper.getDm(XB);
    }

    /**
     * 民族
     *
     * @return
     */
    public List<DmDo> mz() {
        return dmMapper.getDm(MZ);
    }

    /**
     * 政治面貌
     *
     * @return
     */
    public List<DmDo> zzmm() {
        return dmMapper.getDm(ZZMM);
    }

    /**
     * 文化程度
     *
     * @return
     */
    public List<DmDo> whcd() {
        return dmMapper.getDm(WHCD);
    }

    public List<DmDo> shzt() {
        return dmMapper.getDm(SHZT);
    }

    public List<DmDo> rklx() {
        return dmMapper.getDm(RKLX);
    }

    public List<DmDo> zdqk() {
        return dmMapper.getDm(ZDQK);
    }

    public List<DmDo> jyqk() {
        return dmMapper.getDm(JYQK);
    }

    public List<DmDo> wdjycydy() {
        return dmMapper.getDm(WDJYCYDY);
    }

    public List<DmDo> ylbxlx() {
        return dmMapper.getDm(YLBXLX);
    }

    public List<DmDo> cjdj() {
        return dmMapper.getDm(CJDJ);
    }
}
