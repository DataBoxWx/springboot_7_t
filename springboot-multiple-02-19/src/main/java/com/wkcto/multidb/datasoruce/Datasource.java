package com.wkcto.multidb.datasoruce;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:Datasource
 * package:com.wkcto.multidb.datasoruce
 * Description:
 *
 * @Data:2018/8/7 19:40
 * @Auther:wangxin
 */
@Configuration
@MapperScan(basePackages = "com.wkcto.multidb.mapper")
public class Datasource {

    @Value("${spring.datasource.username3307}")
    private String username3307;
    @Value("${spring.datasource.password3307}")
    private String password3307;
    @Value("${spring.datasource.driver-class-name3307}")
    private String driver3307;
    @Value("${spring.datasource.url3307}")
    private String url3307;

    @Bean
    public DruidDataSource dataSource3307(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3307);
        druidDataSource.setDriverClassName(driver3307);
        druidDataSource.setUsername(username3307);
        druidDataSource.setPassword(password3307);
        return druidDataSource;
    }

    @Value("${spring.datasource.username3308}")
    private String username3308;
    @Value("${spring.datasource.password3308}")
    private String password3308;
    @Value("${spring.datasource.driver-class-name3308}")
    private String driver3308;
    @Value("${spring.datasource.url3308}")
    private String url3308;

    @Bean
    public DruidDataSource dataSource3308(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3308);
        druidDataSource.setDriverClassName(driver3308);
        druidDataSource.setUsername(username3308);
        druidDataSource.setPassword(password3308);
        return druidDataSource;
    }

    @Value("${spring.datasource.username3309}")
    private String username3309;
    @Value("${spring.datasource.password3309}")
    private String password3309;
    @Value("${spring.datasource.driver-class-name3309}")
    private String driver3309;
    @Value("${spring.datasource.url3309}")
    private String url3309;

    @Bean
    public DruidDataSource dataSource3309(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3309);
        druidDataSource.setDriverClassName(driver3309);
        druidDataSource.setUsername(username3309);
        druidDataSource.setPassword(password3309);
        return druidDataSource;
    }

    @Value("${spring.datasource.username3310}")
    private String username3310;
    @Value("${spring.datasource.password3310}")
    private String password3310;
    @Value("${spring.datasource.driver-class-name3310}")
    private String driver3310;
    @Value("${spring.datasource.url3310}")
    private String url3310;

    @Bean
    public DruidDataSource dataSource3310(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3310);
        druidDataSource.setDriverClassName(driver3310);
        druidDataSource.setUsername(username3310);
        druidDataSource.setPassword(password3310);
        return druidDataSource;
    }

    @Bean
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> map = new HashMap<>();
        map.put("3307",dataSource3307());
        map.put("3308",dataSource3308());
        map.put("3309",dataSource3309());
        map.put("3310",dataSource3310());
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }
    /**
     <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
     <property name="dataSource" ref="dynamicDataSource" />
     </bean>
     */

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sqlSessionTemplate;
    }

}
