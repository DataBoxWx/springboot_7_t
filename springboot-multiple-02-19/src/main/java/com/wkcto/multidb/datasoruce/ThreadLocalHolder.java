package com.wkcto.multidb.datasoruce;

/**
 * ClassName:ThreadLocalHolder
 * package:com.wkcto.multiple.datasource
 * Description:
 *
 * @Data:2018/8/7 9:38
 * @Auther:wangxin
 */
public class ThreadLocalHolder  {
    public static ThreadLocal<String> holder = new ThreadLocal<String>();

    public static String getHolder() {
        return holder.get();
    }

    public static void setHolder(String dataSourceKey) {
        holder.set(dataSourceKey);
    }
}
