package com.easyai.service.impl;

import com.easyai.bean.TemplateModel;
import com.easyai.bean.UserModel;
import com.easyai.mapper.UserModelMapper;
import com.easyai.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserModelServiceImpl implements UserModelService {
    @Autowired
    UserModelMapper userModelMapper;

    @Override
    public List<UserModel> GetModelByUserId(String userId) {
        return userModelMapper.GetModelByUserId(userId);
    }

    @Override
    public UserModel GetModelByModelId(String modelId) {
        return userModelMapper.GetModelByModelId(modelId);
    }

    @Override
    public TemplateModel GetTemplateModelByName(String tModelName) {
        return userModelMapper.GetTemplateModelByName(tModelName);
    }

    @Override
    public int InsertModel(UserModel model) {
        return userModelMapper.InsertModel(model);
    }

    @Override
    public int UpdateModel(UserModel model) {
        return userModelMapper.UpdateModel(model);
    }

    @Override
    public int DeleteModel(String modelId) {
        return userModelMapper.DeleteModel(modelId);
    }

    @Override
    public List<UserModel> GetFavouriteModelByUserId(String userId) {
        return userModelMapper.GetFavouriteModelByUserId(userId);
    }

}
