package pres.yj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import pres.yj.model.dto.space.SpaceAddRequest;
import pres.yj.model.dto.space.SpaceQueryRequest;
import pres.yj.model.entity.Space;
import pres.yj.model.entity.User;
import pres.yj.model.vo.SpaceVO;

/**
* @author OuYJ
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2026-04-20 17:42:07
*/
public interface SpaceService extends IService<Space> {


    /**
     * 添加空间
     *
     * @param spaceAddRequest 添加空间请求
     * @param loginUser 登录用户
     * @return 添加的空间ID
     */
    Long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);
    /**
     * 获取单个空间封装
     *
     * @param space
     * @param request
     * @return
     */
    SpaceVO getSpaceVO(Space space, HttpServletRequest request);


    /**
     * 分页获取空间封装
     *
     * @param spacePage 分页对象
     * @param request 请求
     * @return 分页对象
     */
    Page<SpaceVO> getSpaceVOPage(Page<Space> spacePage, HttpServletRequest request);

    /**
     * 获取空间查询条件
     *
     * @param spaceQueryRequest 查询条件
     * @return 查询条件
     */
    QueryWrapper<Space> getQueryWrapper(SpaceQueryRequest spaceQueryRequest);

    /**
     * 校验空间
     *
     * @param space 空间实体类
     * @param add   是否创建时添加
     */
    void validSpace(Space space, boolean add);

    /**
     * 根据空间级别，自动填充限额
     *
     * @param space 空间实体类
     */
    void fillSpaceBySpaceLevel(Space space);

    /**
     * 校验用户对空间权限
     *
     * @param loginUser 登录用户
     * @param space 空间实体类
     * @return 是否有权限
     */
    void checkSpaceAuth(User loginUser, Space space);
}
