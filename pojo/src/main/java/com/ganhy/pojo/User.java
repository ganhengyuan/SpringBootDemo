package com.ganhy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ganhy
 * @since 2019-12-05
 */
@ApiModel(value = "用户表", description = "用于用户的相关操作")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "id", example = "10000", required = false)
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "username", value = "用户名", example = "张三", required = true)
    @TableField("username")
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(name = "password", value = "用户密码", example = "123", required = true)
    @TableField("password")
    private String password;

    /**
     * 用户性别
     */
    @ApiModelProperty(name = "gender", value = "用户性别", example = "男", required = false)
    @TableField("gender")
    private String gender;

    /**
     * 用户年龄
     */
    @ApiModelProperty(name = "age", value = "用户年龄", example = "23", required = false)
    @TableField("age")
    private Integer age;

    /**
     * 联系电话
     */
    @ApiModelProperty(name = "phone", value = "用户联系电话", example = "13888888888", required = false)
    @TableField("phone")
    private String phone;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除状态
     */
    @TableField("delete_status")
    @TableLogic
    private String deleteStatus;


}
