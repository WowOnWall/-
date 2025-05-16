package com.imyc.SBAP.Http.client.services.dataprocess;

import com.imyc.SBAP.Http.client.dao.Client;
import com.imyc.SBAP.Http.client.dao.Repository.ClientRepository;
import com.imyc.SBAP.Http.client.viewobject.ClientReadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientDPO {

    private final ClientRepository clientRepo;

    @Autowired
    public ClientDPO(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Optional<ClientReadVO> getClientById(Long id) {
        Optional<Client> optionalClient = clientRepo.findById(id);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            ClientReadVO vo = new ClientReadVO()
                    .setId(client.getId())
                    .setTimestamp(client.getTimestamp())
                    .setCreatedAt(client.getCreatedAt())
                    .setIp(client.getIp())
                    .setForwardedFor(client.getForwardedFor())
                    .setConnectionType(client.getConnectionType())
                    .setLanguage(client.getLanguage())
                    .setTimeZone(client.getTimeZone())
                    .setPlatform(client.getPlatform())
                    .setUserAgent(client.getUserAgent())
                    .setCpuCores(Integer.valueOf(client.getCpuCores()))
                    .setDeviceMemory(client.getDeviceMemory())
                    .setScreenResolution(client.getScreenResolution())
                    .setViewportSize(client.getViewportSize())
                    .setCountry(client.getCountry())
                    .setRegion(client.getRegion())
                    .setCity(client.getCity())
                    .setLatitude(client.getLatitude())
                    .setLongitude(client.getLongitude())
                    .setPlugins(client.getPlugins())
                    .setReferer(client.getReferer());

            return Optional.of(vo);
        } else {
            return Optional.empty();
        }
    }
}
