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
    @Insert("INSERT INTO ym_jtjbqk_jtcy(fid,xm,xb,zjhm,yhzgx,mz,zzmm,whcd,zxszk,sxyy,jkzk,ldjn,sfhjpth,sfcjcxjmjbytlbx,sfcjsybcylbx,sfxsncjmzjshbz,sfcjcxjmybyanglbx,sfxsrsywbxbt,lxdh) " +
    "VALUES (#{fid},#{xm},#{xb},#{zjhm},#{yhzgx},#{mz},#{zzmm},#{whcd},#{zxszk},#{sxyy},#{jkzk},#{ldjn},#{sfhjpth},#{sfcjcxjmjbytlbx},#{sfcjsybcylbx},#{sfxsncjmzjshbz},#{sfcjcxjmybyanglbx},#{sfxsrsywbxbt},#{lxdh})"
    )
    int add(JtcyDO jtcyDO);

    @Update("UPDATE ym_jtjbqk_jtcy SET scbz = 1 WHERE id = #{id}")
    int delete(int id);

    @Select("SELECT id,fid,xm,xb,zjhm,yhzgx,mz,zzmm,whcd,zxszk,sxyy,jkzk,ldjn,sfhjpth,sfcjcxjmjbytlbx,sfcjsybcylbx,sfxsncjmzjshbz,sfcjcxjmybyanglbx,sfxsrsywbxbt,lxdh FROM ym_jtjbqk_jtcy WHERE scbz = 0 and id = #{id}")
    JtcyDO get(int id);

    @Select("SELECT id,fid,xm,xb,zjhm,yhzgx,mz,zzmm,whcd,zxszk,sxyy,jkzk,ldjn,sfhjpth,sfcjcxjmjbytlbx,sfcjsybcylbx,sfxsncjmzjshbz,sfcjcxjmybyanglbx,sfxsrsywbxbt,lxdh " +
    "FROM ym_jtjbqk_jtcy WHERE scbz = 0 and fid = #{fid}"
    )
    List<JtcyDO> list(int fid);

    @Update("UPDATE ym_jtjbqk_jtcy SET xm=#{xm},xb=#{xb},zjhm=#{zjhm},yhzgx=#{yhzgx},mz=#{mz},zzmm=#{zzmm},whcd=#{whcd},zxszk=#{zxszk},sxyy=#{sxyy},jkzk=#{jkzk},ldjn=#{ldjn},sfhjpth=#{sfhjpth},sfcjcxjmjbytlbx=#{sfcjcxjmjbytlbx},sfcjsybcylbx=#{sfcjsybcylbx},sfxsncjmzjshbz=#{sfxsncjmzjshbz},sfcjcxjmybyanglbx=#{sfcjcxjmybyanglbx},sfxsrsywbxbt=#{sfxsrsywbxbt},lxdh=#{lxdh} WHERE id = #{id}")
    int update(JtcyDO jtcyDO);
}

