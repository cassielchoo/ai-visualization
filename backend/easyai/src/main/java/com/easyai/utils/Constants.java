package com.easyai.utils;

import com.easyai.bean.CommonResult;

public class Constants {
    public static String baseDir = System.getProperty("user.dir");
    public static CommonResult setResult(CommonResult commonResult){
        commonResult.setCode(500);
        commonResult.setMsg("ERROR");
        commonResult.setData(null);
        return commonResult;
    }
}
