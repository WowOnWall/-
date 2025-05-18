package com.imyc.SBAP.Http.dashboard.viewobject;

public class ipVO {
    private String ip;
    private String country;
    private String region;
    private String city;
    private double latitude;
    private double longitude;

    // Getters and setters
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}
