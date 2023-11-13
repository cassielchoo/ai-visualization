package com.easyai.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.easyai.bean.CommonResult;
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
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String modelId = String.valueOf(UUID.randomUUID());
                String modelName = params.get("modelName").toString().trim();
                String userId = StpUtil.getLoginId().toString().trim();
                String dataJson = "";
                String modelURL = "";
                String thumbnailUrl = "";
                int isFavourite = 0;
                String lastEditTime = String.valueOf(System.currentTimeMillis());
                String creatTime = String.valueOf(System.currentTimeMillis());
                UserModel newUserModel = new UserModel(modelId, modelName, userId, dataJson, modelURL, lastEditTime, creatTime, thumbnailUrl, isFavourite);
                int i = userModelService.InsertModel(newUserModel);
                Map<String,String> resultMap = new HashMap<>();
                resultMap.put("modelId",modelId);
                result.setMsg("OK");
                result.setCode(200);
                result.setData(resultMap);
                log.info("/cModelCreat执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/cUser/register执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 创建模型 接口
     *
     * @return
     */
    @PostMapping("/getmodelbyuserid")
    public String getModelByUserId() {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
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
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format(new Date(Long.parseLong(String.valueOf(usermomdel.getLastEditTime()))));
                modelDetailJson.put("lastEditTime", sd);
                modelDetailJson.put("isFavourite", String.valueOf(usermomdel.getIsFavourite()));
                modelDetailJson.put("thumbnailUrl", usermomdel.getThumbnailUrl());
                returnMap.put("model" + index, modelDetailJson);
                index++;
            }
            result.setMsg("OK");
            result.setCode(200);
            result.setData(returnMap);
            log.info("/cModelCreat执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/cUser/register执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
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
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
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
                String modelURL = originModel.getModelURL();
                String thumbnailUrl = params.get("thumbnailUrl").toString().trim();;
                int isFavourite = originModel.getIsFavourite();
                String lastEditTime = String.valueOf(System.currentTimeMillis());
                String creatTime = originModel.getCreatTime();
                UserModel newUserModel = new UserModel(modelId, modelName, userId, dataJson, modelURL, lastEditTime, creatTime, thumbnailUrl, isFavourite);
                int i = userModelService.UpdateModel(newUserModel);
                Map<String,String> resultMap = new HashMap<>();
                result.setMsg("OK");
                result.setCode(200);
                log.info("/cModelCreat执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/cUser/register执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

}
