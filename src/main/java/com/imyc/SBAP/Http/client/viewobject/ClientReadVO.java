package com.imyc.SBAP.Http.client.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class ClientReadVO {
    private Long id;
    private LocalDateTime timestamp;
    private LocalDateTime createdAt;

    // 网络信息
    private String ip;
    private String forwardedFor;
    private String connectionType;
    private String language;
    private String timeZone;

    // 设备信息
    private String platform;
    private String userAgent;
    private Integer cpuCores;
    private Float deviceMemory;

    // 显示信息
    private String screenResolution;
    private String viewportSize;

    // 地理位置
    private String country;
    private String region;
    private String city;
    private Double latitude;
    private Double longitude;

    // 其他信息
    private String plugins;
    private String referer;

    // 计算字段（可选）
    public String getFullLocation() {
        return String.join(" / ", country, region, city);
    }

    public String getDeviceSummary() {
        return String.format("%s (%d cores, %.1fGB)",
                platform, cpuCores, deviceMemory);
    }
}