package com.easyai.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.easyai.bean.CommonResult;
import com.easyai.bean.TemplateModel;
import com.easyai.bean.UserDataSet;
import com.easyai.bean.UserModel;
import com.easyai.service.UserModelService;
import com.easyai.service.UserService;
import com.easyai.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/usermodel")

public class cUserModel {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    UserModelService userModelService;
    @Autowired
    UserService userService;

    /**
     * 创建模型 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/creat")
    public String creat(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String modelId = String.valueOf(UUID.randomUUID());
                String modelName = params.get("modelName").toString().trim();
                String userId = StpUtil.getLoginId().toString().trim();
                String dataJson = "";
                if (params.containsKey("templateModelName")){
                    String templateModelName = params.get("templateModelName").toString().trim();
                    TemplateModel tmodel = userModelService.GetTemplateModelByName(templateModelName);
                    dataJson = tmodel.getTDataJson();
                }
                String modelURL = "";
                String thumbnailUrl = "";
                String isFavourite = "0";
                String isShared = "0";
                String lastEditTime = String.valueOf(System.currentTimeMillis());
                String creatTime = String.valueOf(System.currentTimeMillis());
                UserModel newUserModel = new UserModel(modelId, modelName, userId, dataJson, modelURL, lastEditTime, creatTime, thumbnailUrl, isFavourite,isShared);
                int i = userModelService.InsertModel(newUserModel);
                Map<String,String> resultMap = new HashMap<>();
                resultMap.put("modelId",modelId);
                resultMap.put("lastEditTime",lastEditTime);
                resultMap.put("isFavourite", "0");
                result.setMsg("OK");
                result.setCode(200);
                result.setData(resultMap);
                log.info("/usermodel/creat执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/creat执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }


    /**
     * 获取指定用户模型 接口
     *
     * @return
     */
    @PostMapping("/getmodelbyuserid")
    public String getModelByUserId() {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {

            String userId = StpUtil.getLoginId().toString().trim();
            String userName = userService.GetUserByUserId(userId).get(0).getUserName();
            Map<String,Map> returnMap = new HashMap<>();
            Map<String,String> modelDetailJson;
            List<UserModel> modelList = userModelService.GetModelByUserId(userId);
            int index = 0;
            for (UserModel usermomdel : modelList) {
                modelDetailJson = new HashMap<>() ;
                modelDetailJson.put("UserName", userName);
                modelDetailJson.put("modelId", usermomdel.getModelId());
                modelDetailJson.put("modelName", usermomdel.getModelName());
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format(new Date(Long.parseLong(String.valueOf(usermomdel.getLastEditTime()))));
                modelDetailJson.put("lastEditTime", sd);
                modelDetailJson.put("isFavourite", String.valueOf(usermomdel.getIsFavourite()));
                modelDetailJson.put("thumbnailUrl", usermomdel.getThumbnailUrl());
                modelDetailJson.put("isShared", String.valueOf(usermomdel.getIsShared()));
                returnMap.put("model" + index, modelDetailJson);
                index++;
            }
            result.setMsg("OK");
            result.setCode(200);
            result.setData(returnMap);
            log.info("/usermodel/getmodelbyuserid执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/getmodelbyuserid执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }

        return JSON.toJSONString(result);
    }

    /**
     * 获取指定用户Fovourite模型 接口
     *
     * @return
     */
    @PostMapping("/getfavouritemodelbyuserid")
    public String getFavouriteModelByUserId() {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {

            String userId = StpUtil.getLoginId().toString().trim();
            String userName = userService.GetUserByUserId(userId).get(0).getUserName();
            Map<String,Map> returnMap = new HashMap<>();
            Map<String,String> modelDetailJson;
            List<UserModel> modelList = userModelService.GetFavouriteModelByUserId(userId);
            int index = 0;
            for (UserModel usermomdel : modelList) {
                modelDetailJson = new HashMap<>() ;
                modelDetailJson.put("UserName", userName);
                modelDetailJson.put("modelId", usermomdel.getModelId());
                modelDetailJson.put("modelName", usermomdel.getModelName());
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format(new Date(Long.parseLong(String.valueOf(usermomdel.getLastEditTime()))));
                modelDetailJson.put("lastEditTime", sd);
                modelDetailJson.put("isFavourite", String.valueOf(usermomdel.getIsFavourite()));
                modelDetailJson.put("thumbnailUrl", usermomdel.getThumbnailUrl());
                modelDetailJson.put("isShared", String.valueOf(usermomdel.getIsShared()));
                returnMap.put("model" + index, modelDetailJson);
                index++;
            }
            result.setMsg("OK");
            result.setCode(200);
            result.setData(returnMap);
            log.info("/usermodel/getmodelbyuserid执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/getmodelbyuserid执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }

        return JSON.toJSONString(result);
    }

    /**
     * 获取指定模型 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/getmodelbymodelid")
    public String getModelByModelId(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            String userId = StpUtil.getLoginId().toString().trim();
            String modelId = params.get("modelId").toString().trim();
            UserModel theModel = userModelService.GetModelByModelId(modelId);
            result.setMsg("OK");
            result.setCode(200);
            result.setData(theModel);
            log.info("/usermodel/getmodelbymodelid执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/getmodelbymodelid执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 删除指定模型 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/delete")
    public String deleteModel(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            String userId = StpUtil.getLoginId().toString().trim();
            String modelId = params.get("modelId").toString().trim();
            int i = userModelService.DeleteModel(modelId);
            result.setMsg("OK");
            result.setCode(200);
            log.info("/usermodel/delete执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/delete执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 获取所有已分享模型 接口
     *
     * @return
     */
    @PostMapping("/getallsharedmodel")
    public String getAllSharedModel() {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            Map<String,Map> returnMap = new HashMap<>();
            Map<String,String> modelDetailJson;
            List<UserModel> modelList = userModelService.GetSharedModel();
            int index = 0;
            for (UserModel usermomdel : modelList) {
                modelDetailJson = new HashMap<>() ;
                String userId = usermomdel.getUserId();
                String userName = userService.GetUserByUserId(userId).get(0).getUserName();
                modelDetailJson.put("UserName", userName);
                String userPhoto = userService.GetUserByUserId(userId).get(0).getUserPhoto();
                modelDetailJson.put("UserName", userName);
                modelDetailJson.put("UserPhoto", userPhoto);
                modelDetailJson.put("modelId", usermomdel.getModelId());
                modelDetailJson.put("modelName", usermomdel.getModelName());
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format(new Date(Long.parseLong(String.valueOf(usermomdel.getLastEditTime()))));
                modelDetailJson.put("lastEditTime", sd);
                modelDetailJson.put("isFavourite", String.valueOf(usermomdel.getIsFavourite()));
                modelDetailJson.put("thumbnailUrl", usermomdel.getThumbnailUrl());
                modelDetailJson.put("isShared", String.valueOf(usermomdel.getIsShared()));
                returnMap.put("model" + index, modelDetailJson);
                index++;
            }
            result.setMsg("OK");
            result.setCode(200);
            result.setData(returnMap);
            String userId = StpUtil.getLoginId().toString().trim();
            log.info("/usermodel/getallsharedmodel执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/getallsharedmodel执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }

        return JSON.toJSONString(result);
    }



    /**
     * 设置Favourite 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/setfavourite")
    public String setFavourite(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String modelId = params.get("modelId").toString().trim();
                UserModel originModel = userModelService.GetModelByModelId(modelId);
                if(!originModel.getUserId().equals(StpUtil.getLoginId().toString().trim())){
                    result.setCode(500);
                    result.setMsg("Error");
                    Map<String,String> returnJson = new HashMap<>();
                    returnJson.put("Error", "Not Your Model!");
                    result.setData(returnJson);
                    return JSON.toJSONString(result);
                }
                String userId = originModel.getUserId();
                String isFavourite = params.get("isFavourite").toString().trim();
                String lastEditTime = String.valueOf(System.currentTimeMillis());
                UserModel newUserModel = originModel;
                newUserModel.setIsFavourite(isFavourite);
                newUserModel.setLastEditTime(lastEditTime);
                int i = userModelService.UpdateModel(newUserModel);
                result.setMsg("OK");
                result.setCode(200);
                log.info("/usermodel/setfavourite执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/setfavourite执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 设置Sare接口
     *
     * @param params
     * @return
     */
    @PostMapping("/setshare")
    public String setShare(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String modelId = params.get("modelId").toString().trim();
                UserModel originModel = userModelService.GetModelByModelId(modelId);
                String modelName = originModel.getModelName();
                if(!originModel.getUserId().equals(StpUtil.getLoginId().toString().trim())){
                    result.setCode(500);
                    result.setMsg("Error");
                    Map<String,String> returnJson = new HashMap<>();
                    returnJson.put("Error", "Not Your Model!");
                    result.setData(returnJson);
                    return JSON.toJSONString(result);
                }
                String userId = originModel.getUserId();
                String isShared = params.get("isShared").toString().trim();
                String lastEditTime = String.valueOf(System.currentTimeMillis());
                UserModel newUserModel = originModel;
                newUserModel.setIsShared(isShared);
                newUserModel.setLastEditTime(lastEditTime);
                int i = userModelService.UpdateModel(newUserModel);
                Map<String,String> resultMap = new HashMap<>();
                result.setMsg("OK");
                result.setCode(200);
                log.info("/usermodel/setshare执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/setshare执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }


    /**
     * 保存模型 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/save")
    public String saveModel(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String modelId = params.get("modelId").toString().trim();
                UserModel originModel = userModelService.GetModelByModelId(modelId);
                String modelName = originModel.getModelName();
                if(!originModel.getUserId().equals(StpUtil.getLoginId().toString().trim())){
                    result.setCode(500);
                    result.setMsg("Error");
                    Map<String,String> returnJson = new HashMap<>();
                    returnJson.put("Error", "Not Your Model!");
                    result.setData(returnJson);
                    return JSON.toJSONString(result);
                }
                String userId = originModel.getUserId();
                String dataJson = params.get("datajson").toString().trim();
                String thumbnailUrl = params.get("thumbnailUrl").toString().trim();;
                UserModel newUserModel = originModel;
                newUserModel.setDataJson(dataJson);
                newUserModel.setThumbnailUrl(thumbnailUrl);
                int i = userModelService.UpdateModel(newUserModel);
                result.setMsg("OK");
                result.setCode(200);
                log.info("/usermodel/save执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/save执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 复制模型 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/copymodelbyid")
    public String copyModelById(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String,String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String userId = StpUtil.getLoginId().toString().trim();
                String orginModelId = params.get("modelId").toString().trim();
                String modelId = String.valueOf(UUID.randomUUID());
                String lastEditTime = String.valueOf(System.currentTimeMillis());
                String creatTime = String.valueOf(System.currentTimeMillis());
                UserModel newUserModel = userModelService.GetModelByModelId(orginModelId);
                newUserModel.setModelId(modelId);
                newUserModel.setUserId(userId);
                newUserModel.setCreatTime(creatTime);
                newUserModel.setLastEditTime(lastEditTime);
                newUserModel.setIsFavourite("0");
                newUserModel.setIsShared("0");
                int i = userModelService.InsertModel(newUserModel);
                Map<String,String> resultMap = new HashMap<>();
                resultMap.put("modelId",modelId);
                resultMap.put("lastEditTime",lastEditTime);
                resultMap.put("isFavourite", "0");
                result.setMsg("OK");
                result.setCode(200);
                result.setData(resultMap);
                log.info("/usermodel/copymodelbyid执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/usermodel/copymodelbyid执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

}
