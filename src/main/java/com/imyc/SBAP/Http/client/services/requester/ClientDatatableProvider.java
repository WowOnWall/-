package com.imyc.SBAP.Http.client.services.requester;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Exception.web.*;
import com.imyc.SBAP.Http.client.services.dataprocess.ClientDatatableDPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.imyc.SBAP.Http.client.dto.ClientCreateDTO;
import com.imyc.SBAP.Http.client.dto.ClientUpdateDTO;
import com.imyc.SBAP.Http.client.services.requester.contracts.*;
import com.imyc.SBAP.Http.client.viewobject.ClientCreateVO;
import com.imyc.SBAP.Http.client.viewobject.ClientDatatableVO;
import com.imyc.SBAP.Http.client.viewobject.ClientReadVO;
import com.imyc.SBAP.Http.client.viewobject.ClientUpdateVO;

import java.util.Optional;

@Service
@Qualifier(value = "ClientDatatableProvider")
public class ClientDatatableProvider implements ClientCreateRequester, ClientDeleteRequester,
        ClientIndexRequester, ClientReadRequester, ClientUpdateRequester {

    private final ClientDatatableDPO clientDatatableDPO;

    @Autowired
    public ClientDatatableProvider(ClientDatatableDPO clientDatatableDPO) {
        this.clientDatatableDPO = clientDatatableDPO;
    }

    ///////////////////////////
    // Index 列表页
    ///////////////////////////
    @Override
    public ClientDatatableVO indexResponse(DatatableServerSideConfig config) {
        return clientDatatableDPO.getClientDatatableVO(config);
    }

    ///////////////////////////
    // Read 详情页
    ///////////////////////////
    @Override
    public ClientReadVO readResponse(int id) throws WebPageNotFoundException {
        Optional<ClientReadVO> voOptional = clientDatatableDPO.getClientDetailForRead(id);
        return voOptional.orElseThrow(WebPageNotFoundException::new);
    }

    ///////////////////////////
    // Delete 删除操作
    ///////////////////////////
    @Override
    public boolean deleteRequest(int id) throws WebDeleteDataException {
        boolean isDeleted = clientDatatableDPO.deleteClientWithRelationById(id);
        if (!isDeleted) {
            throw new WebDeleteDataException("删除失败，客户端ID: " + id);
        }
        return true;
    }

    ///////////////////////////
    // Create 创建页
    ///////////////////////////
    @Override
    public ClientCreateVO createResponse() {
        return clientDatatableDPO.getRequiredDataForCreate();
    }

    @Override
    public boolean createRequest(ClientCreateDTO dto) throws WebCreateDataException {
        boolean isCreated = clientDatatableDPO.clientCreate(dto);
        if (!isCreated) {
            throw new WebCreateDataException("创建失败: " + dto.getName());
        }
        return true;
    }

    ///////////////////////////
    // Update 编辑页
    ///////////////////////////
    @Override
    public ClientUpdateVO updateResponse(int id) throws WebPageNotFoundException {
        Optional<ClientUpdateVO> voOptional = clientDatatableDPO.getClientForUpdate(id);
        return voOptional.orElseThrow(WebPageNotFoundException::new);
    }

    @Override
    public boolean updateRequest(ClientUpdateDTO dto, int id) throws WebUpdateDataException {
        boolean isUpdated = clientDatatableDPO.clientUpdate(dto, id);
        if (!isUpdated) {
            throw new WebUpdateDataException("更新失败: " + dto.getName());
        }
        return true;
    }
}