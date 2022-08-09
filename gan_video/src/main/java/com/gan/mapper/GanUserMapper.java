package com.gan.mapper;

import com.gan.entity.GanUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Gan
 * @since 2022-08-09
 */
@Repository
public interface GanUserMapper extends BaseMapper<GanUser> {

    int lockUserBatch(@Param("ids") Long[] ids, @Param("lockStatus") int lockStatus);

}
