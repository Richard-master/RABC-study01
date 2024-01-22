package com.example.common.pojo.po;

import java.time.LocalDateTime;

/**
 * 操作时间接口-jsr310时间API
 *
 * @author Jack
 * @date 2022/05/10
 */
public interface Jsr310OperatedTime {

    LocalDateTime getOperatedTime();

    void setOperatedTime(LocalDateTime operatedTime);
}

