package com.example.lin.config;

/**
 * Created by wujm on 2017/5/16.
 */
public class DataSourceTypeManager {

    private static final ThreadLocal<DataSources> DATASOURCE_TYPES = ThreadLocal.withInitial(()->DataSources.WECHAT);

    public static DataSources get(){
        return DATASOURCE_TYPES.get();
    }

    public static void set(DataSources dataSourceType){
        DATASOURCE_TYPES.set(dataSourceType);
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource(){
        DATASOURCE_TYPES.set(null);
    }

    public static void reset() {
        DATASOURCE_TYPES.set(DataSources.WECHAT);
    }
}
