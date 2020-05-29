package com.fovsoft.gn.mapper.jtjbqk;

import com.fovsoft.gn.entity.JtcyDO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 家庭成员mapper
 */
@Mapper
public interface JTCYMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ym_jtjbqk_jtcy(" +
            "fid,xm,xb,zjhm,yhzgx,mz,zzmm,whcd,zxsqk,sxhcxyy,jkzk,ldjn,sfhjpth,sfcjcxjmjbytlbx,sfcjsybcylbx,sfxsncjmzjshbz,sfcjcxjmybyanglbx,sfxsrsywbxbt,lxdh," +
            "shzt ,sgny ,rklx ,zdqk ,zxjdxx ,cjzh ,cjdj ,djbhdmxbk ,mxbsm ,dbjz ,sfxszcdd ,jyyy ,jyqk ,jycydw ,wdjycydy ,ylbxlx) " +
            "VALUES (#{fid},#{xm},#{xb},#{zjhm},#{yhzgx},#{mz},#{zzmm},#{whcd},#{zxsqk},#{sxhcxyy},#{jkzk},#{ldjn},#{sfhjpth},#{sfcjcxjmjbytlbx},#{sfcjsybcylbx},#{sfxsncjmzjshbz},#{sfcjcxjmybyanglbx},#{sfxsrsywbxbt},#{lxdh}" +
            ",#{shzt},#{sgny},#{rklx},#{zdqk},#{zxjdxx},#{cjzh},#{cjdj},#{djbhdmxbk},#{mxbsm},#{dbjz},#{sfxszcdd},#{jyyy},#{jyqk},#{jycydw},#{wdjycydy},#{ylbxlx})"
    )
    int add(JtcyDO jtcyDO);

    @Update("UPDATE ym_jtjbqk_jtcy SET scbz = 1 WHERE id = #{id}")
    int delete(int id);

    @Select("SELECT id,fid,xm,xb,zjhm,yhzgx,mz,zzmm,whcd,zxsqk,sxhcxyy,jkzk,ldjn,sfhjpth,sfcjcxjmjbytlbx,sfcjsybcylbx,sfxsncjmzjshbz,sfcjcxjmybyanglbx,sfxsrsywbxbt,lxdh" +
            ",shzt,sgny,rklx,zdqk,zxjdxx,cjzh,cjdj,djbhdmxbk,mxbsm,dbjz,sfxszcdd,jyyy,jyqk,jycydw,wdjycydy,ylbxlx" +
            " FROM ym_jtjbqk_jtcy WHERE scbz = 'N' and id = #{id}")
    JtcyDO get(int id);


    @Select("SELECT " +
            "  id," +
            "  fid," +
            "  xm," +
            "  (SELECT `name` FROM dm_ym_xb xb WHERE xb.dm = xb) xb," +
            "  zjhm, " +
            "  (SELECT `name` FROM dm_ym_yhzgx a WHERE a.dm = yhzgx) yhzgx, " +
            "  (SELECT `name` FROM dm_ym_mz a WHERE a.dm = mz) mz,  " +
            "  (SELECT `name` FROM dm_ym_zzmm a WHERE a.dm = zzmm) zzmm, " +
            "  (SELECT `name` FROM dm_ym_whcd a WHERE a.dm = whcd) whcd, " +
            "  (SELECT `name` FROM dm_ym_zxsqk a WHERE a.dm = zxsqk) zxsqk, " +
            "  (SELECT `name` FROM dm_ym_sxhcxyy a WHERE a.dm = sxhcxyy) sxhcxyy, " +
            "  (SELECT `name` FROM dm_ym_jkzk a WHERE a.dm = jkzk) jkzk, " +
            "  (SELECT `name` FROM dm_ym_ldjn a WHERE a.dm = ldjn) ldjn, " +
            "  sfhjpth, " +
            "  sfcjcxjmjbytlbx, " +
            "  sfcjsybcylbx, " +
            "  sfxsncjmzjshbz, " +
            "  sfcjcxjmybyanglbx, " +
            "  sfxsrsywbxbt, " +
            "  lxdh  " +
            "FROM " +
            "  ym_jtjbqk_jtcy  " +
            "WHERE scbz = 'N'  " +
            "  AND fid = #{fid}"
    )
    List<JtcyDO> list(int fid);

    @Update("UPDATE ym_jtjbqk_jtcy SET xm=#{xm},xb=#{xb},zjhm=#{zjhm},yhzgx=#{yhzgx},mz=#{mz},zzmm=#{zzmm},whcd=#{whcd},zxsqk=#{zxsqk},sxhcxyy=#{sxhcxyy},jkzk=#{jkzk},ldjn=#{ldjn},sfhjpth=#{sfhjpth},sfcjcxjmjbytlbx=#{sfcjcxjmjbytlbx},sfcjsybcylbx=#{sfcjsybcylbx},sfxsncjmzjshbz=#{sfxsncjmzjshbz},sfcjcxjmybyanglbx=#{sfcjcxjmybyanglbx},sfxsrsywbxbt=#{sfxsrsywbxbt},lxdh=#{lxdh}" +
            ",shzt=#{shzt},sgny=#{sgny},rklx=#{rklx},zdqk=#{zdqk},zxjdxx=#{zxjdxx},cjzh=#{cjzh},cjdj=#{cjdj},djbhdmxbk=#{djbhdmxbk},mxbsm=#{mxbsm},dbjz=#{dbjz},sfxszcdd=#{sfxszcdd},jyyy=#{jyyy},jyqk=#{jyqk},jycydw=#{jycydw},wdjycydy=#{wdjycydy},ylbxlx=#{ylbxlx}" +
            " WHERE id = #{id}")
    int update(JtcyDO jtcyDO);
}


