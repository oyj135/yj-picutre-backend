package pres.yj.model.dto.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/13
 * 用户登录请求
 */

@Data
public class UserLoginRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1994155335993562351L;
    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}
