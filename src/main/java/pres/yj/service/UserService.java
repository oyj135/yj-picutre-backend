package pres.yj.service;

import jakarta.servlet.http.HttpServletRequest;
import pres.yj.model.dto.user.UserLoginRequest;
import pres.yj.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import pres.yj.model.vo.LoginUserVO;

/**
* @author OuYJ
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2026-04-11 17:44:07
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    Long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求封装类
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request 请求
     * @return 当前登录用户
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 获取脱敏后的用户信息
     *
     * @param user 用户信息
     * @return 脱敏后的用户信息
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获取加密密码
     *
     * @param userPassword 用户密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String userPassword);

    /**
     * 用户登出
     *
     * @param request 请求
     * @return 登出结果
     */
    boolean userLogout(HttpServletRequest request);
}
