package com.gan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("gan_admin")
public class GanAdmin extends Model<GanAdmin> {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
    @TableId(value = "admin_user_id", type = IdType.AUTO)
    private Long adminUserId;

    /**
     * 管理员登陆名称
     */
    @TableField("login_user_name")
    private String loginUserName;

    /**
     * 管理员登陆密码
     */
    @TableField("login_password")
    private String loginPassword;

    /**
     * 管理员显示昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 是否锁定 0未锁定 1已锁定无法登陆
     */
    @TableField("locked")
    private Integer locked;


    @Override
    protected Serializable pkVal() {
        return this.adminUserId;
    }

}
