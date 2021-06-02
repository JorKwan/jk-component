package com.persagy.dc.image.helper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ImageCollector {

    public static void main(String[] args) {
        ImageCollector c = new ImageCollector();
        c.doCollect("D:/Temp", "D:/Temp1");
    }

    /**
     * 文件分组归档
     * 归档路径：202104/20210401120000-999999.jpg
     * @param srcDirectory 来源目录
     * @param desDirectory 归档目录
     */
    public void doCollect(String srcDirectory, String desDirectory) {
        log.info("开始文件整理，目录：{}", srcDirectory);
        // 获取路径后所有文件
        List<File> files = FileUtil.loopFiles(srcDirectory);
        if(CollUtil.isEmpty(files)) {
            log.info("没有任何文件，整理结束。目录：{}", srcDirectory);
            return;
        }
        int fileSize = files.size();
        log.info("文件收集完成，文件共{}个。", fileSize);
        AtomicInteger index = new AtomicInteger(0);
        // 创建10个线程
        List<Callable<Integer>> callableList = new ArrayList<>();
        for(int i = 0;i < 10;i++) {
            callableList.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    while(true) {
                        int currIndex = index.getAndIncrement();
                        if (currIndex >= fileSize) {
                            return currIndex;
                        }
                        log.info("执行进度：当前第{}个，共{}个", currIndex+1, fileSize);
                        File file = files.get(currIndex);
                        Date date = ImageHelper.getFileDate(file);
                        String desFilePath = getDesFileName(file, desDirectory, date);
                        FileUtil.move(file, new File(desFilePath), false);
                    }
                }
            });
        }
        try {
            ExecutorService pool = Executors.newFixedThreadPool(100);
            List<Future<Integer>> futures = pool.invokeAll(callableList);
            while(true) {
                boolean finish = true;
                for (Future<Integer> future : futures) {
                    if(future.isDone()) {
                        continue;
                    }
                    finish = false;
                    break;
                }
                if(finish) {
                    log.info("执行结束！");
                    pool.shutdown();
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        for(File file:files) {
//            Date date = ImageHelper.getFileDate(file);
//            String desFilePath = getDesFileName(file, desDirectory, date);
//            FileUtil.move(file, new File(desFilePath), false);
//        }
    }

    /**
     * 目标文件路径
     * @param file 原始文件
     * @param desDirectory 归档目录
     * @param date 文件创建日期
     * @return {desDirectory}/202104/20210401120000-999999.jpg
     */
    private String getDesFileName(File file, String desDirectory, Date date) {
        String dic = date == null?"default":DateUtil.format(date, "yyyy-MM");
        String name = date == null?FileUtil.getPrefix(file):DateUtil.format(date, "yyyyMMddHHmmss");
        StringBuffer desFileName = new StringBuffer(desDirectory);
        desFileName.append(FileNameUtil.UNIX_SEPARATOR).append(dic);
        desFileName.append(FileNameUtil.UNIX_SEPARATOR).append(name);
        desFileName.append("-").append(RandomUtil.randomInt(1000000));
        desFileName.append(".").append(FileUtil.getSuffix(file));
        return desFileName.toString();
    }
}
