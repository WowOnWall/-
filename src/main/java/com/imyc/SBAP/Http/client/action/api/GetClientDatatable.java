package com.imyc.SBAP.Http.client.action.api;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.client.services.requester.contracts.ClientIndexRequester;
import com.imyc.SBAP.Http.client.viewobject.ClientDatatableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetClientDatatable {

    private final ClientIndexRequester clientIndexRequester;
    private DatatableServerSideConfig datatableServerSideConfig;

    @Autowired
    public GetClientDatatable(@Qualifier("ClientDatatableProvider") ClientIndexRequester clientIndexRequester) {
        this.clientIndexRequester = clientIndexRequester;
    }

    @GetMapping("/api/datatable/client")
    public ResponseEntity<ClientDatatableVO> handle(
            @RequestParam int draw,
            @RequestParam int start,
            @RequestParam int length,
            @RequestParam(name = "search[value]", required = false) String keyword) {

        datatableServerSideConfig = new DatatableServerSideConfig();
        datatableServerSideConfig
                .setDraw(draw)
                .setStart(start)
                .setLength(length)
                .setKeyword(keyword != null ? keyword.trim() : "");

        ClientDatatableVO result = clientIndexRequester.indexResponse(datatableServerSideConfig);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
