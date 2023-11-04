package com.easyai.mapper;

import com.easyai.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from dbUser where userId = #{userId};")
    List<User> GetUserByUserId(@Param("userId") String userId);
    @Insert("insert into dbUser(userId,userName,userPasshash,userPhoto,userPhone) values (#{userId},#{userName},#{userPasshash},#{userPhoto},#{userPhone});")
    int InsertUser(User user);
}
