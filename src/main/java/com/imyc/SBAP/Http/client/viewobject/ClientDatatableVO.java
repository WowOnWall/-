package com.imyc.SBAP.Http.client.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ClientDatatableVO {

    private Integer draw;              // datatable 请求的次数，方便前端同步
    private Long recordsTotal;         // 总数据条数
    private Long recordsFiltered;      // 过滤后条数（这里简化处理和总数相同）
    private List<ClientReadVO> data;   // 当前页数据列表

}
