// ClientService.java
package com.imyc.SBAP.Http.client.services;

import com.imyc.SBAP.Http.client.dao.Client;
import com.imyc.SBAP.Http.client.dao.Repository.ClientRepository;
import com.imyc.SBAP.Http.client.viewobject.ClientReadVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientReadVO> getAllClients() {
        List<Client> entities = clientRepository.findAll();
        return entities.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    private ClientReadVO convertToVO(Client entity) {
        return new ClientReadVO()
                .setId(entity.getId())
                .setTimestamp(entity.getTimestamp())
                .setCreatedAt(entity.getCreatedAt())
                // 网络信息
                .setIp(entity.getIp())
                .setForwardedFor(entity.getForwardedFor())
                .setConnectionType(entity.getConnectionType())
                .setLanguage(entity.getLanguage())
                .setTimeZone(entity.getTimeZone())
                // 设备信息
                .setPlatform(entity.getPlatform())
                .setUserAgent(entity.getUserAgent())
                .setCpuCores(Integer.valueOf(entity.getCpuCores()))
                .setDeviceMemory(entity.getDeviceMemory())
                // 显示信息
                .setScreenResolution(entity.getScreenResolution())
                .setViewportSize(entity.getViewportSize())
                // 地理位置
                .setCountry(entity.getCountry())
                .setRegion(entity.getRegion())
                .setCity(entity.getCity())
                .setLatitude(entity.getLatitude())
                .setLongitude(entity.getLongitude())
                // 其他信息
                .setPlugins(entity.getPlugins())
                .setReferer(entity.getReferer());
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ?
                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) :
                "N/A";
    }
}