package com.imyc.SBAP.Http.client.services.dataprocess;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.client.dto.ClientCreateDTO;
import com.imyc.SBAP.Http.client.dto.ClientUpdateDTO;
import com.imyc.SBAP.Http.client.viewobject.ClientCreateVO;
import com.imyc.SBAP.Http.client.viewobject.ClientDatatableVO;
import com.imyc.SBAP.Http.client.viewobject.ClientReadVO;
import com.imyc.SBAP.Http.client.viewobject.ClientUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientDatatableDPO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<ClientReadVO> getClientDetailForRead(int id) {
        String sql = "SELECT * FROM client_info WHERE id = ?";

        try {
            ClientReadVO vo = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<ClientReadVO>() {
                @Override
                public ClientReadVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ClientReadVO vo = new ClientReadVO();
                    vo.setId(rs.getLong("id"))
                            .setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime())
                            .setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .setIp(rs.getString("ip"))
                            .setForwardedFor(rs.getString("forwarded_for"))
                            .setConnectionType(rs.getString("connection_type"))
                            .setLanguage(rs.getString("language"))
                            .setTimeZone(rs.getString("time_zone"))
                            .setPlatform(rs.getString("platform"))
                            .setUserAgent(rs.getString("user_agent"))
                            .setCpuCores(getNullableInt(rs, "cpu_cores"))
                            .setDeviceMemory(getNullableFloat(rs, "device_memory"))
                            .setScreenResolution(rs.getString("screen_resolution"))
                            .setViewportSize(rs.getString("viewport_size"))
                            .setCountry(rs.getString("country"))
                            .setRegion(rs.getString("region"))
                            .setCity(rs.getString("city"))
                            .setLatitude(getNullableDouble(rs, "latitude"))
                            .setLongitude(getNullableDouble(rs, "longitude"))
                            .setPlugins(rs.getString("plugins"))
                            .setReferer(rs.getString("referer"));

                    return vo;
                }

                private Integer getNullableInt(ResultSet rs, String columnLabel) throws SQLException {
                    int value = rs.getInt(columnLabel);
                    return rs.wasNull() ? null : value;
                }

                private Float getNullableFloat(ResultSet rs, String columnLabel) throws SQLException {
                    float value = rs.getFloat(columnLabel);
                    return rs.wasNull() ? null : value;
                }

                private Double getNullableDouble(ResultSet rs, String columnLabel) throws SQLException {
                    double value = rs.getDouble(columnLabel);
                    return rs.wasNull() ? null : value;
                }
            });

            return Optional.of(vo);
        } catch (Exception e) {
            return Optional.empty(); // 查询失败或未找到记录
        }
    }

    public ClientDatatableVO getClientDatatableVO(DatatableServerSideConfig config) {
        String baseSql = "SELECT * FROM client_info WHERE 1=1 ";
        List<Object> params = new ArrayList<>();

        // TODO: 根据 config.getSearch() 进行模糊查询过滤（这里先留空）

        // 查询总数
        String countSql = "SELECT COUNT(*) FROM client_info WHERE 1=1 ";
        // 如果有过滤条件，要拼接过滤条件的sql和参数
        Long total = jdbcTemplate.queryForObject(countSql, Long.class);

        // 分页SQL
        String pagedSql = baseSql + " LIMIT ? OFFSET ? ";
        params.add(config.getLength());
        params.add(config.getStart());

        List<ClientReadVO> list = jdbcTemplate.query(pagedSql, params.toArray(), (rs, rowNum) -> {
            ClientReadVO vo = new ClientReadVO();
            vo.setId(rs.getLong("id"))
                    .setTimestamp(rs.getTimestamp("timestamp") != null ? rs.getTimestamp("timestamp").toLocalDateTime() : null)
                    .setCreatedAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                    .setIp(rs.getString("ip"))
                    .setForwardedFor(rs.getString("forwarded_for"))
                    .setConnectionType(rs.getString("connection_type"))
                    .setLanguage(rs.getString("language"))
                    .setTimeZone(rs.getString("time_zone"))
                    .setPlatform(rs.getString("platform"))
                    .setUserAgent(rs.getString("user_agent"))
                    .setCpuCores(rs.getObject("cpu_cores") != null ? rs.getInt("cpu_cores") : null)
                    .setDeviceMemory(rs.getObject("device_memory") != null ? rs.getFloat("device_memory") : null)
                    .setScreenResolution(rs.getString("screen_resolution"))
                    .setViewportSize(rs.getString("viewport_size"))
                    .setCountry(rs.getString("country"))
                    .setRegion(rs.getString("region"))
                    .setCity(rs.getString("city"))
                    .setLatitude(rs.getObject("latitude") != null ? rs.getDouble("latitude") : null)
                    .setLongitude(rs.getObject("longitude") != null ? rs.getDouble("longitude") : null)
                    .setPlugins(rs.getString("plugins"))
                    .setReferer(rs.getString("referer"));
            return vo;
        });

        return new ClientDatatableVO()
                .setDraw(config.getDraw())
                .setRecordsTotal(total)
                .setRecordsFiltered(total)  // 简单示例，无过滤条件，保持一致
                .setData(list);
    }


    // 删除客户端及关联数据示例（假设只删client_info表）
    public boolean deleteClientWithRelationById(int id) {
        String sql = "DELETE FROM client_info WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }

    // 获取创建页需要的数据（如果没有额外要求，直接返回空VO）
    public ClientCreateVO getRequiredDataForCreate() {
        return new ClientCreateVO();
    }

    // 创建新客户端示例
    public boolean clientCreate(ClientCreateDTO dto) {
        String sql = "INSERT INTO client_info (ip, forwarded_for, connection_type, language, time_zone, platform, user_agent, cpu_cores, device_memory, screen_resolution, viewport_size, country, region, city, latitude, longitude, plugins, referer, created_at, timestamp) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
        int rows = jdbcTemplate.update(sql,
                dto.getIp(),
                dto.getForwardedFor(),
                dto.getConnectionType(),
                dto.getLanguage(),
                dto.getTimeZone(),
                dto.getPlatform(),
                dto.getUserAgent(),
                dto.getCpuCores(),
                dto.getDeviceMemory(),
                dto.getScreenResolution(),
                dto.getViewportSize(),
                dto.getCountry(),
                dto.getRegion(),
                dto.getCity(),
                dto.getLatitude(),
                dto.getLongitude(),
                dto.getPlugins(),
                dto.getReferer()
        );
        return rows > 0;
    }

    public Optional<ClientUpdateVO> getClientForUpdate(int id) {
        String sql = "SELECT * FROM client_info WHERE id = ?";
        try {
            ClientUpdateVO vo = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<ClientUpdateVO>() {
                @Override
                public ClientUpdateVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ClientUpdateVO vo = new ClientUpdateVO();
                    vo.setId(rs.getLong("id"))
                            .setIp(rs.getString("ip"))
                            .setPlatform(rs.getString("platform"))
                            .setUserAgent(rs.getString("user_agent"))
                            .setConnectionType(rs.getString("connection_type"))
                            .setCpuCores(getNullableInt(rs, "cpu_cores"))
                            .setDeviceMemory(getNullableFloat(rs, "device_memory"))
                            .setScreenResolution(rs.getString("screen_resolution"))
                            .setViewportSize(rs.getString("viewport_size"))
                            .setCountry(rs.getString("country"))
                            .setRegion(rs.getString("region"))
                            .setCity(rs.getString("city"))
                            .setLatitude(getNullableDouble(rs, "latitude"))
                            .setLongitude(getNullableDouble(rs, "longitude"));

                    // 注意：这里不设置 options，后面会调用 initOptions()
                    return vo;
                }

                private Integer getNullableInt(ResultSet rs, String columnLabel) throws SQLException {
                    int value = rs.getInt(columnLabel);
                    return rs.wasNull() ? null : value;
                }

                private Float getNullableFloat(ResultSet rs, String columnLabel) throws SQLException {
                    float value = rs.getFloat(columnLabel);
                    return rs.wasNull() ? null : value;
                }

                private Double getNullableDouble(ResultSet rs, String columnLabel) throws SQLException {
                    double value = rs.getDouble(columnLabel);
                    return rs.wasNull() ? null : value;
                }
            });

            // 查询完毕后，初始化下拉选项
            vo.initOptions();

            return Optional.of(vo);
        } catch (Exception e) {
            return Optional.empty();
        }
    }



    // 更新客户端示例
    public boolean clientUpdate(ClientUpdateDTO dto, int id) {
        String sql = "UPDATE client_info SET ip=?, forwarded_for=?, connection_type=?, language=?, time_zone=?, platform=?, user_agent=?, cpu_cores=?, device_memory=?, screen_resolution=?, viewport_size=?, country=?, region=?, city=?, latitude=?, longitude=?, plugins=?, referer=?, timestamp=NOW() WHERE id=?";
        int rows = jdbcTemplate.update(sql,
                dto.getIp(),
                dto.getForwardedFor(),
                dto.getConnectionType(),
                dto.getLanguage(),
                dto.getTimeZone(),
                dto.getPlatform(),
                dto.getUserAgent(),
                dto.getCpuCores(),
                dto.getDeviceMemory(),
                dto.getScreenResolution(),
                dto.getViewportSize(),
                dto.getCountry(),
                dto.getRegion(),
                dto.getCity(),
                dto.getLatitude(),
                dto.getLongitude(),
                dto.getPlugins(),
                dto.getReferer(),
                id
        );
        return rows > 0;
    }
}
