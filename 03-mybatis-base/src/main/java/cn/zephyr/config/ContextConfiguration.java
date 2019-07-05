package cn.zephyr.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-05 17:02
 */
@Configuration
@EnableAutoConfiguration(exclude = {MybatisAutoConfiguration.class})
public class ContextConfiguration {

    private SystemConfiguration systemConfiguration;

    @Bean
    public SystemConfiguration systemConfiguration(){
        systemConfiguration = new SystemConfiguration();
        return systemConfiguration;
    }

    /**
     * Datasource
     * @return
     */
    @Bean
    public DataSource dataSource(){
        System.err.println("============datasource============");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(systemConfiguration.getDatabaseUsername());
        dataSource.setPassword(systemConfiguration.getDatabasePassword());
        dataSource.setDriverClassName(systemConfiguration.getDatabaseDriverName());
        dataSource.setUrl(systemConfiguration.getDatabaseUrl());
        return dataSource;
    }

    /**
     * SqlSessionFactory，配置mapper.xml的扫描位置
     * @param dataSource
     * @return
     */
    @Bean(name="sqlSessionFactory")
    SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        System.err.println("============sqlSessionFactory============");
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        ssfb.setTypeAliasesPackage("cn.zephyr.entity");
        return ssfb;
    }

    /**
     * 事务管理器
     * @param dataSource
     * @return
     */
    @Bean
    PlatformTransactionManager transactionManager (DataSource dataSource){
        System.err.println("============transactionManager initlized============");
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    /**
     * DAO接口所在包名
     * @return
     */
    @Bean
    MapperScannerConfigurer mpperScannnerConfigurer(){
        System.err.println("============mapper scanner============");
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        msc.setBasePackage("cn.zephyr.mapper");
        return msc;
    }


}
