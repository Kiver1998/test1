package com.markerhub.util;


import com.markerhub.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @Author yan
 * @create 2021/3/3 9:05
 */
public class ShiroUtil {
    public static AccountProfile getProfile(){

        return  (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
