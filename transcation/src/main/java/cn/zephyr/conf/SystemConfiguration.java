package cn.zephyr.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

/**
 * @ClassName: SystemConfiguration
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/26 17:44
 */
@Configuration
public class SystemConfiguration {

    @Bean
    @ConditionalOnMissingBean(JpaTransactionManager.class)
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
