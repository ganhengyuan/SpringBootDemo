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
@ApiModel(value = "菜单表", description = "用于菜单的展示")
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "id",example = "10000", required = false)
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 父菜单id
     */
    @ApiModelProperty(name = "pid", value = "父级菜单Id",example = "1000",  required = false)
    @TableField("pid")
    private Long pid;

    /**
     * 菜单名
     */
    @ApiModelProperty(name = "name", value = "菜单名称",example = "用户管理",  required = true)
    @TableField("name")
    private String name;

    /**
     * 访问路径
     */
    @ApiModelProperty(name = "url", value = "菜单访问路径",example = "/manage/user",  required = true)
    @TableField("url")
    private String url;

    /**
     * 访问路径别称 (用于权限判断)
     */
    @ApiModelProperty(name = "urlNickName", value = "菜单别称,用于访问权限",example = "user",  required = true)
    @TableField("url_nick_name")
    private String urlNickName;

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
