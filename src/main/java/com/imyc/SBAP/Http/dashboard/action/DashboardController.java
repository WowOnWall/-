package com.imyc.SBAP.Http.dashboard.action;

import com.imyc.SBAP.Http.dashboard.DAO.Repository.ClientInfoRepository;
import com.imyc.SBAP.Http.dashboard.service.IpService;
import com.imyc.SBAP.Http.dashboard.viewobject.ipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Value("${cesiumtoken}")
    private String cesiumToken;

    @Autowired
    private IpService ipService;

    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // 从数据库中获取唯一的 IP 列表
        List<String> ipList = clientInfoRepository.findDistinctIps();

        // 使用 ipService 查询每个 IP 的位置信息
        List<ipVO> ipVOList = ipList.stream()
                .map(ipService::lookup)
                .filter(Objects::nonNull) // 避免 lookup 返回 null
                .collect(Collectors.toList());

        // 传给前端
        model.addAttribute("ipList", ipVOList);
        model.addAttribute("cesiumToken", cesiumToken);
        return "admin-panel/dashboard/index";
    }
}

