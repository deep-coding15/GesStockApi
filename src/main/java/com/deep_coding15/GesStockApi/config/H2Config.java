package com.deep_coding15.GesStockApi.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.sql.SQLException;

@Configuration
@Profile("!test")
public class H2Config {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer("-tcpAllowOthers", "-tcpPort", "9092");
    }
}

