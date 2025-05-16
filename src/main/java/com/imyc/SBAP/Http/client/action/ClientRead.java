package com.imyc.SBAP.Http.client.action;

import com.imyc.SBAP.Http.client.viewobject.ClientReadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Http.client.services.requester.contracts.ClientReadRequester;

@Controller
public class ClientRead {

    private final ClientReadRequester clientReadRequester;

    @Autowired
    public ClientRead(@Qualifier("ClientDatatableProvider") ClientReadRequester clientReadRequester) {
        this.clientReadRequester = clientReadRequester;
    }

    @GetMapping("/client/read/{id}")
    public ModelAndView render(@PathVariable(value="id") int id) throws WebPageNotFoundException {
        ClientReadVO clientReadVO = clientReadRequester.readResponse(id);
        return new ModelAndView("admin-panel/client/read", "clientVO", clientReadVO);
    }
}