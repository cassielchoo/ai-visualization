package com.easyai.mapper.handler;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class ListHandler extends BaseTypeHandler<List<Object>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Object> o, JdbcType jdbcType) throws SQLException {
        String tStr = JSONArray.toJSONString(o);
        preparedStatement.setString(i,tStr);
    }

    @Override
    public List<Object> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String tStr = resultSet.getString(s);
        if(tStr == null)return null;
        return JSONArray.parse(tStr).toList(Object.class);
    }

    @Override
    public List<Object> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String tStr = resultSet.getString(i);
        if(tStr == null)return null;
        return JSONArray.parse(tStr).toList(Object.class);
    }

    @Override
    public List<Object> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String tStr = callableStatement.getString(i);
        if(tStr == null)return null;
        return JSONArray.parse(tStr).toList(Object.class);
    }
}
