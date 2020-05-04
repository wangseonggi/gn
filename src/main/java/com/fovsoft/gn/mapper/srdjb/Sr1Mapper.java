package com.fovsoft.gn.mapper.srdjb;

import com.fovsoft.gn.entity.Sr1DO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 生产经营性收支mapper
 */
@Mapper
public interface Sr1Mapper {

    @Insert({
            "<script>",
            "INSERT INTO ym_srdjb_sr1(fid ,lb1 ,lb2 ,lb3 ,nf ,item1 ,item2 ,item3 ,item4 ,item5 ,item6 ,item7 ,item8 ,item9 ,item10 ,item11 ,item12) VALUES ",
            "<foreach collection='param' item='item' index='index' separator=','>",
            "(#{item.fid},#{item.lb1},#{item.lb2},#{item.lb3},#{item.nf},#{item.item1},#{item.item2},#{item.item3},#{item.item4},#{item.item5},#{item.item6},#{item.item7},#{item.item8},#{item.item9},#{item.item10},#{item.item11},#{item.item12})",
            "</foreach>",
            "</script>"
    })
    int add(@Param(value = "param") List param);

    @Delete("DELETE FROM ym_srdjb_sr1 WHERE fid = #{fid} AND nf = #{nf}")
    int delete(@Param("fid") int fid, @Param("nf")String nf);

    @Select("SELECT * FROM ym_srdjb_sr1 WHERE fid = #{fid} AND nf = #{nf}")
    List<Sr1DO> get(Map map);

}
