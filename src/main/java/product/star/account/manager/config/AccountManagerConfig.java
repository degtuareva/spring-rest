package product.star.account.manager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import product.star.account.manager.AccountDao;
import product.star.account.manager.InMemoryAccountDao;

@Configuration
@EnableJpaRepositories(basePackages = "product.star.contact.manager")
@EntityScan(basePackages = "product.star.contact.manager")
public class AccountManagerConfig {

    private static final Logger logger = LoggerFactory.getLogger(AccountManagerConfig.class);


    @Bean
    public AccountDao accountDao() {
        logger.info("Creating InMemoryAccountDao bean");
        return new InMemoryAccountDao();
    }
}