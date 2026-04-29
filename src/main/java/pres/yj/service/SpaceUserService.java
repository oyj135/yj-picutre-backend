package pres.yj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import pres.yj.model.dto.spaceuser.SpaceUserAddRequest;
import pres.yj.model.dto.spaceuser.SpaceUserQueryRequest;
import pres.yj.model.entity.SpaceUser;
import com.baomidou.mybatisplus.extension.service.IService;
import pres.yj.model.vo.SpaceUserVO;

import java.util.List;

/**
 * @author OuYJ
 * @description 针对表【space_user(空间用户关联)】的数据库操作Service
 * @createDate 2026-04-29 15:35:06
 */
public interface SpaceUserService extends IService<SpaceUser> {

    /**
     * 添加空间成员
     *
     * @param spaceUserAddRequest 添加的空间成员
     * @return 添加的空间成员id
     */
    long addSpaceUser(SpaceUserAddRequest spaceUserAddRequest);


    /**
     * 获取查询条件
     *
     * @param spaceUserQueryRequest 查询条件
     * @return 查询条件
     */
    QueryWrapper<SpaceUser> getQueryWrapper(SpaceUserQueryRequest spaceUserQueryRequest);

    /**
     * 获取空间成员视图
     *
     * @param spaceUser 空间成员
     * @param request   请求
     * @return 空间成员视图
     */
    SpaceUserVO getSpaceUserVO(SpaceUser spaceUser, HttpServletRequest request);

    /**
     * 获取空间成员视图列表
     *
     * @param spaceUserList 空间成员列表
     * @return 空间成员视图列表
     */
    List<SpaceUserVO> getSpaceUserVOList(List<SpaceUser> spaceUserList);

    /**
     * 校验空间成员
     *
     * @param spaceUser 空间成员
     * @param add       是否为添加操作
     */
    void validSpaceUser(SpaceUser spaceUser, boolean add);
}
