package com.easyai.mapper;

import com.easyai.bean.TemplateModel;
import com.easyai.bean.User;
import com.easyai.bean.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserModelMapper {
    @Select("select modelId,modelName,lastEditTime,isFavourite,thumbnailUrl,isShared from dbModelInfo where userId = #{userId};")
    List<UserModel> GetModelByUserId(@Param("userId") String userId);
    @Select("select modelId,modelName,lastEditTime,isFavourite,thumbnailUrl,isShared from dbModelInfo where userId = #{userId} and isFavourite = 1;")
    List<UserModel> GetFavouriteModelByUserId(@Param("userId") String userId);

    @Select("select * from dbModelInfo where modelId = #{modelId};")
    UserModel GetModelByModelId(@Param("modelId") String modelId);

    @Insert("insert into dbModelInfo(modelId,modelName,userId,dataJson,modelURL,lastEditTime,creatTime,thumbnailUrl,isFavourite,isShared) values (#{modelId},#{modelName},#{userId},#{dataJson},#{modelURL},#{lastEditTime},#{creatTime},#{thumbnailUrl},#{isFavourite},#{isShared});")
    int InsertModel(UserModel model);

    @Select("select * from dbTemplateModel where tName = #{tModelName};")
    TemplateModel GetTemplateModelByName(@Param("tModelName")String tModelName);

    @Update("update dbModelInfo set modelName=#{modelName},userId=#{userId},dataJson=#{dataJson},modelURL=#{modelURL},lastEditTime=#{lastEditTime},creatTime=#{creatTime},thumbnailUrl=#{thumbnailUrl},isFavourite=#{isFavourite},isShared=#{isShared} where modelId = #{modelId}")
    int UpdateModel(UserModel model);

    @Delete("delete from dbModelInfo where modelId = #{modelId};")
    int DeleteModel(String modelId);
    @Select("select modelId,modelName,lastEditTime,isFavourite,thumbnailUrl,isShared from dbModelInfo where isShared = '1';")
    List<UserModel> GetSharedModel();
}
