package com.wkcto.multidb.datasoruce;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ClassName:DynamicDataSource
 * package:com.wkcto.multiple.datasource
 * Description:
 *
 * @Data:2018/8/7 9:22
 * @Auther:wangxin
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String DB3307 = "3307";
    public static final String DB3308 = "3309";
    public static final String DB3309 = "3309";
    public static final String DB3310 = "3310";

    protected Object determineCurrentLookupKey() {
        return ThreadLocalHolder.getHolder();
    }
}
