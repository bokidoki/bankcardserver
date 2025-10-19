package com.keepsafe.bankcardserver.service

interface DeviceService {

    /**
     * 绑定设备
     *
     * @param deviceId 设备唯一标识
     * @param userId 用户唯一标识
     */
    fun bindDevice(deviceId: Long, userId: Long)

    /**
     * 解绑设备
     *
     * @param deviceId 设备唯一标识
     * @param userId 用户唯一标识
     */
    fun unbindDevice(deviceId: Long, userId: Long)
}