package com.fovsoft.gn.mapper.bfr;

import com.fovsoft.gn.entity.BfrDo;
import com.fovsoft.gn.entity.holder.BfgxXXHolder;
import com.fovsoft.gn.entity.holder.BfrBqhHolder;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface BfrMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ym_bfr (xm,xb,sfzhm,gzdw,zw,lxdh,lrrq)" +
            " VALUES (#{xm},#{xb},#{sfzhm},#{gzdw},#{zw},#{lxdh},#{lrrq})")
    public int add(BfrDo bfrDo);

    @Delete("DELETE FROM ym_bfr WHERE id = #(id)")
    public int delete(int id);

    @Delete("DELETE FROM ym_bfr WHERE id IN ( ${inStr} )")
    public int deleteAll(@RequestParam(name = "inStr") String inStr);

    @Update("UPDATE ym_bfr SET xm = #{xm}, xb = #{xb}, sfzhm = #{sfzhm}, gzdw = #{gzdw}, zw = #{zw}, lxdh = #{lxdh}, lrrq = #{lrrq}, bgrq = #{bgrq} WHERE id = #{id}")
    public int update(BfrDo bfrDo);

    @Select("SELECT xm, xb, sfzhm, gzdw, zw, lxdh, lrrq, bgrq, zt FROM ym_bfr where id = #{id} and zt = 1")
    public BfrDo get(int id);

    @Select({"<script>",
            "SELECT id,xm,xb,sfzhm,gzdw,zw,lxdh,pkhsx,GROUP_CONCAT(fid SEPARATOR ',') AS fid,GROUP_CONCAT(hzxm SEPARATOR ',') AS hzxm ",
            "FROM (",
            "SELECT bfr.id,bfr.xm,bfr.xb,bfr.sfzhm,bfr.gzdw,bfr.zw,bfr.lxdh,t.id AS fid, t.pkhsx,t.xm AS hzxm ",
            "FROM ym_bfr bfr LEFT JOIN ym_mid_bfr_jtjbqk c ON bfr.id = c.`bid`  ",
            "LEFT JOIN (",
            "SELECT jt.id,jt.pkhsx,cy.xm ",
            "FROM ym_jtjbqk_jtjbxx jt LEFT JOIN ym_jtjbqk_jtcy cy ON jt.id = cy.fid ",
            "WHERE cy.yhzgx = 1) t ON c.fid = t.id WHERE 1 = 1 ",
            "<when test='name!=null'>",
            "and bfr.xm like '%${name}%' ",
            "</when>",
            "<when test='gzdw!=null'>",
            "and bfr.gzdw like '%${gzdw}%' ",
            "</when>",
            "<when test='lxdh!=null'>",
            "and bfr.lxdh like '%${lxdh}%' ",
            "</when>",
            " ORDER BY bfr.id desc",
            " ) tb ",
            " GROUP BY id",
            "</script>"})
    public List<BfrBqhHolder> list(@Param("name") String name, @Param("gzdw") String gzdw, @Param("lxdh") String lxdh);


    @Select("SELECT aa.*,bb.num " +
            "FROM  " +
            "( " +
            "SELECT t.id,t.pkhsx, d.`xm`,d.`zjhm` " +
            "FROM  " +
            "( " +
            "SELECT c.id,c.`pkhsx` " +
            "FROM  " +
            "ym_bfr a, ym_mid_bfr_jtjbqk b, ym_jtjbqk_jtjbxx c " +
            "WHERE a.id = b.`bid` AND b.`fid` = c.`id`  " +
            "AND a.id = #{bfrid} " +
            ") t LEFT JOIN ym_jtjbqk_jtcy d ON t.id = d.`fid` " +
            "WHERE d.yhzgx = '01' " +
            ") aa " +
            "LEFT JOIN " +
            "( " +
            "SELECT c.id,COUNT(1) num FROM " +
            "ym_bfr a, ym_mid_bfr_jtjbqk b,ym_jtjbqk_jtjbxx c, ym_jtjbqk_jtcy d " +
            "WHERE a.id = b.bid AND b.fid = c.id AND a.id = #{bfrid} AND d.`fid` = c.id GROUP BY id " +
            ") bb " +
            "ON aa.id = bb.id")
    List<BfgxXXHolder> getBBfrxx(@Param("bfrid")int bfrid);

    @Select("SELECT aa.*,bb.num\n" +
            "FROM \n" +
            "(\n" +
            "SELECT t.id,t.pkhsx, d.`xm`,d.`zjhm`\n" +
            "FROM \n" +
            "(\n" +
            "SELECT c.id,c.`pkhsx`\n" +
            "FROM \n" +
            "ym_jtjbqk_jtjbxx c\n" +
            "WHERE c.id = #{bqhid}\n" +
            ") t LEFT JOIN ym_jtjbqk_jtcy d ON t.id = d.`fid`\n" +
            "WHERE d.yhzgx = '01'\n" +
            ") aa\n" +
            "LEFT JOIN\n" +
            "(\n" +
            "SELECT c.id,COUNT(1) num FROM\n" +
            "ym_jtjbqk_jtjbxx c, ym_jtjbqk_jtcy d\n" +
            "WHERE c.id = #{bqhid} AND d.`fid` = c.id\n" +
            ") bb\n" +
            "ON aa.id = bb.id")
    BfgxXXHolder getBqh(@Param("bqhid")int id);

    /**
     * 关联方法
     * 指定帮扶人帮扶搬迁户
     *
     * @return
     */

    @Insert({
            "<script>",
            "INSERT INTO ym_mid_bfr_jtjbqk(bid,fid) VALUES ",
            "<foreach collection='fids' item='item' index='index' separator=','>",
            "(#{bid},#{item})",
            "</foreach>",
            "</script>"
    })
    public int zd(@Param(value = "bid")int bid, @Param(value = "fids")int[] fids);

    @Delete("delete from ym_mid_bfr_jtjbqk where bid = #{bid}")
    public int deleteZd(int bid);

}
