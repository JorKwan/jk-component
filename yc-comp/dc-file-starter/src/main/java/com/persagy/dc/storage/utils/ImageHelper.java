package com.persagy.dc.storage.utils;

import cn.hutool.core.img.ImgUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 图片工具
 * @author Charlie Yu
 * @date 2021-05-15
 */
@Slf4j
public class ImageHelper {

    /** 压缩后大小(b) */
    private static final BigDecimal IMAGE_SIZE = BigDecimal.valueOf(100 * 1024);

    /**
     * 压缩裁剪图片
     * @param in 原图片
     * @return 图片压缩至IMAGE_SIZE以下
     */
    public static InputStream cutImage(InputStream in) {
        try {
            // 文件大小
            BigDecimal fileSize = BigDecimal.valueOf(in.available());
            // 文件小于压缩后大小，不必处理
            if(fileSize.compareTo(IMAGE_SIZE) <= 0) {
                return in;
            }
            // 文件压缩比例
            BigDecimal fileScaleValue = IMAGE_SIZE.divide(fileSize, 8, RoundingMode.DOWN);
            // 图片压缩比例 = 根号 文件压缩比例
            float scaleValue = BigDecimal.valueOf(Math.sqrt(fileScaleValue.doubleValue())).floatValue();
            // 容错处理，如果图片过大，会导致压缩比例过小为0，采用最小压缩比
            if(scaleValue <= 0) {
                scaleValue = 0.001f;
            }
            try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                // 压缩
                ImgUtil.scale(in, out, scaleValue);
                return new ByteArrayInputStream(out.toByteArray());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return in;
    }
}
