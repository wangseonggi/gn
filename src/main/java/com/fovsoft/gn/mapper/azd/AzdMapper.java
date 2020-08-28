package com.fovsoft.gn.mapper.azd;

import com.fovsoft.gn.entity.AzdDo;
import com.fovsoft.gn.entity.AzdFwxxDo;
import com.fovsoft.gn.entity.holder.AzdFwxxHzHolder;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AzdMapper {

    // 安置点相关

    @Select("SELECT id, mc, jc, dz, lrrq, img FROM ym_azd")
    List<AzdDo> list();


    @Select("SELECT mc, jc, dz, img FROM ym_azd WHERE id = #{id}")
    AzdDo getAzd(@Param("id")Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ym_azd (mc,jc,lrrq,img, dz)  " +
            "VALUES (#{mc},#{jc},#{lrrq},#{img},#{dz})")
    Integer addAzd(AzdDo azdDo);


    @Update("UPDATE ym_azd SET mc = #{mc}, jc = #{jc}, dz = #{dz}, img=#{img}, lrrq=#{lrrq} WHERE id = #{id} ")
    Integer updateAzd(AzdDo azdDo);

    @Delete("DELETE FROM ym_azd WHERE id = #{id}")
    Integer delAzd(@Param("id")Integer id);

    // 详情相关
    @Select({"<script>",
            "SELECT t.*,tt.xm,tt.zjhm FROM (SELECT a.id,ld,dy,fh, mc FROM ym_azd_fwxx a WHERE aid = #{aid} ",
            "<when test='ld!=null '>",
            "and ld = #{ld} ",
            "</when>",
            "<when test='dy!=null '>",
            "and dy = #{dy} ",
            "</when>",
            "<when test='fh!=null '>",
            "and fh like '%${fh}%' ",
            "</when>",
            ") t",
            " LEFT JOIN ym_azd_fwxx_rz m ON t.id = m.fwid",
            " LEFT JOIN (SELECT a.id,b.xm,b.zjhm FROM ym_jtjbqk_jtjbxx a LEFT JOIN ym_jtjbqk_jtcy b ON a.id = b.fid WHERE b.yhzgx = '02') tt ON m.fid = tt.id ORDER BY t.id DESC",
            "</script>"})
    List<AzdFwxxHzHolder> fwxxList(@Param("aid")Integer aid, @Param("ld")String ld , @Param("dy")String dy , @Param("fh") String fh);

    @Select("SELECT aid, ld, dy, fh, mc FROM ym_azd_fwxx WHERE id = #{id}")
    AzdFwxxDo fwxxGet(@Param("id")Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ym_azd_fwxx (aid,ld,dy,fh,mc)" +
            " VALUES (#{aid},#{ld},#{dy},#{fh},#{mc})")
    Integer fwxxAdd(AzdFwxxDo azdFwxxDo);

    @Update("UPDATE ym_azd_fwxx SET ld = #{ld}, dy = #{dy}, fh = #{fh}, mc=#{mc} WHERE id = #{id}")
    Integer fwxxUpdate(AzdFwxxDo azdFwxxDo);

    @Select("SELECT aa.*,bb.num FROM ( " +
            "SELECT t.id,t.pkhsx, d.`xm`,d.`zjhm` " +
            "FROM ( " +
            "SELECT c.id,c.`pkhsx` FROM " +
            "ym_jtjbqk_jtjbxx c WHERE c.id = (SELECT fid FROM ym_azd_fwxx_rz WHERE fwid = #{id}) " +
            ") t LEFT JOIN ym_jtjbqk_jtcy d ON t.id = d.`fid` WHERE d.yhzgx = '02' " +
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
    BfgxXXHolder getGLZHXX(Integer id);


    @Insert("INSERT INTO ym_azd_fwxx_rz (fwid, fid) VALUES (#{fwid},#{fid})")
    Integer rz(@Param("fwid")Integer fwid, @Param("fid")Integer fid);

    @Select("SELECT id from ym_azd_fwxx_rz WHERE fwid = #{fwid}")
    Integer getRz(@Param("fwid")Integer fwid);

    @Update("UPDATE ym_azd_fwxx_rz set fwid = #{fwid}, fid=#{fid} WHERE id = #{id}")
    Integer updateRz(@Param("id")Integer id, @Param("fwid")Integer fwid, @Param("fid")Integer fid);

    @Delete("DELETE FROM ym_azd_fwxx WHERE id = #{id}")
    Integer del(@Param("id")Integer id);

    @Delete("DELETE FROM ym_azd_fwxx_rz WHERE fwid = #{fwid}")
    Integer delRz(@Param("fwid")Integer fwid);

    @Delete("DELETE FROM ym_azd_fwxx WHERE id IN (#{id})")
    Integer delAll(@Param("id")String id);

    @Delete("DELETE FROM ym_azd_fwxx_rz WHERE fwid IN (#{fwid})")
    Integer delAllRz(@Param("fwid")String fwid);
}
