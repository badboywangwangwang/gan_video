package com.gan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gan.common.ServiceResultEnum;
import com.gan.entity.GanAdmin;
import com.gan.entity.GanAdminToken;
import com.gan.mapper.GanAdminMapper;
import com.gan.mapper.GanAdminTokenMapper;
import com.gan.service.GanAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gan.util.NumberUtil;
import com.gan.util.SystemUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gan
 * @since 2022-08-09
 */
@Service
public class GanAdminServiceImpl extends ServiceImpl<GanAdminMapper, GanAdmin> implements GanAdminService {

    @Resource
    private GanAdminMapper ganAdminMapper;

    @Resource
    private GanAdminTokenMapper ganAdminTokenMapper;

    @Override
    public String login(String userName, String password) {


        LambdaQueryWrapper<GanAdmin> lqw = new LambdaQueryWrapper<>();
        lqw.eq(GanAdmin::getLoginUserName,userName);
        lqw.eq(GanAdmin::getLoginPassword,password);

        GanAdmin loginAdminUser = ganAdminMapper.selectOne(lqw);

        if (loginAdminUser != null) {
            //登录后即执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "", loginAdminUser.getAdminUserId());
            GanAdminToken ganAdminToken = ganAdminTokenMapper.selectById(loginAdminUser.getAdminUserId());

            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);//过期时间 48 小时
            if (ganAdminToken == null) {
                ganAdminToken = new GanAdminToken();
                ganAdminToken.setAdminUserId(loginAdminUser.getAdminUserId());
                ganAdminToken.setToken(token);
                ganAdminToken.setUpdateTime(now.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
                ganAdminToken.setExpireTime(expireTime.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
                //新增一条token数据
                if (ganAdminTokenMapper.insert(ganAdminToken) > 0) {
                    //新增成功后返回
                    return token;
                }
            } else {
                ganAdminToken.setToken(token);
                ganAdminToken.setUpdateTime(now.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
                ganAdminToken.setExpireTime(expireTime.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
                //更新
                if (ganAdminTokenMapper.updateById(ganAdminToken) > 0) {
                    //修改成功后返回
                    return token;
                }
            }

        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }


    /**
     * 获取token值
     *
     * @param timeStr
     * @param userId
     * @return
     */
    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(6);
        return SystemUtil.genToken(src);
    }


    @Override
    public Boolean updatePassword(Long loginUserId, String originalPassword, String newPassword) {


        GanAdmin adminUser = ganAdminMapper.selectById(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //比较原密码是否正确
            if (originalPassword.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPassword);

                if (ganAdminMapper.updateById(adminUser) > 0 && ganAdminTokenMapper.deleteById(loginUserId) > 0) {
                    //修改成功且清空当前token则返回true
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Boolean updateName(Long loginUserId, String loginUserName, String nickName) {

        GanAdmin adminUser = ganAdminMapper.selectById(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //设置新名称并修改
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (ganAdminMapper.updateById(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean logout(Long adminUserId) {
        return ganAdminTokenMapper.deleteById(adminUserId) > 0;
    }
}
