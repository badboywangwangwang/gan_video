package com.gan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
* @author Gan
* @version 1.0, 2022-08-09
* @fileSimpleName GanAdminTokenDTO
* @fileName com.gan.controller.GanAdminTokenDTO
* @description DTO
*/
@Getter
@Setter
@ToString
@ApiModel("")
public class GanAdminTokenDTO {

    private static final long serialVersionUID = 1L;


    /**
     * 用户主键id
     */
    @ApiModelProperty("用户主键id")
    private Long adminUserId;

    /**
     * token值(32位字符串)
     */
    @ApiModelProperty("token值(32位字符串)")
    private String token;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * token过期时间
     */
    @ApiModelProperty("token过期时间")
    private LocalDateTime expireTime;


}
