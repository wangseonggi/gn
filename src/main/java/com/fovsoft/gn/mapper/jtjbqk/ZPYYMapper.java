package com.fovsoft.gn.mapper.jtjbqk;

import com.fovsoft.gn.entity.ZpyyDO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ZPYYMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ym_jtjbqk_zpyy(fid,zpyy1,zpyy2,zpyy3,ncjtrks,nmjtrks) VALUES (#{fid},#{zpyy1},#{zpyy2},#{zpyy3},#{ncjtrks},#{nmjtrks})")
    int add(ZpyyDO zpyyDO);

    @Update("UPDATE ym_jtjbqk_zpyy SET zpyy1=#{zpyy1},zpyy2=#{zpyy2},zpyy3=#{zpyy3},ncjtrks=#{ncjtrks},nmjtrks=#{nmjtrks} WHERE fid = #{fid}")
    int update(ZpyyDO zpyyDO);

    @Select("SELECT zpyy1,zpyy2,zpyy3,ncjtrks,nmjtrks FROM ym_jtjbqk_zpyy WHERE fid = #{fid}")
    ZpyyDO get(int fid);
}




