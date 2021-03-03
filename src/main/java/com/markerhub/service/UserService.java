package com.markerhub.service;

import com.markerhub.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kiver
 * @since 2021-03-01
 */
@Service
public interface UserService extends IService<User> {

}
