package com.gan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gan
 * @since 2022-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gan_user")
public class GanUser extends Model<GanUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 登陆名称(默认为手机号)
     */
    @TableField("login_name")
    private String loginName;

    /**
     * MD5加密后的密码
     */
    @TableField("password_md5")
    private String passwordMd5;

    /**
     * 个性签名
     */
    @TableField("introduce_sign")
    private String introduceSign;

    /**
     * 注销标识字段(0-正常 1-已注销)
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    /**
     * 锁定标识字段(0-未锁定 1-已锁定)
     */
    @TableField("locked_flag")
    private Integer lockedFlag;

    /**
     * 注册时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 会员(0:非会员 1:初级 2:中级 3:高级4:超级 5:永久)
     */
    @TableField("member_type")
    private Integer memberType;

    /**
     * 购买的套餐(0:非套餐 1:月 2:季度 3:半年4:年度5:永久)
     */
    @TableField("member_pay")
    private Integer memberPay;

    /**
     * 会员到期时间
     */
    @TableField("membership_due")
    private LocalDateTime membershipDue;

    /**
     * 乐观锁
     */
    @TableField("version")
    @Version
    private Integer version;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 头像
     */
    @TableField("icon_url")
    private String iconUrl;

    /**
     * 粉丝数量
     */
    @TableField("fan_num")
    private Integer fanNum;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
