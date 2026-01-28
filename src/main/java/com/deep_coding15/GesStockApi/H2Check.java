package com.deep_coding15.GesStockApi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class H2Check {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void check() throws Exception {
        System.out.println(
            "Datasource = " + dataSource.getConnection().getMetaData().getURL()
        );
    }
}

