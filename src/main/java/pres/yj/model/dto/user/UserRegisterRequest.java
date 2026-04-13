package pres.yj.model.dto.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/11
 * 用户注册请求参数
 */

@Data
public class UserRegisterRequest implements Serializable {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 2362069639420099364L;

    /**
     * 用户账户
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkPassword;

}
