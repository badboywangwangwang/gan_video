package com.gan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* @author Gan
* @version 1.0, 2022-08-09
* @fileSimpleName GanAdminDTO
* @fileName com.gan.controller.GanAdminDTO
* @description DTO
*/
@Getter
@Setter
@ToString
@ApiModel("")
public class GanAdminDTO {

    private static final long serialVersionUID = 1L;


    /**
     * 管理员id
     */
    @ApiModelProperty("管理员id")
    private Long adminUserId;

    /**
     * 管理员登陆名称
     */
    @ApiModelProperty("管理员登陆名称")
    private String loginUserName;

    /**
     * 管理员登陆密码
     */
    @ApiModelProperty("管理员登陆密码")
    private String loginPassword;

    /**
     * 管理员显示昵称
     */
    @ApiModelProperty("管理员显示昵称")
    private String nickName;

    /**
     * 是否锁定 0未锁定 1已锁定无法登陆
     */
    @ApiModelProperty("是否锁定 0未锁定 1已锁定无法登陆")
    private Integer locked;


}
