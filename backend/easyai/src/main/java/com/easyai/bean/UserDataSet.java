package com.easyai.bean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataSet {
    private String dataId;
    private String dataName;
    private String userId;
    private String dataURL;
    private String dataDescribe;
    private String isFavourite;
    private String isShare;
}
