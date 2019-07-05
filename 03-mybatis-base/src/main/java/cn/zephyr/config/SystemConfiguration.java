package cn.zephyr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: laizonghao
 * @Description: 系统配置的统一管理（application.properties）
 * @Date: 2019-07-05 17:16
 */
@Data
@Component
public class SystemConfiguration {

    @Value("${spring.datasource.username}")
    private String databaseUsername;
    @Value("${spring.datasource.password}")
    private String databasePassword;
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String databaseDriverName;

}
