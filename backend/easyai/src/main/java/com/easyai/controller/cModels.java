package com.easyai.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
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

import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j

@RequestMapping("/model")
public class cModels {
    @Value("${server.port}")
    private String serverPort;

    /**
     * K_Means 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/Kmeans")
    public String kmeans(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                int NUmClusters = Integer.parseInt(params.get("NUmClusters").toString().trim());
                result.setCode(200);
                result.setMsg("OK");
                Map<String,Map> returnJson = RandomForest_K_means.K_means(NUmClusters);
                result.setData(returnJson);
                log.info("/model/Kmeans POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            log.error("/model/Kmeans POST 执行出现错误,error:{},现在时间:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * RandomForest 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/RandomForest")
    public String randomForest(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(403);
            result.setMsg("Access Denied");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                int NumTrees = Integer.parseInt(params.get("NumTrees").toString().trim());
                int depth = Integer.parseInt(params.get("depth").toString().trim());
                int NumSeed = Integer.parseInt(params.get("NumSeed").toString().trim());
                int NumAttribute = Integer.parseInt(params.get("NumAttribute").toString().trim());
                result.setCode(200);
                result.setMsg("OK");
                Map<String,Map> returnJson = RandomForest_K_means.RandomForest(NumTrees, depth, NumSeed, NumAttribute);
                result.setData(returnJson);
                log.info("/model/RandomForest POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            log.error("/model/RandomForest POST 执行出现错误,error:{},现在时间:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * Catboost 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/Catboost")
    public String catboost(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(403);
            result.setMsg("Access Denied");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                result.setCode(200);
                result.setMsg("OK");
                Map<String,Map> returnJson = Catboost.catboost();
                result.setData(returnJson);
                log.info("/model/Catboost POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            log.error("/model/Catboost POST 执行出现错误,error:{},现在时间:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * CNN 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/CNN")
    public String CNN(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(403);
            result.setMsg("Access Denied");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                double learningRate = Double.valueOf(params.get("NumTrees").toString().trim());
                int batchSize = Integer.parseInt(params.get("batchSize").toString().trim());
                int nEpochs = Integer.parseInt(params.get("nEpochs").toString().trim());
                int numHiddenNodes = Integer.parseInt(params.get("numHiddenNodes").toString().trim());
                result.setCode(200);
                result.setMsg("OK");
                Map<String,Map> returnJson = CNN.CNNmodel(learningRate, batchSize, nEpochs, numHiddenNodes);
                result.setData(returnJson);
                log.info("/model/CNN POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            log.error("/model/CNN POST 执行出现错误,error:{},现在时间:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * FullConnect 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/FullConnect")
    public String fullConnect(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(403);
            result.setMsg("Access Denied");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                double learningRate = Double.valueOf(params.get("learningRate").toString().trim());
                int batchSize = Integer.parseInt(params.get("batchSize").toString().trim());
                int nEpochs = Integer.parseInt(params.get("nEpochs").toString().trim());
                int numHiddenNodes = Integer.parseInt(params.get("numHiddenNodes").toString().trim());
                result.setCode(200);
                result.setMsg("OK");
                Map<String,Map> returnJson = FullConnect.FullConnect(learningRate, batchSize, nEpochs, numHiddenNodes);
                result.setData(returnJson);
                log.info("/model/FullConnect POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            log.error("/model/FullConnect POST 执行出现错误,error:{},现在时间:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * LightGBM 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/LightGBM")
    public String lightGBM(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(403);
            result.setMsg("Access Denied");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                int nEpochs = Integer.parseInt(params.get("nEpochs").toString().trim());
                result.setCode(200);
                result.setMsg("OK");
                Map<String,Map> returnJson = LightGBM.lightgbm(nEpochs);
                result.setData(returnJson);
                log.info("/model/LightGBM POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            log.error("/model/LightGBM POST 执行出现错误,error:{},现在时间:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }


    /**
     * Pred 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/Pred")
    public String pred(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(403);
            result.setMsg("Access Denied");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String modelType = params.get("modelType").toString().trim();
                if (modelType.equals("CNN")) {
                    String name = params.get("name").toString().trim();
                    result.setCode(200);
                    result.setMsg("OK");
                    Map<String,String> returnJson = new HashMap<>();
                    returnJson.put("result", String.valueOf(pred.get_num(name)));
                    result.setData(returnJson);
                    log.info("/model/Pred_CNN POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
                } else if (modelType.equals("RNN")) {
                    String name = params.get("name").toString().trim();
                    result.setCode(200);
                    result.setMsg("OK");
                    Map<String,String> returnJson = new HashMap<>();
                    returnJson.put("result", String.valueOf(pred.get_num(name)));
                    result.setData(returnJson);
                    log.info("/model/Pred_RNN POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
                } else if (modelType.equals("randomforest") || modelType.equals("catboost") || modelType.equals("fullconnect") || modelType.equals("lightgbm") || modelType.equals("xgboost")) {
                    double sepal_length = Double.parseDouble(params.get("sepal_length").toString().trim());
                    double sepal_width = Double.parseDouble(params.get("sepal_width").toString().trim());
                    double petal_length = Double.parseDouble(params.get("petal_length").toString().trim());
                    double petal_width = Double.parseDouble(params.get("petal_width").toString().trim());
                    result.setCode(200);
                    result.setMsg("OK");
                    Map<String,String> returnJson = new HashMap<>();
                    returnJson.put("result", String.valueOf(pred.get_flowerclass(sepal_length, sepal_width, petal_length, petal_width)));
                    result.setData(returnJson);
                    log.info("/model/Pred_Others POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
                } else {
                    result.setCode(400);
                    result.setMsg("Model Not Found");
                    Map<String,String> returnJson = new HashMap<>();
                    returnJson.put("Error", "Model" + modelType + "Not Found");
                    result.setData(returnJson);
                    log.error("/model/Pred POST 执行出现错误,error:{},现在时间:{},port:{}", "Model Not Found", DateUtil.now(), serverPort);
                }

            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            log.error("/model/LinearDataClassifier POST 执行出现错误,error:{},现在时间:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * RNN 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/RNN")
    public String RNN(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(403);
            result.setMsg("Access Denied");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                int nEpochs = Integer.parseInt(params.get("nEpochs").toString().trim());
                result.setCode(200);
                result.setMsg("OK");
                Map<String,Map> returnJson = RNN.rnn(nEpochs);
                result.setData(returnJson);
                log.info("/model/RNN POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            log.error("/model/RNN POST 执行出现错误,error:{},现在时间:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * Xgboost 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/Xgboost")
    public String Xgboost(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try{
            StpUtil.checkLogin();
        }catch (Exception e){
            result.setCode(403);
            result.setMsg("Access Denied");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                double eta = Double.parseDouble(params.get("eta").toString().trim());
                int depth = Integer.parseInt(params.get("depth").toString().trim());
                int nEpoch = Integer.parseInt(params.get("nEpochs").toString().trim());
                result.setCode(200);
                result.setMsg("OK");
                Map<String,Map> returnJson = Xgboost.xgboost(eta, depth, nEpoch);
                result.setData(returnJson);
                log.info("/model/Xgboost POST 执行完成,现在时间:{},port:{}", DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("Error");
            Map<String,String> returnJson = new HashMap<>();
            returnJson.put("Error", e.getMessage());
            result.setData(returnJson);
            log.error("/model/Xgboost POST 执行出现错误,error:{},现在时间:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

}