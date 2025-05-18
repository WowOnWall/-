package com.imyc.SBAP.Http.dashboard.DAO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_info")
public class ipInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @Column(length = 45, nullable = false)
    private String ip;

    @Column(name = "forwarded_for", length = 255)
    private String forwardedFor;

    @Column(name = "user_agent", length = 512)
    private String userAgent;

    @Column(length = 50)
    private String language;

    @Column(name = "screen_resolution", length = 20)
    private String screenResolution;

    @Column(name = "time_zone", length = 50)
    private String timeZone;

    @Column(name = "viewport_size", length = 20)
    private String viewportSize;

    @Column(name = "connection_type", length = 20)
    private String connectionType;

    @Column(length = 50)
    private String platform;

    @Column(name = "cpu_cores")
    private Byte cpuCores;

    @Column(name = "device_memory")
    private Float deviceMemory;

    @Lob
    private String plugins;

    @Column(length = 512)
    private String referer;

    @Column(length = 2)
    private String country;

    @Column(length = 100)
    private String region;

    @Column(length = 100)
    private String city;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // ======= Getters & Setters（可用 IDE 自动生成）=======

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getForwardedFor() {
        return forwardedFor;
    }

    public void setForwardedFor(String forwardedFor) {
        this.forwardedFor = forwardedFor;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getViewportSize() {
        return viewportSize;
    }

    public void setViewportSize(String viewportSize) {
        this.viewportSize = viewportSize;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Byte getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(Byte cpuCores) {
        this.cpuCores = cpuCores;
    }

    public Float getDeviceMemory() {
        return deviceMemory;
    }

    public void setDeviceMemory(Float deviceMemory) {
        this.deviceMemory = deviceMemory;
    }

    public String getPlugins() {
        return plugins;
    }

    public void setPlugins(String plugins) {
        this.plugins = plugins;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 无需 setter，created_at 由数据库自动生成
}
