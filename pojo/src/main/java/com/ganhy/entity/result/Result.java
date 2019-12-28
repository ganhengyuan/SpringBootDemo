package com.ganhy.entity.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Gan
 * @ClassName: Result
 * @Date: 2019-12-5 13:20
 * @Description: 返回前端的模型类
 **/
@ApiModel(value = "返回前端的主要模型", description = "返回前端的主要模型")
@Data
public class Result<T> {

    /**
     * 状态码
     */
    @ApiModelProperty(name = "code", value = "状态码", required = true)
    private Integer code;

    /**
     * 结果状态
     */
    @ApiModelProperty(name = "status", value = "处理结果状态", required = true)
    private String status;

    /**
     * 结果信息
     */
    @ApiModelProperty(name = "message", value = "处理结果信息", required = true)
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty(name = "data", value = "前端接收的数据", required = false)
    private T data;

    /**
     * 添加其他信息
     */
    @ApiModelProperty(name = "code", value = "其他信息", required = false)
    private Object other;

    private Result() {
    }

    public Result(Integer code, String status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String status, String message, T data, Object other) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
        this.other = other;
    }

}
