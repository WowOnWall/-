package com.imyc.SBAP.Http.dashboard.service;

import com.imyc.SBAP.Http.dashboard.viewobject.ipVO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
@Service
public class IpApiServiceImpl implements IpService {

    @Override
    public ipVO lookup(String ip) {
        // ip-api.com 免费 API 请求地址
        String url = "http://ip-api.com/json/" + ip + "?fields=status,country,regionName,city,lat,lon,query";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(response);

        if (!"success".equals(json.getString("status"))) {
            return null;
        }

        ipVO client = new ipVO();
        client.setIp(json.getString("query"));
        client.setCountry(json.getString("country"));
        client.setRegion(json.getString("regionName"));
        client.setCity(json.getString("city"));
        client.setLatitude(json.getDouble("lat"));
        client.setLongitude(json.getDouble("lon"));

        return client;
    }
}
