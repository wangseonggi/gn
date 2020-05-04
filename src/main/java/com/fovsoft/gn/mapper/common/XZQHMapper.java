package com.fovsoft.gn.mapper.common;

import com.fovsoft.gn.entity.XzqhDO;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 行政区划mapper
 */
@Mapper
public interface XZQHMapper {

    /**
     * 默认取钦州下辖行政区划
     *
     */
    @Select("<script>" +
            "select xzqhmz,xzqhdm from ty_xzqh where 1=1 " +
            "<if test=\"sjxzqhdm != null\"> and sjxzqhdm = #{sjxzqhdm}</if>" +
            "<if test=\"sjxzqhdm == null\"> and sjxzqhdm = '450100000000'</if>" +
            "</script>")
    List<XzqhDO> get(String sjxzqhdm);
}

