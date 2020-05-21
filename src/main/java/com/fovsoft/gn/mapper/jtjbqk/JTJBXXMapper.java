package com.fovsoft.gn.mapper.jtjbqk;

import com.fovsoft.gn.entity.JtjbxxDO;
import com.fovsoft.gn.entity.holder.JbxxHzHolder;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 家庭基本信息mapper
 */
@Mapper
public interface JTJBXXMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ym_jtjbqk_jtjbxx(shi,xian,zhen,xzc,zrct,lxdh,khyh,yhkh,pkhsx,jhtpnd,fpnd,fpyy,sfjls,sfbqh,bqfs,bqdz,lrrq,xgrq,lrrdm,scbz) VALUES " +
            "(#{shi},#{xian},#{zhen},#{xzc},#{zrct},#{lxdh},#{khyh},#{yhkh},#{pkhsx},#{jhtpnd},#{fpnd},#{fpyy},#{sfjls},#{sfbqh},#{bqfs},#{bqdz},#{lrrq},#{xgrq},#{lrrdm},#{scbz})"
    )
    int add(JtjbxxDO jtjbxxDO);

    @Update({"UPDATE ym_jtjbqk_jtjbxx SET scbz = 1 WHERE id = #{id}; " +
            "UPDATE ym_jtjbqk_jtcy SET scbz = 1 WHERE fid = #{id}; " +
            "UPDATE ym_jtjbqk_zpyy SET scbz = 1 WHERE fid = #{id}; " +
            "UPDATE ym_jtjbqk_scshtj SET scbz = 1 WHERE fid = #{id}; "
    })
    int delete(Integer id);

    @Update({"UPDATE ym_jtjbqk_jtjbxx SET scbz = 1 WHERE id IN ( ${inStr} );" +
            "UPDATE ym_jtjbqk_jtcy SET scbz = 1 WHERE fid IN ( ${inStr} );" +
            "UPDATE ym_jtjbqk_zpyy SET scbz = 1 WHERE fid IN ( ${inStr} );" +
            "UPDATE ym_jtjbqk_scshtj SET scbz = 1 WHERE fid IN ( ${inStr} );"
    })
    int delAll(@RequestParam(name = "inStr") String inStr);


    @Update("UPDATE ym_jtjbqk_jtjbxx set shi=#{shi},xian=#{xian},zhen=#{zhen},xzc=#{xzc},zrct=#{zrct},lxdh=#{lxdh},khyh=#{khyh},yhkh=#{yhkh},pkhsx=#{pkhsx},jhtpnd=#{jhtpnd},fpnd=#{fpnd},fpyy=#{fpyy},sfjls=#{sfjls},sfbqh=#{sfbqh},bqfs=#{bqfs},bqdz=#{bqdz},lrrq=#{lrrq},xgrq=#{xgrq},lrrdm=#{lrrdm},scbz=#{scbz} WHERE id=#{id}")
    int update(JtjbxxDO jtjbxxDO);

    @Select("select id,shi,xian,zhen,xzc,zrct,lxdh,khyh,yhkh,pkhsx,jhtpnd,fpnd,fpyy,sfjls,sfbqh,bqfs,bqdz,lrrq,xgrq,lrrdm,scbz FROM ym_jtjbqk_jtjbxx WHERE id = #{id}")
    JtjbxxDO get(int id);

    @Select({"<script>",
            "SELECT * FROM ( ",
            "SELECT a.`id`,a.`shi`,a.`xian`,a.`xzc`,a.`pkhsx`,b.`fid`,b.`xm` hzxm,b.`zjhm` FROM ym_jtjbqk_jtjbxx a ",
            "LEFT JOIN ( ",
            "SELECT fid,xm,xb,zjhm,scbz ",
            "FROM ym_jtjbqk_jtcy WHERE yhzgx = '01' AND scbz = 0) b ",
            "ON a.`id` = b.`fid` ",
            "WHERE a.`scbz` = 0 ",
            ") t WHERE 1 = 1 ",
            "<when test='name!=null'>",
            "and t.hzxm like '%${name}%' ",
            "</when>",
            "<when test='sfzhm != null '>",
            "and t.zjhm like '%${sfzhm}%' ",
            "</when>",
            " ORDER BY t.id desc",
            "</script>"})
    List<JbxxHzHolder> list(@Param("name") String name, @Param("sfzhm")String sfzhm);

    @Select("SELECT azd," +
            "ldfh," +
            "ndbqrw," +
            "qcdxxdz," +
            "qcdlx," +
            "jtsyrk," +
            "zfmj," +
            "zczj," +
            "bqrzsj," +
            "sjrzsj," +
            "yyzf," +
            "yyzfmj," +
            "ccjf," +
            "bhzfzl," +
            "ccsj," +
            "ccjfmj," +
            "dxcfjl," +
            "fkflqk," +
            "cyfcxm," +
            "nnhdcyfczj," +
            "tpqk," +
            "hkqy," +
            "fczblhq FROM ym_jtjbqk_jtjbxx WHERE id = #{id}")
    JtjbxxDO getNew(int id);

    @Select("SELECT COUNT(1) FROM ym_jtjbqk_jtcy WHERE fid = #{fid} AND  yhzgx = '01' ")
    int getHz(int fid);
}

