package pres.yj.model.dto.picture;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/15
 * 图片上传请求封装类
 */

@Data
public class PictureUploadRequest implements Serializable {

    /**
     * 图片 id（用于修改）
     */
    private Long id;

    /**
     * 图片 url
     */
    private String fileUrl;

    /**
     * 图片名称
     */
    private String picName;

    /**
     * 空间 id
     */
    private Long spaceId;

    @Serial
    private static final long serialVersionUID = 1L;
}
