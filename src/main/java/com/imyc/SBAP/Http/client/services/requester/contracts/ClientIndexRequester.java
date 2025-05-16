package com.imyc.SBAP.Http.client.services.requester.contracts;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.client.viewobject.ClientDatatableVO;

public interface ClientIndexRequester {
    ClientDatatableVO indexResponse(DatatableServerSideConfig config);
}