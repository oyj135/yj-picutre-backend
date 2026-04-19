package pres.yj.model.dto.picture;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/15
 * 批量导入图片请求
 */

@Data
public class PictureUploadByBatchRequest implements Serializable {

    /**
     * 搜索文本
     */
    private  String searchText;

    /**
     * 抓取数量
     */
    private final Integer count = 10;

    /**
     * 图片文件名前缀
     */
    private String namePrefix;


    @Serial
    private static final long serialVersionUID = 1L;
}
