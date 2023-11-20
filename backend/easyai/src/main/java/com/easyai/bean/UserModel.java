package com.easyai.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String modelId;
    private String modelName;
    private String userId;
    private String dataJson;
    private String modelURL;
    private String lastEditTime;
    private String creatTime;
    private String thumbnailUrl;
    private String isFavourite;
    private String isShare;
}
