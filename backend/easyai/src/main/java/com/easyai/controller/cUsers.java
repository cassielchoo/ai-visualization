package com.easyai.controller;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.easyai.bean.CommonResult;
import com.easyai.bean.User;
import com.easyai.service.UserService;
import com.easyai.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

//处理用户相关事宜

@RestController
@Slf4j

@RequestMapping("/user")

public class cUsers {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    UserService userService;

    /**
     * 用户登录接口
     * @param params
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> params){
        CommonResult result = new CommonResult();
        try{
            if(params != null){
                String userId = params.get("userId").toString().trim();
                String passwordHash = params.get("userPasshash").toString().trim();
                int size = userService.GetUserByUserId(userId).size();
                if(size != 0){
                    User user = userService.GetUserByUserId(userId).get(0);
                    String pwd = user.getUserPasshash();
                    if(pwd.equals(passwordHash)){
                        result.setCode(200);
                        result.setMsg("OK");
                        StpUtil.login(userId,new SaLoginModel().setTimeout(24*60*60*7).setIsLastingCookie(true));
                        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                        String tokenName = tokenInfo.getTokenName();
                        String tokenValue = tokenInfo.getTokenValue();
                        JSONObject returnJson = new JSONObject();
                        returnJson.set("tokenName",tokenName);
                        returnJson.set("tokenValue",tokenValue);
                        result.setData(returnJson);
                        log.info("/cUser/login执行,userId:{},现在时间是:{},port:{}",userId, DateUtil.now(),serverPort);
                    }else{
                        result.setCode(403);
                        result.setMsg("Password Incorrect");
                        log.warn("/cUser/login执行,userId:{},密码错误,现在时间是:{},port:{}",userId,DateUtil.now(),serverPort);
                    }
                }else{
                    result.setCode(403);
                    result.setMsg("Account Did Not Exist");
                    log.warn("/cUser/login执行,userId:{},账号不存在,现在时间是:{},port:{}",userId,DateUtil.now(),serverPort);
                }
            }
        }catch (Exception e){
            result = Constants.setResult(result);
            log.error("/cUser/login执行出现问题,error:{},现在时间是:{},port:{}",e.getMessage(),DateUtil.now(),serverPort);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 注册接口
     * @param params
     * @return
     */
    @PostMapping("/register")
    public String register(@RequestBody Map<String, Object> params){
        CommonResult result = new CommonResult();
        try{
            if(params != null){
                // 都是必填的
                String userId = params.get("userId").toString().trim();
                String userName = params.get("userName").toString().trim();
                String userPassword = params.get("userPasshash").toString().trim();
                String userPhone = params.get("userPhone").toString().trim();
                int size = userService.GetUserByUserId(userId).size();
                if(size == 0){
                    User newUser = new User(userId, userName, userPassword, "" , userPhone );
                    int i = userService.InsertUser(newUser);
                    result.setMsg("OK");
                    result.setCode(200);
                    log.info("/cUser/register执行,userId:{},现在时间:{},port:{}",userId,DateUtil.now(),serverPort);
                }else{
                    result.setCode(403);
                    result.setMsg("Account Already Exist");
                    log.warn("/cUser/register执行,账号已存在,userId:{},现在时间:{},port:{}",userId,DateUtil.now(),serverPort);
                }
            }
        }catch (Exception e){
            result = Constants.setResult(result);
            log.error("/cUser/register执行出现错误,error:{},现在时间是:{},port:{}",e.getMessage(),DateUtil.now(),serverPort);
        }
        return JSON.toJSONString(result);
    }

}
