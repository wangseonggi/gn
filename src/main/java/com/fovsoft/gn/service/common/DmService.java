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

    private final String FPYY = "DM_YM_FPYY";
    private final String JKZK = "DM_YM_JKZK";
    private final String LDJN = "DM_YM_LDJN";
    private final String PKHSX = "DM_YM_PKHSX";
    private final String RHLLX = "DM_YM_RHLLX";
    private final String SXHCXYY = "DM_YM_SXHCXYY";
    private final String WFJB = "DM_YM_WFJB";
    private final String YHZGX = "DM_YM_YHZGX";
    private final String ZXSQK = "DM_YM_ZXSQK";
    private final String ZYRLLX = "DM_YM_ZYRLLX";
    private final String XB = "DM_YM_XB";
    private final String MZ = "DM_YM_MZ";
    private final String ZZMM = "DM_YM_ZZMM";
    private final String WHCD = "DM_YM_WHCD";
    private final String SHZT = "DM_YM_SHZT";
    private final String RKLX = "DM_YM_RKLX";
    private final String ZDQK = "DM_YM_ZDQK";
    private final String JYQK = "DM_YM_JYQK";
    private final String WDJYCYDY = "DM_YM_WDJYCYDY";
    private final String YLBXLX = "DM_YM_YLBXLX";
    private final String CJDJ = "DM_YM_CJDJ";

    private final String[] tableNames = new String[]{"DM_YM_FPYY", "DM_YM_JKZK", "DM_YM_LDJN", "DM_YM_PKHSX", "DM_YM_RHLLX", "DM_YM_SXHCXYY", "DM_YM_WFJB", "DM_YM_YHZGX", "DM_YM_ZXSQK", "DM_YM_ZYRLLX", "DM_YM_XB", "DM_YM_MZ", "DM_YM_ZZMM", "DM_YM_WHCD", "DM_YM_SHZT", "DM_YM_RKLX", "DM_YM_ZDQK", "DM_YM_JYQK", "DM_YM_WDJYCYDY", "DM_YM_YLBXLX", "DM_YM_CJDJ"};


    @Resource
    private DmMapper dmMapper;

    public Map getDmByNames(List<String> param) {
        Map result = new HashMap();
        for (String dmType :
                param) {
            String currentTableName = "DM_YM_" + dmType.toUpperCase();
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
