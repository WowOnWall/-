package com.imyc.SBAP.Http.client.viewobject;

import com.imyc.SBAP.Http.client.dao.Client;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class ClientRowVO {
    private Long id;
    private String ip;
    private String platform;
    private String userAgent;
    private String connectionType;
    private String country;
    private String region;
    private String city;
    private LocalDateTime lastAccessTime;
    private String screenResolution;
    private Integer cpuCores;
    private Float deviceMemory;
    private String viewportSize;
    // 计算字段（用于前端直接展示）
    public String getGeoLocation() {
        return String.join(" / ",
                nonNull(country),
                nonNull(region),
                nonNull(city)
        );
    }

    public String getDeviceSummary() {
        return String.format("%s | %d核 %.1fGB",
                nonNull(platform),
                cpuCores != null ? cpuCores : 0,
                deviceMemory != null ? deviceMemory : 0
        );
    }

    public String getNetworkInfo() {
        return String.format("%s (%s)",
                nonNull(connectionType),
                nonNull(ip)
        );
    }

    public String getDisplayInfo() {
        return String.format("%s (Viewport: %s)",
                nonNull(screenResolution),
                nonNull(viewportSize)
        );
    }

    // 辅助方法
    private String nonNull(String value) {
        return value != null ? value : "N/A";
    }

    // 示例静态构造方法
    public static ClientRowVO fromEntity(Client entity) {
        return new ClientRowVO()
                .setId(entity.getId())
                .setIp(entity.getIp())
                .setPlatform(entity.getPlatform())
                .setUserAgent(entity.getUserAgent())
                .setConnectionType(entity.getConnectionType())
                .setCountry(entity.getCountry())
                .setRegion(entity.getRegion())
                .setCity(entity.getCity())
                .setLastAccessTime(entity.getLastAccessTime())
                .setScreenResolution(entity.getScreenResolution())
                .setCpuCores(Integer.valueOf(entity.getCpuCores()))
                .setDeviceMemory(entity.getDeviceMemory())
                .setViewportSize(entity.getViewportSize());
    }
}