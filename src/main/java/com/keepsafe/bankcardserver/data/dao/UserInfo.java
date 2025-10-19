package com.keepsafe.bankcardserver.data.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@TableName("users")
@Data
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String password;

    @TableField("modify_date")
    private LocalDateTime modifyDate;

    @TableField("create_date")
    private LocalDateTime createDate;

}
