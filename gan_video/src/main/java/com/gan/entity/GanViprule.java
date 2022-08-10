package com.gan.entity;

import java.math.BigDecimal;
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
 * 会员规则
 * </p>
 *
 * @author Gan
 * @since 2022-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gan_viprule")
public class GanViprule extends Model<GanViprule> {

    private static final long serialVersionUID = 1L;

    /**
     * 规则号
     */
    @TableId(value = "rule_id", type = IdType.ASSIGN_ID)
    private String ruleId;

    /**
     * 乐观锁
     */
    @TableField("version")
    @Version
    private String version;

    /**
     * 逻辑删除
     */
    @TableField("is_deleted")
    @TableLogic
    private String isDeleted;

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
     * 权益介绍
     */
    @TableField("interest")
    private String interest;

    /**
     * 会员名称
     */
    @TableField("title")
    private String title;

    /**
     * 金额
     */
    @TableField("money")
    private BigDecimal money;

    /**
     * 时长
     */
    @TableField("duration")
    private Integer duration;


    @Override
    protected Serializable pkVal() {
        return this.ruleId;
    }

}
