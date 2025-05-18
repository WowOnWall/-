package com.imyc.SBAP.Http.dashboard.DAO.Repository;

import com.imyc.SBAP.Http.dashboard.DAO.ipInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientInfoRepository extends JpaRepository<ipInfo, Long> {
    @Query("SELECT DISTINCT c.ip FROM ipInfo c")
    List<String> findDistinctIps();  // 获取唯一 IP 列表
}

