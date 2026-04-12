package pres.yj.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yj.common.BaseResponse;
import pres.yj.exception.ErrorCode;
import pres.yj.exception.ThrowUtils;
import pres.yj.model.dto.UserRegisterRequest;
import pres.yj.service.UserService;
import pres.yj.utils.ResultUtils;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/11
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求封装类
     * @return 新用户id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 校验参数
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        Long registerUserId = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(registerUserId);
    }
}
