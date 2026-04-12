package pres.yj.service;

import pres.yj.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 获取加密密码
     *
     * @param userPassword 用户密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String userPassword);
}
