package com.easyai.service;

import com.easyai.bean.UserDataSet;
import com.easyai.bean.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDataSetService {
    List<UserDataSet> GetDataSetByUserId(String userId);

    List<UserDataSet> GetFavouriteDataSetByUserId(String userId);

    UserDataSet GetDataSetByDataSetId(String dataId);

    int InsertDataSet(UserDataSet dataSet);

    int UpdateDataSet(UserDataSet dataSet);

    int DeleteDataSet(String dataId);
}
