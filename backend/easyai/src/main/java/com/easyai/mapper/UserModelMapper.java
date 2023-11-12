package com.easyai.mapper;

import com.easyai.bean.User;
import com.easyai.bean.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserModelMapper {
    @Select("select * from dbModelInfo where userId = #{userId};")
    List<UserModel> GetModelByUserId(@Param("userId") String userId);
    @Select("select * from dbModelInfo where modelId = #{modelId};")
    UserModel GetModelByModelId(@Param("modelId") String modelId);
    @Insert("insert into dbModelInfo(modelId,modelName,userId,dataJson,modelURL,lastEditTime,creatTime) values (#{modelId},#{modelName},#{userId},#{dataJson},#{modelURL},#{lastEditTime},#{creatTime});")
    int InsertModel(UserModel model);


}
