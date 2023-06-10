package com.yql.outlierbackend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("数据库字段")
public class Data {

    @ApiModelProperty(value = "检测序号ID", required = true, example = "1")
    private int id;

    @ApiModelProperty(value = "算法类型", required = true, example = "KNN")
    private String algorithm;

    @ApiModelProperty(value = "参数1", required = true, example = "10")
    private int param1;

    @ApiModelProperty(value = "参数2", required = true, example = "50")
    private int param2;

    @ApiModelProperty(value = "文件路径", required = true, example = "")
    private String filepath;

    @ApiModelProperty(value = "图片路径", required = true, example = "")
    private String imgpath;

}
