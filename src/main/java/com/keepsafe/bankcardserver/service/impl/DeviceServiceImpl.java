package com.keepsafe.bankcardserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.keepsafe.bankcardserver.data.dao.DeviceInfo;
import com.keepsafe.bankcardserver.data.dto.DeviceReqDTO;
import com.keepsafe.bankcardserver.mapper.DeviceMapper;
import com.keepsafe.bankcardserver.service.DeviceService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceMapper deviceMapper;

    @Autowired
    public DeviceServiceImpl(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    @Override
    public boolean bindDevice(DeviceReqDTO dto, long userId) {
        // 先查询设备UUID是否已存在
        QueryWrapper<DeviceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", dto.getUuid());
        DeviceInfo existingDevice = deviceMapper.selectOne(queryWrapper);

        if (existingDevice == null) {
            // UUID不存在，创建新设备并绑定
            DeviceInfo newDevice = new DeviceInfo();
            newDevice.setUuid(dto.getUuid());
            newDevice.setUserId(userId);
            newDevice.setBrand(dto.getBrand());
            newDevice.setModel(dto.getModel());
            newDevice.setCreateDate(LocalDateTime.now());
            newDevice.setModifyDate(LocalDateTime.now());
            return true;
        } else {
            // UUID已存在，不绑定
            return false;
        }
    }

    @Override
    public boolean unbindDevice(String uuid, long userId) {
        // 查询设备是否存在且属于该用户
        LambdaQueryWrapper<DeviceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DeviceInfo::getUuid, uuid)
                .eq(DeviceInfo::getUserId, userId);
        DeviceInfo device = deviceMapper.selectOne(queryWrapper);

        if (device != null) {
            // 存在且属于用户，删除
            deviceMapper.deleteById(device);
            return true;
        }
        // 不存在或不属于用户，返回false
        return false;
    }

    @NotNull
    @Override
    public List<DeviceInfo> findDeviceById(long userId) {
        LambdaQueryWrapper<DeviceInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DeviceInfo::getUserId, userId);
        return deviceMapper.selectList(wrapper);
    }
}
