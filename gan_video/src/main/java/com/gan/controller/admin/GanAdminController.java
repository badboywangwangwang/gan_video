package com.gan.controller.admin;


import com.gan.common.Constants;
import com.gan.common.ServiceResultEnum;
import com.gan.config.annotation.TokenToAdminUser;
import com.gan.controller.admin.param.AdminLoginParam;
import com.gan.controller.admin.param.UpdateAdminNameParam;
import com.gan.controller.admin.param.UpdateAdminPasswordParam;
import com.gan.entity.GanAdmin;
import com.gan.entity.GanAdminToken;
import com.gan.mapper.GanAdminMapper;
import com.gan.service.GanAdminService;
import com.gan.util.Result;
import com.gan.util.ResultGenerator;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gan
 * @since 2022-08-09
 */
@Controller
@RestController
@Api(value = "v1", tags = "1-0.后台管理系统管理员模块接口")
@RequestMapping("/manage-api/v1")
public class GanAdminController {
    
    @Resource
    private GanAdminService ganAdminService;

    @Resource
    private GanAdminMapper ganAdminMapper;

    private static final Logger logger = LoggerFactory.getLogger(GanAdminController.class);
    
    
    @RequestMapping(value = "/adminUser/login", method = RequestMethod.POST)
    public Result<String> login(@RequestBody @Valid AdminLoginParam adminLoginParam) {
        String loginResult = ganAdminService.login(adminLoginParam.getUserName(), adminLoginParam.getPasswordMd5());
        logger.info("manage login api,adminName={},loginResult={}", adminLoginParam.getUserName(), loginResult);

        //登录成功
        if (!StringUtils.isEmpty(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }


    @RequestMapping(value = "/adminUser/profile", method = RequestMethod.GET)
    public Result profile(@TokenToAdminUser GanAdminToken adminUser) {
        logger.info("adminUser:{}", adminUser.toString());

        GanAdmin adminUserEntity = ganAdminMapper.selectById(adminUser.getAdminUserId());

        if (adminUserEntity != null) {
            adminUserEntity.setLoginPassword("******");
            Result result = ResultGenerator.genSuccessResult();
            result.setData(adminUserEntity);
            return result;
        }
        return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
    }


    @RequestMapping(value = "/adminUser/password", method = RequestMethod.PUT)
    public Result passwordUpdate(@RequestBody @Valid UpdateAdminPasswordParam adminPasswordParam, @TokenToAdminUser GanAdminToken adminUser) {
        logger.info("adminUser:{}", adminUser.toString());

        if (ganAdminService.updatePassword(adminUser.getAdminUserId(), adminPasswordParam.getOriginalPassword(), adminPasswordParam.getNewPassword())) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(ServiceResultEnum.DB_ERROR.getResult());
        }
    }

    @RequestMapping(value = "/adminUser/name", method = RequestMethod.PUT)
    public Result nameUpdate(@RequestBody @Valid UpdateAdminNameParam adminNameParam, @TokenToAdminUser GanAdminToken adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        if (ganAdminService.updateName(adminUser.getAdminUserId(), adminNameParam.getLoginUserName(), adminNameParam.getNickName())) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(ServiceResultEnum.DB_ERROR.getResult());
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public Result logout(@TokenToAdminUser GanAdminToken adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        ganAdminService.logout(adminUser.getAdminUserId());
        return ResultGenerator.genSuccessResult();
    }

}
