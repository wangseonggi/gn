package com.fovsoft.gn.mapper.jtjbqk;

import com.fovsoft.gn.entity.ScshtjDO;
import com.fovsoft.gn.entity.ZpyyDO;
import org.apache.ibatis.annotations.*;

/**
 * 生产生活条件mapper
 */
@Mapper
public interface SCSHTJMapper {

    @Insert("INSERT INTO ym_jtjbqk_scshtj (fid,gdmj,ldmj,tghlmj,lgmj,mcdmj,smmj,sfjrnmzyhzs,sfyltqybz,sfycyzfdtrdd,sftshyd,sftgbds,sfjjaqyys,sfywscs,yczgljl,rhllx,zfmj,sfwf,wfdj,wgnd,zyrllx,jtrlmc,lrrq,gxrq,scbz) " +
    "VALUES (#{fid},#{gdmj},#{ldmj},#{tghlmj},#{lgmj},#{mcdmj},#{smmj},#{sfjrnmzyhzs},#{sfyltqybz},#{sfycyzfdtrdd},#{sftshyd},#{sftgbds},#{sfjjaqyys},#{sfywscs},#{yczgljl},#{rhllx},#{zfmj},#{sfwf},#{wfdj},#{wgnd},#{zyrllx},#{jtrlmc},#{lrrq},#{gxrq},#{scbz})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(ScshtjDO scshtjDO);

    @Update("UPDATE ym_jtjbqk_scshtj SET scbz = 1 WHERE id = #{id};")
    int delete(int id);

    @Update("UPDATE ym_jtjbqk_scshtj set gdmj=#{gdmj},ldmj=#{ldmj},tghlmj=#{tghlmj},lgmj=#{lgmj},mcdmj=#{mcdmj},smmj=#{smmj},sfjrnmzyhzs=#{sfjrnmzyhzs},sfyltqybz=#{sfyltqybz},sfycyzfdtrdd=#{sfycyzfdtrdd},sftshyd=#{sftshyd},sftgbds=#{sftgbds},sfjjaqyys=#{sfjjaqyys},sfywscs=#{sfywscs},yczgljl=#{yczgljl},rhllx=#{rhllx},zfmj=#{zfmj},sfwf=#{sfwf},wfdj=#{wfdj},wgnd=#{wgnd},zyrllx=#{zyrllx},jtrlmc=#{jtrlmc},lrrq=#{lrrq},gxrq=#{gxrq},scbz=#{scbz} " +
    "WHERE id = #{id}"
    )
    int update(ScshtjDO scshtjDO);

    @Select("SELECT id,fid,gdmj,ldmj,tghlmj,lgmj,mcdmj,smmj,sfjrnmzyhzs,sfyltqybz,sfycyzfdtrdd,sftshyd,sftgbds,sfjjaqyys,sfywscs,yczgljl,rhllx,zfmj,sfwf,wfdj,wgnd,zyrllx,jtrlmc FROM ym_jtjbqk_scshtj WHERE fid = #{fid}")
    ScshtjDO get(int fid);
}



