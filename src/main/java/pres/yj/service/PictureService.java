package pres.yj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import pres.yj.model.dto.picture.PictureQueryRequest;
import pres.yj.model.dto.picture.PictureReviewRequest;
import pres.yj.model.dto.picture.PictureUploadByBatchRequest;
import pres.yj.model.dto.picture.PictureUploadRequest;
import pres.yj.model.dto.vo.PictureVO;
import pres.yj.model.entity.Picture;
import pres.yj.model.entity.User;

/**
 * @author OuYJ
 * @description 针对表【picture(图片)】的数据库操作Service
 * @createDate 2026-04-15 17:36:45
 */
public interface PictureService extends IService<Picture> {

    /**
     * 上传图片
     *
     * @param inputSource 输入源
     * @param pictureUploadRequest 图片上传请求
     * @param loginUser 登录用户
     * @return 图片封装
     */
    PictureVO uploadPicture(Object inputSource,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    /**
     * 获取单个图片封装
     *
     * @param picture
     * @param request
     * @return
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);


    /**
     * 分页获取图片封装
     *
     * @param picturePage
     * @param request
     * @return
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 获取图片查询条件
     *
     * @param pictureQueryRequest
     * @return
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    /**
     * 校验图片
     *
     * @param picture
     */
    void validPicture(Picture picture);

    /**
     * 图片审核
     *
     * @param pictureReviewRequest
     * @param loginUser
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);


    /**
     * 封装审核参数
     *
     * @param picture   图片实体类
     * @param loginUser 登录用户
     */
    void fillReviewParams(Picture picture, User loginUser);

    /**
     * 批量抓取图片
     *
     * @param pictureUploadByBatchRequest 批量抓取请求封装类
     * @param loginUser 登录用户
     * @return 图片数量
     */
    Integer uploadPictureByBatch(PictureUploadByBatchRequest pictureUploadByBatchRequest, User loginUser);

}
