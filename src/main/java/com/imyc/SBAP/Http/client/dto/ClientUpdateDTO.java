package com.imyc.SBAP.Http.client.dto;

import lombok.Data;

@Data
public class ClientUpdateDTO {
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

    // 方便调用方统一调用getName方法，避免报错
    public String getName() {
        return ip != null ? ip : "";
    }
}
