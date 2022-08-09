package com.gan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("gan_admin_token")
public class GanAdminToken extends Model<GanAdminToken> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键id
     */
    @TableId(value = "admin_user_id", type = IdType.ASSIGN_ID)
    private Long adminUserId;

    /**
     * token值(32位字符串)
     */
    @TableField("token")
    private String token;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * token过期时间
     */
    @TableField("expire_time")
    private LocalDateTime expireTime;


    @Override
    protected Serializable pkVal() {
        return this.adminUserId;
    }

}
