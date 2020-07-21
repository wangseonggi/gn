package com.fovsoft.gn.mapper.photo;

import com.fovsoft.gn.entity.YmPhotoDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: by tpc
 * @date: 2020/7/21 15:54
 * @description: 影像资料图片dao类
 **/

@Mapper
public interface YmPhotoMapper {


    @Select("select * from ym_photo where fid=#{familyId} ")
    YmPhotoDo getHouseholderPhoto(Integer familyId);
}
