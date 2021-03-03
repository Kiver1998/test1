package com.markerhub.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "昵称不为空")
    private String username;
    @NotBlank(message = "密码不为空")
    private  String password;

}
