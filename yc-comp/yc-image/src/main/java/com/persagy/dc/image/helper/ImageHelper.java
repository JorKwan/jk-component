package com.persagy.dc.image.helper;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectoryBase;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 图片工具
 * @author Charlie Yu
 * @date 2021-05-28
 */
public class ImageHelper {

    /**
     * 获取文件所有标签信息
     * @param filePath
     */
    public static void getFileTags(String filePath) {
        File jpeg = new File(filePath);
        try {
            Metadata metadata = JpegMetadataReader.readMetadata(jpeg);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.format("[%s] - %s = %s \n",
                            directory.getName(), tag.getTagName(), tag.getDescription());
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s \n", error);
                    }
                }
            }
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件创建日期
     * @param file
     * @return
     */
    public static Date getFileDate(File file) {
        try {
            Metadata metadata = JpegMetadataReader.readMetadata(file);
            ExifDirectoryBase directory = metadata.getFirstDirectoryOfType(ExifDirectoryBase.class);
            Date date = null;
            if(directory.containsTag(ExifDirectoryBase.TAG_DATETIME)) {
                date = directory.getDate(ExifDirectoryBase.TAG_DATETIME);
            }
            return date;
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
