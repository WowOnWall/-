// ClientController.java
package com.imyc.SBAP.Http.client.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    @GetMapping("/client")
    public String render() {
        return "admin-panel/client/index";
    }

}