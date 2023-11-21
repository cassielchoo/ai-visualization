package com.easyai.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.easyai.bean.CommonResult;
import com.easyai.bean.UserDataSet;
import com.easyai.service.UserDataSetService;
import com.easyai.service.UserService;
import com.easyai.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j

@RequestMapping("/dataset")
public class cUserDataSet {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    UserDataSetService userDataSetService;
    @Autowired
    UserService userService;


    /**
     * 创建数据集 接口
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
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String dataId = String.valueOf(UUID.randomUUID());
                String dataName = params.get("dataName").toString().trim();
                String userId = StpUtil.getLoginId().toString().trim();
                String dataURL = params.get("dataURL").toString().trim();
                String dataDescribe = params.get("dataDescribe").toString().trim();
                String isShared = "0";
                String isFavourite = "0";
                UserDataSet newDataSet = new UserDataSet(dataId, dataName, userId, dataURL,dataDescribe, isFavourite,isShared);
                int i = userDataSetService.InsertDataSet(newDataSet);
                Map<String, String> resultMap = new HashMap<>();
                resultMap.put("dataId", dataId);
                result.setMsg("OK");
                result.setCode(200);
                result.setData(resultMap);
                log.info("/dataset/creat执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/dataset/creat执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 删除指定数据集 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/delete")
    public String deleteDataSet(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            String userId = StpUtil.getLoginId().toString().trim();
            String dataId = params.get("dataId").toString().trim();
            int i = userDataSetService.DeleteDataSet(dataId);
            result.setMsg("OK");
            result.setCode(200);
            log.info("/dataset/delete执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/dataset/delete执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
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
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String dataId = params.get("dataId").toString().trim();
                UserDataSet originDataSet = userDataSetService.GetDataSetByDataSetId(dataId);
                if (!originDataSet.getUserId().equals(StpUtil.getLoginId().toString().trim())) {
                    result.setCode(500);
                    result.setMsg("Error");
                    Map<String, String> returnJson = new HashMap<>();
                    returnJson.put("Error", "Not Your DataSet!");
                    result.setData(returnJson);
                    return JSON.toJSONString(result);
                }
                String userId = originDataSet.getUserId();
                String isFavourite = params.get("isFavourite").toString().trim();
                UserDataSet newUserDataSet = originDataSet;
                newUserDataSet.setIsFavourite(isFavourite);
                int i = userDataSetService.UpdateDataSet(newUserDataSet);
                result.setMsg("OK");
                result.setCode(200);
                log.info("/dataset/setfavourite执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/dataset/setfavourite执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 设置Share接口
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
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String dataId = params.get("dataId").toString().trim();
                UserDataSet originDataSet = userDataSetService.GetDataSetByDataSetId(dataId);
                if (!originDataSet.getUserId().equals(StpUtil.getLoginId().toString().trim())) {
                    result.setCode(500);
                    result.setMsg("Error");
                    Map<String, String> returnJson = new HashMap<>();
                    returnJson.put("Error", "Not Your DataSet!");
                    result.setData(returnJson);
                    return JSON.toJSONString(result);
                }
                String userId = originDataSet.getUserId();
                String isShared = params.get("isShared").toString().trim();
                UserDataSet newUserDataSet = originDataSet;
                newUserDataSet.setIsShared(isShared);
                int i = userDataSetService.UpdateDataSet(newUserDataSet);
                result.setMsg("OK");
                result.setCode(200);
                log.info("/dataset/setshare执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/dataset/setshare执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 获取指定用户数据集 接口
     *
     * @return
     */
    @PostMapping("/getdatasetbyuserid")
    public String getDataSetByUserId() {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {

            String userId = StpUtil.getLoginId().toString().trim();
            Map<String, Map> returnMap = new HashMap<>();
            Map<String, String> dataSetDetailJson;
            List<UserDataSet> dataSetList = userDataSetService.GetDataSetByUserId(userId);
            int index = 0;
            for (UserDataSet userDataSet : dataSetList) {
                dataSetDetailJson = new HashMap<>();
                dataSetDetailJson.put("dataId", userDataSet.getDataId());
                dataSetDetailJson.put("dataName", userDataSet.getDataName());
                dataSetDetailJson.put("isFavourite", String.valueOf(userDataSet.getIsFavourite()));
                dataSetDetailJson.put("isShared", String.valueOf(userDataSet.getIsShared()));
                dataSetDetailJson.put("dataDescribe", String.valueOf(userDataSet.getDataDescribe()));
                returnMap.put("userDataSet" + index, dataSetDetailJson);
                index++;
            }
            result.setMsg("OK");
            result.setCode(200);
            result.setData(returnMap);
            log.info("/dataset/getdatasetbyuserid执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/dataset/getdatasetbyuserid执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }

        return JSON.toJSONString(result);
    }

    /**
     * 获取指定用户Favourite数据集 接口
     *
     * @return
     */
    @PostMapping("/getfavouritedatasetbyuserid")
    public String getFavouriteDataByUserId() {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {

            String userId = StpUtil.getLoginId().toString().trim();
            Map<String, Map> returnMap = new HashMap<>();
            Map<String, String> dataSetDetailJson;
            List<UserDataSet> dataSetList = userDataSetService.GetFavouriteDataSetByUserId(userId);
            int index = 0;
            for (UserDataSet userDataSet : dataSetList) {
                dataSetDetailJson = new HashMap<>();
                dataSetDetailJson.put("dataId", userDataSet.getDataId());
                dataSetDetailJson.put("dataName", userDataSet.getDataName());
                dataSetDetailJson.put("isFavourite", String.valueOf(userDataSet.getIsFavourite()));
                dataSetDetailJson.put("isShared", String.valueOf(userDataSet.getIsShared()));
                dataSetDetailJson.put("dataDescribe", String.valueOf(userDataSet.getDataDescribe()));
                returnMap.put("userDataSet" + index, dataSetDetailJson);
                index++;
            }
            result.setMsg("OK");
            result.setCode(200);
            result.setData(returnMap);
            log.info("/dataset/getdatabyuserid执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/dataset/getdatabyuserid执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }

        return JSON.toJSONString(result);
    }

    /**
     * 获取所有已分享数据集 接口
     *
     * @return
     */
    @PostMapping("/getallshareddataset")
    public String getAllSharedDataSet() {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {

            String userId = StpUtil.getLoginId().toString().trim();
            Map<String, Map> returnMap = new HashMap<>();
            Map<String, String> dataSetDetailJson;
            List<UserDataSet> dataSetList = userDataSetService.GetAllSharedDataSet();
            int index = 0;
            for (UserDataSet userDataSet : dataSetList) {
                dataSetDetailJson = new HashMap<>();
                dataSetDetailJson.put("dataId", userDataSet.getDataId());
                dataSetDetailJson.put("dataName", userDataSet.getDataName());
                dataSetDetailJson.put("isFavourite", String.valueOf(userDataSet.getIsFavourite()));
                dataSetDetailJson.put("isShared", String.valueOf(userDataSet.getIsShared()));
                dataSetDetailJson.put("dataDescribe", String.valueOf(userDataSet.getDataDescribe()));
                returnMap.put("userDataSet" + index, dataSetDetailJson);
                index++;
            }
            result.setMsg("OK");
            result.setCode(200);
            result.setData(returnMap);
            log.info("/dataset/getdatabyuserid执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/dataset/getdatabyuserid执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }

        return JSON.toJSONString(result);
    }

    /**
     * 获取指定数据集 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/getdatasetydataid")
    public String getDataSetByDataId(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            String userId = StpUtil.getLoginId().toString().trim();
            String dataSetId = params.get("dataId").toString().trim();
            UserDataSet theDataSet = userDataSetService.GetDataSetByDataSetId(dataSetId);
            result.setMsg("OK");
            result.setCode(200);
            result.setData(theDataSet);
            log.info("/dataset/getdatasetydataid执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);

        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/dataset/getdatasetydataid执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 复制数据集 接口
     *
     * @param params
     * @return
     */
    @PostMapping("/copydatasetbyid")
    public String copyDataSetById(@RequestBody Map<String, Object> params) {
        CommonResult result = new CommonResult();
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            result.setCode(403);
            result.setMsg("Error");
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("Error", e.getMessage());
            result.setData(returnMap);
            return JSON.toJSONString(result);
        }
        try {
            if (params != null) {
                String dataId = String.valueOf(UUID.randomUUID());
                String orignDataId = params.get("dataId").toString().trim();
                String userId = StpUtil.getLoginId().toString().trim();
                UserDataSet newDataSet = userDataSetService.GetDataSetByDataSetId(orignDataId);
                newDataSet.setDataId(dataId);
                newDataSet.setUserId(userId);
                newDataSet.setIsShared("0");
                newDataSet.setIsFavourite("0");
                int i = userDataSetService.InsertDataSet(newDataSet);
                Map<String, String> resultMap = new HashMap<>();
                resultMap.put("dataId", dataId);
                result.setMsg("OK");
                result.setCode(200);
                result.setData(resultMap);
                log.info("/dataset/copydatasetbyid执行,userId:{},现在时间:{},port:{}", userId, DateUtil.now(), serverPort);
            }
        } catch (Exception e) {
            result = Constants.setResult(result);
            log.error("/dataset/copydatasetbyid执行出现错误,error:{},现在时间是:{},port:{}", e.getMessage(), DateUtil.now(), serverPort);
        }
        return JSON.toJSONString(result);
    }
}
