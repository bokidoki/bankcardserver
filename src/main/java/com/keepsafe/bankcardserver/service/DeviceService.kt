package com.keepsafe.bankcardserver.service

import com.keepsafe.bankcardserver.data.dao.DeviceInfo
import com.keepsafe.bankcardserver.data.dto.DeviceReqDTO

interface DeviceService {

    /**
     * 绑定设备
     *
     * @param deviceReqDTO 设备信息
     * @param userId 用户唯一标识
     */
    fun bindDevice(deviceReqDTO: DeviceReqDTO, userId: Long): Boolean

    /**
     * 解绑设备
     *
     * @param uuid 设备唯一标识
     * @param userId 用户唯一标识
     */
    fun unbindDevice(uuid: String, userId: Long): Boolean

    /**
     * 通过用户ID查询绑定设备
     *
     * @param userId 用户唯一标识
     */
    fun findDeviceById(userId: Long): List<DeviceInfo>
}