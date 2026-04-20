package pres.yj.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/18
 * 图片标签分类列表视图
 */

@Data
public class PictureTagCategory {

    private List<String> tagList;

    private List<String> categoryList;
}
