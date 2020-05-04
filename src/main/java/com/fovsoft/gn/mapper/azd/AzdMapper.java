package com.fovsoft.gn.mapper.azd;

import com.fovsoft.gn.entity.AzdDo;
import com.fovsoft.gn.entity.AzdFwxxDo;
import com.fovsoft.gn.entity.BfrDo;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AzdMapper {

    // 安置点相关

    @Select("SELECT id, mc, jc, dz, lrrq FROM ym_azd")
    public List<AzdDo> list();

    public int add();

    public int edit();

    // 详情相关
    @Select({"<script>",
            "SELECT id,aid,ld,dy,fh,CONCAT(ld, '#',IFNULL(CONCAT('-',dy),''),'-',fh) AS mc  FROM ym_azd_fwxx WHERE aid = #{aid} ",
            "<when test='ld!=null '>",
            "and ld = #{ld} ",
            "</when>",
            "<when test='dy!=null '>",
            "and dy = #{dy} ",
            "</when>",
            "<when test='fh!=null '>",
            "and fh like '%${fh}%' ",
            "</when>",
            "<when test='mjgt!=null'>",
            "and mj &gt; #{mjgt} ",
            "</when>",
            "<when test='mjlt!=null'>",
            "and mj &lt; #{mjlt} ",
            "</when>",
            "</script>"})
    public List<AzdFwxxDo> fwxxList(@Param("aid")int aid, @Param("ld")String ld , @Param("dy")String dy ,@Param("fh") String fh , @Param("mjgt")Double mjgt , @Param("mjlt")Double mjlt);

    @Select("SELECT aid, ld, dy, fh, mc FROM ym_azd_fwxx WHERE id = #{id}")
    public AzdFwxxDo fwxxGet(@Param("id")int id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ym_azd_fwxx (aid,ld,dy,fh)" +
            " VALUES (#{aid},#{ld},#{dy},#{fh})")
    public int fwxxAdd(AzdFwxxDo azdFwxxDo);

    @Update("UPDATE ym_azd_fwxx SET ld = #{ld}, dy = #{dy}, fh = #{fh} WHERE id = #{id}")
    public int fwxxUpdate(AzdFwxxDo azdFwxxDo);

    @Select("SELECT aa.*,bb.num FROM ( " +
            "SELECT t.id,t.pkhsx, d.`xm`,d.`zjhm` " +
            "FROM ( " +
            "SELECT c.id,c.`pkhsx` FROM " +
            "ym_jtjbqk_jtjbxx c WHERE c.id = (SELECT fid FROM ym_azd_fwxx_rz WHERE fwid = #{id}) " +
            ") t LEFT JOIN ym_jtjbqk_jtcy d ON t.id = d.`fid` WHERE d.yhzgx = '01' " +
            ") aa " +
            "LEFT JOIN  " +
            "( " +
            "SELECT c.id, COUNT(1) num FROM " +
            "ym_jtjbqk_jtjbxx c , ym_jtjbqk_jtcy d " +
            "WHERE c.id =  " +
            "(SELECT fid FROM ym_azd_fwxx_rz WHERE fwid = #{id}) " +
            " AND d.`fid` = c.id " +
            ") bb " +
            "ON aa.id = bb.id ")
    public BfgxXXHolder getGLZHXX(int id);
}
