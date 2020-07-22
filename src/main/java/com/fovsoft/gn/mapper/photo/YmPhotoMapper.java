package com.fovsoft.gn.mapper.photo;

import com.fovsoft.gn.entity.YmPhotoDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: by tpc
 * @date: 2020/7/21 15:54
 * @description: 影像资料图片dao类
 **/

@Mapper
public interface YmPhotoMapper {


    @Select("select * from ym_photo where fid=#{familyId} ")
    List<YmPhotoDo> getHouseholderPhoto(Integer familyId);


    @Select("select count(*) from ym_photo where fid=#{familyId} ")
    int getHouseholderPhotoCount(Integer familyId);


    @Select("select * from ym_photo where fid=#{familyId} order by sequence desc limit 1")
    YmPhotoDo getHouseholderPhotoMax(Integer familyId);


    @Insert("insert into ym_photo(fid,photo1,photo2,sequence,gxsj) values(#{fid},#{photo1},#{photo2},#{sequence},#{gxsj}) ")
    int addHouseHolderPhoto(YmPhotoDo ymPhotoDo);

    @Delete("delete from ym_photo where id=#{id} ")
    int delHouseholderPhotoById(Integer id);
}
