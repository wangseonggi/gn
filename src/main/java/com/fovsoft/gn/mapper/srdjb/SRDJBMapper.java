package com.fovsoft.gn.mapper.srdjb;

import com.fovsoft.gn.entity.holder.JbxxSrHolder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 收入登记表mapper
 *
 * 用于列表操作相关，跟具体的表格无关
 */
@Mapper
public interface SRDJBMapper {

    @Select({"<script>",
            "SELECT * FROM ( ",
            "SELECT t.id,t.fid,t.hzxm,t.zjhm,GROUP_CONCAT(t.nf SEPARATOR ',') AS nf " +
            "FROM ( " +
            "SELECT DISTINCT b.nf,a.id,a.fid,a.hzxm,a.zjhm " +
            "FROM( " +
            "SELECT a.`id`,b.`fid`,b.`xm` hzxm,b.`zjhm` " +
            "FROM ym_jtjbqk_jtjbxx a  " +
            "LEFT JOIN (SELECT fid,xm,xb,zjhm,scbz FROM ym_jtjbqk_jtcy  " +
            "WHERE yhzgx = '02' AND scbz = 'N') b ON a.`id` = b.`fid` WHERE a.`scbz` = 'N' ) a  " +
            "LEFT JOIN ym_srdjb_sr1 b ON a.id = b.fid) t GROUP BY t.id   " +
            ") t WHERE 1 = 1 ",
            "<when test='name!=null'>",
            "and t.hzxm like '%${name}%' ",
            "</when>",
            "<when test='sfzhm != null '>",
            "and t.zjhm like '%${sfzhm}%' ",
            "</when>",
            " ORDER BY t.id desc",
            "</script>"})
    List<JbxxSrHolder> list(@Param("name") String name, @Param("sfzhm")String sfzhm);
}
