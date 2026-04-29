package pres.yj.model.dto.spaceuser;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 查询请求
 *
 * @author yj
 */
@Data
public class SpaceUserQueryRequest implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 空间 ID
     */
    private Long spaceId;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 空间角色：viewer/editor/admin
     */
    private String spaceRole;

    @Serial
    private static final long serialVersionUID = 1L;
}
