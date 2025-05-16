// ClientRepository.java
package com.imyc.SBAP.Http.client.dao.Repository;

import com.imyc.SBAP.Http.client.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // 自定义查询示例
    // List<Client> findByPlatform(String platform);
}