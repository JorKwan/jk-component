package com.persagy.fm.mybatis.helper;

import cn.hutool.core.util.StrUtil;
import com.persagy.fm.mybatis.service.ITreeCodeService;
import com.persagy.yc.common.helper.SpringHelper;
import com.persagy.yc.common.model.entity.ITreeEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 树编码助手
 * @author Charlie Yu
 * @date 2021-03-29
 */
public final class TreeCodeHelper {

    /**
     * 编码引擎
     */
    private static ITreeCodeService service = null;

    /**
     * 取得编码服务
     * @return 编码服务
     */
    public static ITreeCodeService getService() {
        if (service == null) {
            service = SpringHelper.getBean(ITreeCodeService.class);
        }
        return service;
    }

    /**
     * 为指定实体生成树编码
     * @param entity     树形实体
     * @param parentCode 上级编码
     * @return 树形实体
     */
    public static <T extends ITreeEntity> T generateTreeCode(T entity, String parentCode) {
        return getService().generateTreeCode(entity, parentCode);
    }

    /**
     * 根据上级实体生成下级编码，如果上级实体为空则传入空实体
     * @param parent 上级实体
     * @return 树形编码
     */
    public static <T extends ITreeEntity> String generateTreeCodeByParent(T parent) {
        return getService().generateTreeCodeByParent(parent);
    }

    /**
     * 根据表名上级节点id上级节点编码生成新的下级编码
     * @param tableName      表名
     * @param parentCode     上级节点编码
     * @param extendWhereSql 扩展条件
     * @return 新的编码
     */
    public static String generateTreeCode(String tableName, String parentCode, String extendWhereSql) {
        return getService().generateTreeCode(tableName, parentCode, extendWhereSql);
    }

    /**
     * 为一组相同上级的实体生成树编码
     * @param entitys    树形实体数组
     * @param parentCode 上级编码
     * @return 树形实体数组
     */
    public static <T extends ITreeEntity> List<T> generateTreeCodes(List<T> entitys, String parentCode) {
        return getService().generateTreeCodes(entitys, parentCode);
    }

    /**
     * 根据上级实体生成多个下级编码，如果上级实体为空则传入空实体
     * @param parent 上级实体
     * @param length 编码个数
     * @return 编码数组
     */
    public static <T extends ITreeEntity> String[] generateTreeCodesByParent(T parent, int length) {
        return getService().generateTreeCodesByParent(parent, length);
    }

    /**
     * 根据实体树编码同步其所有子节点编码
     * @param entity      需要同步子节点编码的节点
     * @param oldTreeCode 原有的实体树编码
     */
    public static <T extends ITreeEntity> void synchronizeSubTree(T entity, String oldTreeCode) {
        getService().synchronizeSubTree(entity, oldTreeCode);
    }

    /**
     * 树形实体更改上级后的处理
     * @param entity     树形实体
     * @param parentCode 上级编码
     */
    public static <T extends ITreeEntity> ITreeEntity afterMoveNodeToParent(T entity, String parentCode) {
        return getService().afterMoveNodeToParent(entity, parentCode);
    }

    /**
     * 取得当前编码的所有上级节点编码数组
     * @param treeCode 树编码
     * @return 上级节点编码数组
     */
    public static String[] getSuppriorTreeCodes(String treeCode, boolean includeSelf) {
        if (StrUtil.isBlank(treeCode) || treeCode.length() == ITreeCodeService.TREE_CODE_LENGTH) {
            if (treeCode != null && treeCode.length() == ITreeCodeService.TREE_CODE_LENGTH && includeSelf) {
                return new String[]{treeCode};
            }
            return null;
        }
        if (treeCode.length() % ITreeCodeService.TREE_CODE_LENGTH != 0) {
            throw new IllegalArgumentException();
        }

        List<String> parentCodes = new ArrayList<String>();
        for (int i = ITreeCodeService.TREE_CODE_LENGTH; i < treeCode.length(); i += ITreeCodeService.TREE_CODE_LENGTH) {
            String code = treeCode.substring(0, i);
            parentCodes.add(code);
        }
        if (includeSelf) {
            parentCodes.add(treeCode);
        }
        return parentCodes.toArray(new String[parentCodes.size()]);
    }

    public static void main(String[] args) {
        Arrays.stream(getSuppriorTreeCodes("00001", true)).forEach(s -> {
            System.out.println(s);
        });
    }

}