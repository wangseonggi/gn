package com.fovsoft.gn.service.common;

import com.fovsoft.gn.entity.DmDo;
import com.fovsoft.gn.mapper.common.DmMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private DmMapper dmMapper;

    /**
     * 返贫原因
     * @return
     */
    public List<DmDo> fpyy() {
        return dmMapper.getDm(FPYY);
    }

    /**
     * 健康状况
     * @return
     */
    public List<DmDo> jkzk() {
        return dmMapper.getDm(FPYY);
    }

    /**
     * 劳动技能
     * @return
     */
    public List<DmDo> ldjn() {
        return dmMapper.getDm(LDJN);
    }

    /**
     * 贫困户属性
     * @return
     */
    public List<DmDo> pkhsx() {
        return dmMapper.getDm(PKHSX);
    }

    /**
     * 入户路类型
     * @return
     */
    public List<DmDo> rhllx() {
        return dmMapper.getDm(RHLLX);
    }

    /**
     * 失学或辍学原因
     * @return
     */
    public List<DmDo> sxhcxyy() {
        return dmMapper.getDm(SXHCXYY);
    }

    /**
     * 危房级别
     * @return
     */
    public List<DmDo> wfjb() {
        return dmMapper.getDm(WFJB);
    }

    /**
     * 与户主关系
     * @return
     */
    public List<DmDo> yhzgx() {
        return dmMapper.getDm(YHZGX);
    }

    /**
     * 在校生情况
     * @return
     */
    public List<DmDo> zxsqk() {
        return dmMapper.getDm(ZXSQK);
    }

    /**
     * 主要燃料类型
     * @return
     */
    public List<DmDo> zyrllx() {
        return dmMapper.getDm(ZYRLLX);
    }
}
