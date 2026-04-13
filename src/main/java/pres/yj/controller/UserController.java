package pres.yj.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.yj.common.BaseResponse;
import pres.yj.exception.ErrorCode;
import pres.yj.exception.ThrowUtils;
import pres.yj.model.dto.user.UserLoginRequest;
import pres.yj.model.dto.user.UserRegisterRequest;
import pres.yj.model.entity.User;
import pres.yj.model.vo.LoginUserVO;
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

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求封装类
     * @return 脱敏用户对象
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        // 校验参数
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        LoginUserVO loginUserVO = userService.userLogin(userLoginRequest, request);
        return ResultUtils.success(loginUserVO);
    }

    /**
     * 获取当前登录用户
     *
     * @param request 请求对象
     * @return 脱敏用户对象
     */
    @PostMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User loginUserVO = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(loginUserVO));
    }

    /**
     * 用户注销
     *
     * @param request 请求对象
     * @return 退出登录结果
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        return ResultUtils.success(userService.userLogout(request));
    }
}
