package com.gan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gan.common.Constants;
import com.gan.common.GanException;
import com.gan.common.ServiceResultEnum;
import com.gan.controller.web.param.GanUserUpdateParam;
import com.gan.entity.GanUser;
import com.gan.entity.GanUserToken;
import com.gan.mapper.GanUserMapper;
import com.gan.mapper.GanUserTokenMapper;
import com.gan.service.GanUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gan.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class GanUserServiceImpl extends ServiceImpl<GanUserMapper, GanUser> implements GanUserService {
    
    @Autowired
    private GanUserMapper ganUserMapper;
    @Autowired
    private GanUserTokenMapper ganUserTokenMapper;

    @Override
    public String register(String loginName, String password) {
        if (ganUserMapper.selectById(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        GanUser registerUser = new GanUser();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        registerUser.setIntroduceSign(Constants.USER_INTRO);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (ganUserMapper.insert(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5) {

        LambdaQueryWrapper<GanUser> lwq = new LambdaQueryWrapper<>();
        lwq.eq(GanUser::getLoginName,loginName);
        lwq.eq(GanUser::getPasswordMd5,passwordMD5);
        GanUser user = ganUserMapper.selectOne(lwq);
        if (user != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult();
            }
            //登录后即执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "", user.getUserId());
            GanUserToken ganUserToken = ganUserTokenMapper.selectById(user.getUserId());
            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);//过期时间 48 小时
            if (ganUserToken == null) {
                ganUserToken = new GanUserToken();
                ganUserToken.setUserId(user.getUserId());
                ganUserToken.setToken(token);
                ganUserToken.setUpdateTime(now.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
                ganUserToken.setExpireTime(expireTime.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
                //新增一条token数据
                if (ganUserTokenMapper.insert(ganUserToken) > 0) {
                    //新增成功后返回
                    return token;
                }
            } else {
                ganUserToken.setToken(token);
                ganUserToken.setUpdateTime(now.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
                ganUserToken.setExpireTime(expireTime.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
                //更新
                if (ganUserTokenMapper.updateById(ganUserToken) > 0) {
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
        String src = timeStr + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }

    @Override
    public Boolean logout(Long userId) {
        return ganUserTokenMapper.deleteById(userId) > 0;
    }

    @Override
    public Boolean updateUserInfo(GanUserUpdateParam ganUser, Long userId) {
        GanUser user = ganUserMapper.selectById(userId);
        if (user == null) {
            GanException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        user.setNickName(ganUser.getNickName());
        //user.setPasswordMd5(ganUser.getPasswordMd5());
        //若密码为空字符，则表明用户不打算修改密码，使用原密码保存
        if (!MD5Util.MD5Encode("", "UTF-8").equals(ganUser.getPasswordMd5())){
            user.setPasswordMd5(ganUser.getPasswordMd5());
        }
        user.setIntroduceSign(ganUser.getIntroduceSign());

        if (ganUserMapper.updateById(user) > 0) {
            return true;
        }
        return false;
    }


    @Override
    public PageResult getGanUsersPage(PageQueryUtil pageUtil) {
        IPage page = new Page(pageUtil.getPage(),pageUtil.getLimit());
        ganUserMapper.selectPage(page,null);
        PageResult pageResult = new PageResult(page.getRecords(), (int)page.getTotal(), pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }


    @Override
    public Boolean lockUsers(Long[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return ganUserMapper.lockUserBatch(ids, lockStatus) > 0;
    }
}
