package pres.yj.model.dto.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/30
 * 会员兑换请求
 */

@Data
public class VipExchangeRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 219029023871487122L;

    /**
     * 兑换码
     */
    private String vipCode;


}
