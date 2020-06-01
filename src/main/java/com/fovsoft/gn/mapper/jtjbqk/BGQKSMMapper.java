package com.fovsoft.gn.mapper.jtjbqk;

import com.fovsoft.gn.entity.BgqksmDO;
import org.apache.ibatis.annotations.*;

/**
 * 变更情况说明mapper
 */
@Mapper
public interface BGQKSMMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ym_jtjbqk_bgqksm (fid,nr,lrrq) VALUES (#{fid},#{nr},#{lrrq})")
    int add(BgqksmDO bgqksmDO);

    @Update("UPDATE ym_jtjbqk_bgqksm SET nr=#{nr},lrrq=#{lrrq} WHERE id=#{id}")
    int update(BgqksmDO bgqksmDO);

    @Select("SELECT id,nr FROM ym_jtjbqk_bgqksm where fid = #{fid}")
    BgqksmDO get(Integer fid);
}
