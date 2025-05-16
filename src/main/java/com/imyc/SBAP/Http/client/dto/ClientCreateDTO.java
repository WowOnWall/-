package com.imyc.SBAP.Http.client.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClientCreateDTO {
    private String ip;
    private String forwardedFor;
    private String connectionType;
    private String language;
    private String timeZone;
    private String platform;
    private String userAgent;
    private Integer cpuCores;
    private Float deviceMemory;
    private String screenResolution;
    private String viewportSize;
    private String country;
    private String region;
    private String city;
    private Double latitude;
    private Double longitude;
    private String plugins;
    private String referer;

    // 方便调用方统一调用getName方法，避免报错（之前provider里用到）
    public String getName() {
        // 如果client_info表里没有name字段，可以返回IP或其它标识代替
        return ip != null ? ip : "";
    }
}
