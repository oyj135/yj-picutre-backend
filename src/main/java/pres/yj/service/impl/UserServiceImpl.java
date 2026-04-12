package pres.yj.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.DigestUtils;
import pres.yj.exception.BusinessException;
import pres.yj.exception.ErrorCode;
import pres.yj.model.entity.User;
import pres.yj.model.enums.UserRoleEnum;
import pres.yj.service.UserService;
import pres.yj.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author OuYJ
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2026-04-11 17:44:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验参数
        // 账号和密码不能为空
        if (StrUtil.hasBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        // 账号和密码长度不能小于4
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        // 密码和校验密码长度不能小于8
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }

        // 2. 检查用户账号是否和数据库中已有的重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        Long count = this.baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
        // 3. 密码加密
        String encryptPassword = getEncryptPassword(userPassword);
        // 4. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName("笑傲阿爸");
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean save = this.save(user);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }
        return user.getId();
    }

    /**
     * 获取加密后的密码
     *
     * @param userPassword 密码
     * @return 加密后的密码
     */
    @Override
    public String getEncryptPassword(String userPassword) {
        // 加盐，混淆密码
        final String SALT = "yj";
        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    }


}




