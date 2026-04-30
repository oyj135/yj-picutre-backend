package pres.yj.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/11
 */

@Getter
public enum UserRoleEnum {
    USER("用户","user"),
    VIP("会员","vip"),
    ADMIN("管理员","admin");

    private final String name;

    private final String value;

    UserRoleEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 根据value获取枚举
     *
     * @param value 枚举值的 value
     * @return 枚举值
     */
    public static UserRoleEnum getEnumByValue(String value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (userRoleEnum.value.equals(value)) {
                return userRoleEnum;
            }
        }
        // 优化：使用map
        // 数据量大时，可以使用map
        // Map<String, UserRoleEnum> map = new HashMap<>();
        // map.put(UserRoleEnum.name, UserRoleEnum.value);
        return null;
    }
}
