package com.easyai.service.impl;

import com.easyai.bean.UserDataSet;
import com.easyai.bean.UserModel;
import com.easyai.mapper.UserDataSetMap;
import com.easyai.service.UserDataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataSetImpl implements UserDataSetService {
    @Autowired
    UserDataSetMap UserDataSetMap;

    @Override
    public List<UserDataSet> GetDataSetByUserId(String userId) {
        return UserDataSetMap.GetDataSetByUserId(userId);
    }

    @Override
    public List<UserDataSet> GetFavouriteDataSetByUserId(String userId) {
        return UserDataSetMap.GetFavouriteDataSetByUserId(userId);
    }

    @Override
    public UserDataSet GetDataSetByDataSetId(String dataId) {
        return UserDataSetMap.GetDataSetByDataSetId(dataId);
    }

    @Override
    public int InsertDataSet(UserDataSet dataSet) {
        return UserDataSetMap.InsertDataSet(dataSet);
    }

    @Override
    public int UpdateDataSet(UserDataSet dataSet) {
        return UserDataSetMap.UpdateDataSet(dataSet);
    }

    @Override
    public int DeleteDataSet(String dataId) {
        return UserDataSetMap.DeleteDataSet(dataId);
    }
}
