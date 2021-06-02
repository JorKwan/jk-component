package com.persagy.dc.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.persagy.dc.message.dao.SenderRegisterMapper;
import com.persagy.dc.message.model.SenderRegister;
import com.persagy.dc.message.service.ISenderRegisterService;
import com.persagy.yc.common.constant.ValidEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 消息渠道注册 服务实现
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Service
@Transactional
public class SenderRegisterServiceImpl extends ServiceImpl<SenderRegisterMapper, SenderRegister> implements ISenderRegisterService {

    @Override
    public List<SenderRegister> queryAll() {
        LambdaQueryWrapper<SenderRegister> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SenderRegister::getValid, ValidEnum.TRUE.getType());
        return list(queryWrapper);
    }
}
