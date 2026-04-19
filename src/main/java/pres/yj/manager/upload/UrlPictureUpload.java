package pres.yj.manager.upload;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pres.yj.exception.BusinessException;
import pres.yj.exception.ErrorCode;
import pres.yj.exception.ThrowUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ</a>
 * @Date 2026/4/18
 * 通过 URL 上传图片
 */
@Service
public class UrlPictureUpload extends PictureUploadTemplate {
    /**
     * 根据url效验文件
     *
     * @param inputSource 文件源
     */
    @Override
    protected void validPicture(Object inputSource) {
        String fileUrl = (String) inputSource;
        ThrowUtils.throwIf(StrUtil.isBlank(fileUrl), ErrorCode.PARAMS_ERROR, "文件地址不能为空");
        try {
            // 1. 验证 URL 格式
            new URL(fileUrl); // 验证是否是合法的 URL
        } catch (MalformedURLException e) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件地址格式不正确");
        }

        // 2. 校验 URL 协议
        ThrowUtils.throwIf(!fileUrl.startsWith("http://") && !fileUrl.startsWith("https://"),
                ErrorCode.PARAMS_ERROR, "仅支持 HTTP 或 HTTPS 协议的文件地址");

        // 3. 发送 HEAD 请求以验证文件是否存在
        HttpResponse response = null;
        try {
            response = HttpUtil.createRequest(Method.HEAD, fileUrl).execute();
            // 未正常返回，无需执行其他判断
            if (response.getStatus() != HttpStatus.HTTP_OK) {
                return;
            }
            // 4. 校验文件类型
            String contentType = response.header("Content-Type");
            if (StrUtil.isNotBlank(contentType)) {
                // 允许的图片类型
                final List<String> ALLOW_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/webp");
                ThrowUtils.throwIf(!ALLOW_CONTENT_TYPES.contains(contentType.toLowerCase()),
                        ErrorCode.PARAMS_ERROR, "文件类型错误");
            }
            // 5. 校验文件大小
            String contentLengthStr = response.header("Content-Length");
            if (StrUtil.isNotBlank(contentLengthStr)) {
                try {
                    long contentLength = Long.parseLong(contentLengthStr);
                    final long TWO_MB = 2 * 1024 * 1024L; // 限制文件大小为 2MB
                    ThrowUtils.throwIf(contentLength > TWO_MB, ErrorCode.PARAMS_ERROR, "文件大小不能超过 2M");
                } catch (NumberFormatException e) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小格式错误");
                }
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 获取原始文件名
     *
     * @param inputSource 源路径
     * @return
     */
    @Override
    protected String getOriginFilename(Object inputSource) {
        String fileUrl = (String) inputSource;
        // 从 URL 中提取完整文件名（包含扩展名）
        String fileName = FileUtil.getName(fileUrl);

        // 先尝试从 URL 中获取带后缀的文件名
        if (StrUtil.isNotBlank(fileName) && fileName.contains(".")) {
            // 验证后缀是否是合法的图片格式
            String suffix = FileUtil.getSuffix(fileName);
            if (isValidImageExtension(suffix)) {
                return fileName;
            }
            // 如果后缀不合法，去掉原有后缀，后续从响应头获取
            fileName = FileUtil.mainName(fileName);
        }

        // 如果URL中没有文件后缀，则从响应头中获取
        HttpResponse response = null;
        try {
            response = HttpUtil.createRequest(Method.HEAD, fileUrl)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .execute();
            // 从Content-Type 推断文件扩展名
            String contentType = response.header("Content-Type");
            if (StrUtil.isNotBlank(contentType)) {
                String ext = getSuffixFromContentType(contentType);
                if (ext != null) {
                    // 如果 URL 中有文件名但不含后缀，补充后缀
                    if (StrUtil.isNotBlank(fileName)) {
                        return fileName + "." + ext;
                    }
                    return "image." + ext;
                }
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        // 兜底：如果都获取不到，返回默认值
        return StrUtil.isNotBlank(fileName) ? fileName : "image.jpg";
    }


    /**
     * 判断是否是合法的图片扩展名
     */
    private boolean isValidImageExtension(String extension) {
        if (StrUtil.isBlank(extension)) {
            return false;
        }
        String lowerExt = extension.toLowerCase();
        return lowerExt.equals("jpg") || lowerExt.equals("jpeg") ||
                lowerExt.equals("png") || lowerExt.equals("gif") ||
                lowerExt.equals("webp") || lowerExt.equals("svg") ||
                lowerExt.equals("bmp");
    }

    private String getSuffixFromContentType(String contentType) {
        switch (contentType) {
            case "image/jpeg": return "jpeg";
            case "image/jpg": return "jpg";
            case "image/png": return "png";
            case "image/webp": return "webp";
            case "image/gif": return "gif";
            case "image/svg": return "svg";
            case "image/bmp": return "bmp";
            default: return null;
        }
    }

    /**
     * 处理文件
     *
     * @param inputSource 图片
     * @param file        路径
     * @throws Exception
     */
    @Override
    protected void processFile(Object inputSource, File file) throws Exception {
        String fileUrl = (String) inputSource;
        // 下载文件到临时目录  
        HttpUtil.downloadFile(fileUrl, file);
    }
}
