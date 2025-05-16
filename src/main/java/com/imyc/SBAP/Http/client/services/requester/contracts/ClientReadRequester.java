package com.imyc.SBAP.Http.client.services.requester.contracts;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Http.client.viewobject.ClientReadVO;

public interface ClientReadRequester {
    ClientReadVO readResponse(int id) throws WebPageNotFoundException;
}