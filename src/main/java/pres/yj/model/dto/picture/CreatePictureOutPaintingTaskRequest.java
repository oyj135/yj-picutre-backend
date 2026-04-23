package pres.yj.model.dto.picture;

import lombok.Data;
import pres.yj.api.aliyunai.model.CreateOutPaintingTaskRequest;

import java.io.Serializable;

/**
 * 创建图片AI扩图任务请求参数
 */
@Data
public class CreatePictureOutPaintingTaskRequest implements Serializable {

    /**
     * 图片 id
     */
    private Long pictureId;

    /**
     * 扩图参数
     */
    private CreateOutPaintingTaskRequest.Parameters parameters;

    private static final long serialVersionUID = 1L;
}
