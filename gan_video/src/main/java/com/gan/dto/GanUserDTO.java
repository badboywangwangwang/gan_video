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
* @fileSimpleName GanUserDTO
* @fileName com.gan.controller.GanUserDTO
* @description DTO
*/
@Getter
@Setter
@ToString
@ApiModel("")
public class GanUserDTO {

    private static final long serialVersionUID = 1L;


    /**
     * 用户主键id
     */
    @ApiModelProperty("用户主键id")
    private Long userId;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickName;

    /**
     * 登陆名称(默认为手机号)
     */
    @ApiModelProperty("登陆名称(默认为手机号)")
    private String loginName;

    /**
     * MD5加密后的密码
     */
    @ApiModelProperty("MD5加密后的密码")
    private String passwordMd5;

    /**
     * 个性签名
     */
    @ApiModelProperty("个性签名")
    private String introduceSign;

    /**
     * 注销标识字段(0-正常 1-已注销)
     */
    @ApiModelProperty("注销标识字段(0-正常 1-已注销)")
    private Integer isDeleted;

    /**
     * 锁定标识字段(0-未锁定 1-已锁定)
     */
    @ApiModelProperty("锁定标识字段(0-未锁定 1-已锁定)")
    private Integer lockedFlag;

    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    private LocalDateTime createTime;

    /**
     * 会员(0:非会员 1:初级 2:中级 3:高级4:超级 5:永久)
     */
    @ApiModelProperty("会员(0:非会员 1:初级 2:中级 3:高级4:超级 5:永久)")
    private Integer memberType;

    /**
     * 购买的套餐(0:非套餐 1:月 2:季度 3:半年4:年度5:永久)
     */
    @ApiModelProperty("购买的套餐(0:非套餐 1:月 2:季度 3:半年4:年度5:永久)")
    private Integer memberPay;

    /**
     * 会员到期时间
     */
    @ApiModelProperty("会员到期时间")
    private LocalDateTime membershipDue;

    /**
     * 乐观锁
     */
    @ApiModelProperty("乐观锁")
    private Integer version;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String iconUrl;

    /**
     * 粉丝数量
     */
    @ApiModelProperty("粉丝数量")
    private Integer fanNum;


}
