package com.imyc.SBAP.Http.client.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ClientCreateVO {

    // 表单基础字段
    private String ip;
    private String platform;
    private String userAgent;
    private String connectionType;
    private String screenResolution;
    private Integer cpuCores;
    private Float deviceMemory;

    // 静态选择项数据
    private List<OptionVO> platformOptions;
    private List<OptionVO> connectionTypeOptions;
    private List<OptionVO> screenResolutionOptions;

    // 嵌套选项类
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class OptionVO {
        private String value;
        private String label;

        public OptionVO() {}

        public OptionVO(String value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    // 初始化方法示例（可在Service层调用）
    public ClientCreateVO initOptions() {
        this.platformOptions = List.of(
                new OptionVO("windows", "Windows"),
                new OptionVO("macos", "macOS"),
                new OptionVO("linux", "Linux"),
                new OptionVO("android", "Android"),
                new OptionVO("ios", "iOS")
        );

        this.connectionTypeOptions = List.of(
                new OptionVO("wifi", "Wi-Fi"),
                new OptionVO("4g", "4G"),
                new OptionVO("5g", "5G"),
                new OptionVO("ethernet", "有线网络")
        );

        this.screenResolutionOptions = List.of(
                new OptionVO("1920x1080", "1920×1080 (Full HD)"),
                new OptionVO("2560x1440", "2560×1440 (QHD)"),
                new OptionVO("3840x2160", "3840×2160 (4K)")
        );

        return this;
    }
}