package com.ganhy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户菜单中间表
 * </p>
 *
 * @author Ganhy
 * @since 2019-12-05
 */
@ApiModel(value = "用户菜单关联表", description = "用于操作用户权限关系")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RelatedUserMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "id", example = "10000", required = false)
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(name = "userId", value = "用户id", example = "10000", required = true)
    @TableField("user_id")
    private Long userId;

    /**
     * 菜单id
     */
    @ApiModelProperty(name = "id", value = "菜单id", example = "10000", required = true)
    @TableField("menu_id")
    private Long menuId;


}
