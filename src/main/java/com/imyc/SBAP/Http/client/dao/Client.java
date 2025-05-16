package com.imyc.SBAP.Http.client.dao;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_info")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 基础访问信息
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "ip", nullable = false, length = 45)
    private String ip;

    @Column(name = "forwarded_for", length = 255)
    private String forwardedFor;

    // 设备信息
    @Column(name = "user_agent", length = 512)
    private String userAgent;

    @Column(name = "platform", length = 50)
    private String platform;

    @Column(name = "cpu_cores")
    private Byte cpuCores;

    @Column(name = "device_memory")
    private Float deviceMemory;

    // 显示信息
    @Column(name = "screen_resolution", length = 20)
    private String screenResolution;

    @Column(name = "viewport_size", length = 20)
    private String viewportSize;

    // 网络信息
    @Column(name = "connection_type", length = 20)
    private String connectionType;

    @Column(name = "language", length = 50)
    private String language;

    @Column(name = "time_zone", length = 50)
    private String timeZone;

    // 地理信息
    @Column(name = "country", length = 2)
    private String country;

    @Column(name = "region", length = 100)
    private String region;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "latitude", precision = 10, scale = 6)
    private Double latitude;

    @Column(name = "longitude", precision = 10, scale = 6)
    private Double longitude;

    // 其他信息
    @Column(name = "plugins", columnDefinition = "TEXT")
    private String plugins;

    @Column(name = "referer", length = 512)
    private String referer;

    // 系统字段
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public LocalDateTime getLastAccessTime() {
        return createdAt;
    }
}