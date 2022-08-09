
package com.gan.config.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gan.common.Constants;
import com.gan.common.GanException;
import com.gan.common.ServiceResultEnum;
import com.gan.config.annotation.TokenToUser;
import com.gan.mapper.GanUserMapper;
import com.gan.mapper.GanUserTokenMapper;
import com.gan.entity.GanUser;
import com.gan.entity.GanUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.ZoneOffset;

@Component
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private GanUserMapper ganUserMapper;
    @Autowired
    private GanUserTokenMapper ganUserTokenMapper;

    public TokenToUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getParameterAnnotation(TokenToUser.class) instanceof TokenToUser) {
            GanUser ganUser = null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == Constants.TOKEN_LENGTH) {


                LambdaQueryWrapper<GanUserToken> lqw = new LambdaQueryWrapper<>();
                lqw.eq(GanUserToken::getToken,token);

                GanUserToken ganUserToken = ganUserTokenMapper.selectOne(lqw);

                if (ganUserToken == null || ganUserToken.getExpireTime().toInstant(ZoneOffset.ofHours(8)).toEpochMilli() <= System.currentTimeMillis()) {

                    GanException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }

                ganUser = ganUserMapper.selectById(ganUserToken.getUserId());

                if (ganUser == null) {
                    GanException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }
                if (ganUser.getLockedFlag().intValue() == 1) {
                    GanException.fail(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
                }
                return ganUser;
            } else {
                GanException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

}
