package com.easyai.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.easyai.bean.CommonResult;
import com.easyai.bean.User;
import com.easyai.bean.UserModel;
import com.easyai.service.UserModelService;
import com.easyai.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/usermodel")

public class cModelCreat {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    UserModelService userModelService;

    /**
     * 创建模型 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/creat")
    public String kmeans(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(500);
            result.setMsg("Error");
            JSONObject returnJson = new JSONObject();
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
                String lastEditTime = String.valueOf(System.currentTimeMillis());
                String creatTime = String.valueOf(System.currentTimeMillis());
                UserModel newUserModel = new UserModel(modelId, modelName, userId, dataJson, modelURL, lastEditTime, creatTime);
                int i = userModelService.InsertModel(newUserModel);
                result.setMsg("OK");
                result.setCode(200);
                result.setData(modelId);
                log.info("/cModelCreat执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/cUser/register执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

}
