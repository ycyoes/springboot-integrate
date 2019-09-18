package com.turing.springbootintegrate.config.multisource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 动态数据源上下文，用于进行数据源的切换
 * @author ycyoes
 * @version 2019-09-18
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
        //将master数据源的key作为默认数据源的key
        protected String initialValues() {
            return "master";
        }
    };

    //数据源key，用于切换时判断数据源是否存在
    public static List<Object> dataSourceKeys = new ArrayList<Object>();

    /**
     * 切换数据源
     * @param key
     */
    public static void setDataSourceKey(String key) {
        contextHolder.set(key);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSourceKey() {
        return contextHolder.get();
    }

    //重置数据源
    public static void clearDataSourceKey() {
        contextHolder.remove();;
    }

    /**
     * 判断是否包含数据源
     * @param key   数据源key
     * @return
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }

    /**
     * 添加数据源keys
     * @param keys
     * @return
     */
    public static boolean addDataSourceKeys(Collection<? extends Object> keys) {
        return dataSourceKeys.addAll(keys);
    }
}
