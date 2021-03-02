package com.markerhub.shiro;

import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author yan
 */
@Component
public class JwtFileter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServerRequest request=(HttpServerRequest) servletRequest;
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
            //执行登录
        }

        return false;
    }
}
