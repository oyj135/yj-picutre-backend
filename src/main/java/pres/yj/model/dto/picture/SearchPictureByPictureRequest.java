package pres.yj.model.dto.picture;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 图片搜索请求(以图搜图)
 *
 * @author yj
 */
@Data
public class SearchPictureByPictureRequest implements Serializable {

    /**
     * 图片 id
     */
    private Long pictureId;

    @Serial
    private static final long serialVersionUID = 1L;
}
