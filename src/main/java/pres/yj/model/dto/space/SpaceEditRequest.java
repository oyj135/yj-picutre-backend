package pres.yj.model.dto.space;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/18
 * 编辑空间请求
 */
@Data
public class SpaceEditRequest implements Serializable {

    /**
     * 空间 id
     */
    private Long id;

    /**
     * 空间名称
     */
    private String spaceName;

    @Serial
    private static final long serialVersionUID = 1L;
}
