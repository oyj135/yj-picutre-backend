package pres.yj.controller;

import cn.hutool.core.lang.UUID;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.utils.IOUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pres.yj.annotation.AuthCheck;
import pres.yj.api.imageSearch.ImageSearchApiFacade;
import pres.yj.api.imageSearch.model.ImageSearchResult;
import pres.yj.common.BaseResponse;
import pres.yj.constant.UserConstant;
import pres.yj.exception.BusinessException;
import pres.yj.exception.ErrorCode;
import pres.yj.exception.ThrowUtils;
import pres.yj.manager.CosManager;
import pres.yj.model.dto.picture.SearchPictureByPictureRequest;
import pres.yj.model.entity.Picture;
import pres.yj.service.PictureService;
import pres.yj.utils.ResultUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/4/15
 */

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private CosManager cosManager;

    @Resource
    private PictureService pictureService;

    /**
     * 文件上传
     *
     * @param multipartFile 文件
     * @return 上传结果
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("test/upload")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile multipartFile) {
        // 获取文件名
        String originalFilename = multipartFile.getOriginalFilename();
        // 拼接设置文件路径
        // 获取当前时间,设置格式
        String datePath = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        // 添加 UUID 防止同一天同文件名冲突
        String uuid = UUID.randomUUID().toString().replace("-", "");

        // 获取文件后缀
        // 确认文件名不为空和包含扩展名
        String suffix = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        // 拼接文件名
        String fileName = uuid + suffix;
        // 拼接文件路径
        String filepath = String.format("%s/%s", datePath, fileName);

        File file = null;
        try {
            // 文件上传
            file = File.createTempFile(fileName, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filepath, file);
            // 返回文件路径
            return ResultUtils.success(filepath);
        } catch (Exception e) {
            log.error("file upload error, filepath = {} ", filepath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {
            // 释放临时文件
            if (file != null) {
                boolean res = file.delete();
                if (!res) {
                    log.error("file delete error, filepath = {}", filepath);
                }
            }
        }
    }

    /**
     * 测试文件下载
     *
     * @param filepath 文件路径
     * @param response 响应对象
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/test/download/")
    public void testDownloadFile(String filepath, HttpServletResponse response) throws IOException {
        COSObjectInputStream cosObjectInput = null;
        try {
            COSObject cosObject = cosManager.getObject(filepath);
            cosObjectInput = cosObject.getObjectContent();
            // 处理下载到的流
            byte[] bytes = IOUtils.toByteArray(cosObjectInput);
            // 设置响应头
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filepath);
            // 写入响应
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("file download error, filepath = " + filepath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "下载失败");
        } finally {
            if (cosObjectInput != null) {
                cosObjectInput.close();
            }
        }
    }
}
