package com.keepsafe.bankcardserver.data.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("devices")
@Data
public class DeviceInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String brand;

    private String model;

    private String uuid;

    @TableField("modify_date")
    private LocalDateTime modifyDate;

    @TableField("create_date")
    private LocalDateTime createDate;
}
