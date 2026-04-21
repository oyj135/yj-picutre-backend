package pres.yj.api.imageSearch;

import lombok.extern.slf4j.Slf4j;
import pres.yj.api.imageSearch.model.ImageSearchResult;
import pres.yj.api.imageSearch.sub.GetImageFirstUrlApi;
import pres.yj.api.imageSearch.sub.GetImageListApi;
import pres.yj.api.imageSearch.sub.GetImagePageUrlApi;

import java.util.List;

@Slf4j
public class ImageSearchApiFacade {

    /**
     * 搜索图片
     *
     * @param imageUrl 图片地址
     * @return 图片列表
     */
    public static List<ImageSearchResult> searchImage(String imageUrl) {
        String imagePageUrl = GetImagePageUrlApi.getImagePageUrl(imageUrl);
        String imageFirstUrl = GetImageFirstUrlApi.getImageFirstUrl(imagePageUrl);
        List<ImageSearchResult> imageList = GetImageListApi.getImageList(imageFirstUrl);
        return imageList;
    }

    public static void main(String[] args) {
        // 测试以图搜图功能
        String imageUrl = "https://www.codefather.cn/logo.png";
        List<ImageSearchResult> resultList = searchImage(imageUrl);
        System.out.println("结果列表" + resultList);
    }
}
