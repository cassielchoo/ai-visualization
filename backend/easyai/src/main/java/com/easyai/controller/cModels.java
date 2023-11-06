package com.easyai.controller;

import com.alibaba.fastjson2.JSON;
import com.easyai.bean.CommonResult;
import com.easyai.model.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j

@RequestMapping("/model")
public class cModels {
    @Value("${server.port}")
    private String serverPort;

    /**
     * K_Means 接口
     * @param params
     * @return
     */
    @PostMapping("/kmeans")
    public String kmeans(@RequestBody Map<String, Object> params){
        CommonResult result = new CommonResult();
        try{
            if(params != null){
                int NUmClusters = Integer.parseInt(params.get("NUmClusters").toString().trim());
                MyDataset myDataset = new MyDataset("data\\flower.csv");

                result.setCode(200);
                result.setMsg("OK");
                JSONObject returnJson = RandomForest_K_means.K_means(myDataset.dataset,NUmClusters);
                result.setData(returnJson);
            }
        }catch (Exception e){

        }
        return JSON.toJSONString(result);
    }
}