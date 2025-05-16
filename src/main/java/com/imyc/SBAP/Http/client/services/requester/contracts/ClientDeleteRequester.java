package com.imyc.SBAP.Http.client.services.requester.contracts;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;

public interface ClientDeleteRequester {
    boolean deleteRequest(int id) throws WebDeleteDataException;
}