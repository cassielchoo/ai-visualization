package com.easyai.mapper;

import com.easyai.bean.UserDataSet;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserDataSetMap {
    @Select("select dataId,dataName,isFavourite,isShared,dataDescribe from dbData where userId = #{userId};")
    List<UserDataSet> GetDataSetByUserId(String userId);
    @Select("select dataId,dataName,isFavourite,isShared,dataDescribe from dbData where userId = #{userId} and isFavourite = 1;")
    List<UserDataSet> GetFavouriteDataSetByUserId(String userId);
    @Select("select * from dbData where dataId = #{dataId};")
    UserDataSet GetDataSetByDataSetId(String dataId);
    @Insert("insert into dbData(dataId,dataName,userId,dataURL,dataDescribe,isFavourite,isShared) values (#{dataId},#{dataName},#{userId},#{dataURL},#{dataDescribe},#{isFavourite},#{isShared});")
    int InsertDataSet(UserDataSet dataSet);
    @Update("update dbData set dataId=#{dataId},dataName=#{dataName},userId=#{userId},dataURL=#{dataURL},dataDescribe=#{dataDescribe},isFavourite=#{isFavourite},isShared=#{isShared} where dataId = #{dataId}")
    int UpdateDataSet(UserDataSet dataSet);
    @Delete("delete from dbData where dataId = #{dataId};")
    int DeleteDataSet(String dataId);
    @Select("select dataId,dataName,isFavourite,isShared,dataDescribe from dbData where isShared = '1'")
    List<UserDataSet> GetAllSharedDataSet();
}
