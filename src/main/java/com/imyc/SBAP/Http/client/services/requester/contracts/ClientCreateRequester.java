package com.imyc.SBAP.Http.client.services.requester.contracts;

import com.imyc.SBAP.Http.client.dto.ClientCreateDTO;
import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Http.client.viewobject.ClientCreateVO;

public interface ClientCreateRequester {
    ClientCreateVO createResponse();
    boolean createRequest(ClientCreateDTO dto) throws WebCreateDataException;
}