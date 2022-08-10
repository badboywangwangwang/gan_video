package com.gan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @author Gan
* @version 1.0, 2022-08-10
* @fileSimpleName GanVipruleDTO
* @fileName com.gan.controller.admin.GanVipruleDTO
* @description 会员规则DTO
*/
@Getter
@Setter
@ToString
@ApiModel("会员规则")
public class GanVipruleDTO {

    private static final long serialVersionUID = 1L;


    /**
     * 规则号
     */
    @ApiModelProperty("规则号")
    private String ruleId;

    /**
     * 乐观锁
     */
    @ApiModelProperty("乐观锁")
    private String version;

    /**
     * 逻辑删除
     */
    @ApiModelProperty("逻辑删除")
    private String isDeleted;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    /**
     * 权益介绍
     */
    @ApiModelProperty("权益介绍")
    private String interest;

    /**
     * 会员名称
     */
    @ApiModelProperty("会员名称")
    private String title;

    /**
     * 金额
     */
    @ApiModelProperty("金额")
    private BigDecimal money;

    /**
     * 时长
     */
    @ApiModelProperty("时长")
    private Integer duration;


}
