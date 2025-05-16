package com.imyc.SBAP.Http.client.viewobject;

import com.imyc.SBAP.Http.client.dao.Client;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ClientUpdateVO {
    private Long id;

    // 基础信息
    private String ip;
    private String platform;
    private String userAgent;
    private String connectionType;

    // 设备信息
    private Integer cpuCores;
    private Float deviceMemory;
    private String screenResolution;
    private String viewportSize;

    // 地理位置
    private String country;
    private String region;
    private String city;
    private Double latitude;
    private Double longitude;

    // 静态选项数据（用于表单下拉框）
    private List<OptionVO> platformOptions;
    private List<OptionVO> connectionTypeOptions;
    private List<OptionVO> countryOptions;

    // 嵌套选项类
    @Getter
    @Setter
    public static class OptionVO {
        private String value;
        private String label;

        public OptionVO(String value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    // 实体转换方法
    public static ClientUpdateVO fromEntity(Client entity) {
        return new ClientUpdateVO()
                .setId(entity.getId())
                .setIp(entity.getIp())
                .setPlatform(entity.getPlatform())
                .setUserAgent(entity.getUserAgent())
                .setConnectionType(entity.getConnectionType())
                .setCpuCores(Integer.valueOf(entity.getCpuCores()))
                .setDeviceMemory(entity.getDeviceMemory())
                .setScreenResolution(entity.getScreenResolution())
                .setViewportSize(entity.getViewportSize())
                .setCountry(entity.getCountry())
                .setRegion(entity.getRegion())
                .setCity(entity.getCity())
                .setLatitude(entity.getLatitude())
                .setLongitude(entity.getLongitude());
    }

    // 初始化选项方法（可在Service层调用）
    public ClientUpdateVO initOptions() {
        this.platformOptions = List.of(
                new OptionVO("windows", "Windows"),
                new OptionVO("macos", "macOS"),
                new OptionVO("android", "Android")
        );

        this.connectionTypeOptions = List.of(
                new OptionVO("wifi", "Wi-Fi"),
                new OptionVO("4g", "4G"),
                new OptionVO("ethernet", "Ethernet")
        );

        this.countryOptions = List.of(
                new OptionVO("CN", "中国"),
                new OptionVO("US", "美国"),
                new OptionVO("JP", "日本")
        );

        return this;
    }
}