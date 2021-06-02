package com.persagy.fm.mybatis.service.impl;

import cn.hutool.core.util.StrUtil;
import com.persagy.fm.mybatis.dao.SimpleTreeCodeDao;
import com.persagy.fm.mybatis.service.ITreeCodeService;
import com.persagy.yc.common.context.AppContext;
import com.persagy.yc.common.exception.BusinessException;
import com.persagy.yc.common.model.entity.ITreeEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 树形编码服务简单实现,目前支持单节点，集群时增加一位节点代码
 * @author Charlie Yu
 * @date 2021-03-29
 */
@Service
public class SimpleTreeCodeServiceImpl implements ITreeCodeService {

    /** 数据访问对象 */
    @Resource
    private SimpleTreeCodeDao dao = null;

    @Override
    public <T extends ITreeEntity> T generateTreeCode(T entity, String parentCode) {
        String tableName = entity.getTreeTableName();
        String innerCodeField = entity.getInnerCodeField();
        String code = generateTreeCode(tableName, parentCode, innerCodeField);
        entity.setInnerCode(code);
        return entity;
    }

    @Override
    public <T extends ITreeEntity> String generateTreeCodeByParent(T parent) {
        return generateTreeCode(parent.getTreeTableName(), parent.getInnerCode(), parent.getInnerCodeField());
    }

    @Override
    public String generateTreeCode(String tableName, String parentCode, String innerCodeField) {
        if (parentCode == null) {
            parentCode = StrUtil.EMPTY;
        }
        String key = AppContext.getContext().getGroupCode() + "-" + tableName + parentCode;
        if (lock(key)) {
            try {
                String maxCode = dao.getMaxCode(tableName, parentCode, innerCodeField);
                if (maxCode != null) {
                    maxCode = maxCode.substring(parentCode.length());
                }
//			if(StringUtils.isBlank(maxCode)){
//				maxCode="00000";
//			}
                maxCode = increase(maxCode);
                return parentCode + maxCode;
            } finally {
                unlock(key);
            }
        }
        throw new BusinessException("编码生成失败，请重新操作");
    }

    @Override
    public <T extends ITreeEntity> List<T> generateTreeCodes(List<T> entitys, String parentCode) {
        if (entitys == null || entitys.size() == 0) {
            return entitys;
        }
        if (parentCode == null) {
            parentCode = StrUtil.EMPTY;
        }
        int length = entitys.size();
        ITreeEntity entity = entitys.get(0);

        String tableName = entity.getTreeTableName();
        String innerCodeField = entity.getInnerCodeField();
        String key = AppContext.getContext().getGroupCode() + "-" + tableName + "-" + parentCode;
        if (lock(key)) {
            try {
                String maxCode = dao.getMaxCode(tableName, parentCode, innerCodeField);
                if (maxCode != null) {
                    maxCode = maxCode.substring(parentCode.length());
                }
                for (int i = 0; i < length; i++) {
                    maxCode = increase(maxCode);
                    entitys.get(i).setInnerCode(parentCode + maxCode);
                }
                return entitys;
            } finally {
                unlock(key);
            }
        }
        throw new BusinessException("编码生成失败，请重新操作");
    }

    @Override
    public <T extends ITreeEntity> String[] generateTreeCodesByParent(T parent, int length) {
        String parentCode = parent.getInnerCode();
        if (parentCode == null) {
            parentCode = StrUtil.EMPTY;
        }
        String tableName = parent.getTreeTableName();
        String[] codes = new String[length];
        String key = getLockKey(tableName);
        if (lock(key)) {
            try {
                String maxCode = dao.getMaxCode(tableName, parentCode, parent.getInnerCodeField());
                if (maxCode != null) {
                    maxCode = maxCode.substring(parentCode.length());
                }
                for (int i = 0; i < length; i++) {
                    maxCode = increase(maxCode);
                    codes[i] = parentCode + maxCode;
                }
                return codes;
            } finally {
                unlock(key);
            }
        }
        throw new BusinessException("编码生成失败，请重新操作");
    }

    @Override
    public <T extends ITreeEntity> void synchronizeSubTree(T entity, String oldTreeCode) {
        if (entity == null || StrUtil.isEmpty(entity.getInnerCode())) {
            return;
        }
        String tableName = entity.getTreeTableName();
        String parentCode = entity.getInnerCode();
        dao.updateSubTreeCode(tableName, parentCode, oldTreeCode, entity.getInnerCodeField());
    }

    @Override
    public <T extends ITreeEntity> T afterMoveNodeToParent(T entity, String parentCode) {
        if (entity == null || StrUtil.isEmpty(entity.getInnerCode())) {
            return entity;
        }
        String oldTreeCode = entity.getInnerCode();
        String key = getLockKey(entity.getTreeTableName());
        if (lock(key)) {
            try {
                generateTreeCode(entity, parentCode);
                synchronizeSubTree(entity, oldTreeCode);
                return entity;
            } finally {
                unlock(key);
            }
        }
        throw new BusinessException("节点移动失败，请重试操作");
    }

    /**
     * 递增最大编码
     * @param maxCode 原有最大编码
     * @return 递增后编码
     */
    public String increase(String maxCode) {
        long v = 0;
        if (maxCode != null) {
            v = Long.parseLong(maxCode, Character.MAX_RADIX);
            v++;
        }
        maxCode = Long.toString(v, Character.MAX_RADIX);
        int digit = TREE_CODE_LENGTH - maxCode.length();
        if (digit < 0) {
            throw new RuntimeException("Tree Code digit overflow!");
        }
        char[] chars = new char[TREE_CODE_LENGTH];
        for (int i = 0; i < digit; i++) {
            chars[i] = '0';
        }
        System.arraycopy(maxCode.toCharArray(), 0, chars, digit, maxCode.length());
        return new String(chars);
    }

    /**
     * 取得锁的键值
     * @param tableName 表名
     * @return 键值
     */
    private String getLockKey(String tableName) {
        String tenant = AppContext.getContext().getGroupCode();
        if (StrUtil.isEmpty(tenant)) {
            throw new BusinessException("未指定租户id");
        }
        return tenant + "-" + tableName;
    }

    /**
     * 先不加锁，后续引入分布式锁
     * @param key
     * @return
     */
    private boolean lock(String key) {
        return true;
    }

    /**
     * 先不加锁，后续引入分布式锁
     * @param key
     */
    private void unlock(String key) {

    }
}
