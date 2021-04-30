package com.ssm.Multipledata;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by Mars-Hasee on 2017/8/4.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder. getDbType();
    }
}
