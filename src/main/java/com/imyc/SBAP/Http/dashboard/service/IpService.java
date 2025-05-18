package com.imyc.SBAP.Http.dashboard.service;

import com.imyc.SBAP.Http.dashboard.viewobject.ipVO;

public interface IpService {
    ipVO lookup(String ip);
}
