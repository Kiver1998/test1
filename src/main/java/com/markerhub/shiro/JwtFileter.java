package com.markerhub.shiro;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.markerhub.common.lang.Result;
import com.markerhub.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yan
 */
@Component
public class JwtFileter extends AuthenticatingFilter {
    JwtUtils jwtUtils;
   @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
       HttpServletRequest request=(HttpServletRequest) servletRequest;
        String jwt =request.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)){
            return null;
        }
        return new JwtToken(jwt);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServerRequest request =(HttpServerRequest) servletRequest;
        String jwt =request.getHeader("Authorization");
        if(StringUtils.isEmpty(jwt)){
            return true;
        }else {
            //校验jwt

         Claims claims=jwtUtils.getClaimByToken(jwt);
         if(claims ==null||jwtUtils.isTokenExpired(claims.getExpiration())){
             throw new ExpiredCredentialsException("token已失效，请重新登录");

         }
            //执行登录
            return  executeLogin(servletRequest,servletResponse);
        }


    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
       HttpServletResponse httpServletResponse=(HttpServletResponse) response;
       Throwable throwable = e.getCause() == null ? e :e.getCause();
        Result result =Result.fail(throwable.getMessage());
        String json = JSONUtil.toJsonStr(result);

        try{
            httpServletResponse.getWriter().print(json);
        }catch (IOException ioException){

        }
       return false;
    }
}
