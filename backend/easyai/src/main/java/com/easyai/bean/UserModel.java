package com.easyai.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    String modelId;
    String modelName;
    String userId;
    String dataJson;
    String modelURL;
    String lastEditTime;
    String creatTime;
    String thumbnailUrl;
    int isFavourite;
}
