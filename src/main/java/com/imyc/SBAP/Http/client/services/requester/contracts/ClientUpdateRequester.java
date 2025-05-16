package com.imyc.SBAP.Http.client.services.requester.contracts;

import com.imyc.SBAP.Http.client.dto.ClientUpdateDTO;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Exception.web.WebUpdateDataException;
import com.imyc.SBAP.Http.client.viewobject.ClientUpdateVO;

public interface ClientUpdateRequester {
    ClientUpdateVO updateResponse(int id) throws WebPageNotFoundException;
    boolean updateRequest(ClientUpdateDTO dto, int id) throws WebUpdateDataException;
}