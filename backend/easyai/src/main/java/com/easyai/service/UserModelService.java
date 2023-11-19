package com.easyai.service;

import com.easyai.bean.TemplateModel;
import com.easyai.bean.UserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserModelService {
    List<UserModel> GetModelByUserId(String userId);
    UserModel GetModelByModelId(String modelId);
    TemplateModel GetTemplateModelByName(String modelName);
    int InsertModel(UserModel model);
    int UpdateModel(UserModel model);
    int DeleteModel(String modelId);

}
