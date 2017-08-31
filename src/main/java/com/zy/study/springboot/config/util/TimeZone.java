package com.zy.study.springboot.config.util;

import java.time.ZoneId;

/**
 * @author liumin
 */
public enum TimeZone {

    ASIA_SHANGHAI("Asia/Shanghai");

    ZoneId id;

    TimeZone(String id) {
        this.id = ZoneId.of(id);
    }

    public ZoneId getId() {
        return id;
    }
}
