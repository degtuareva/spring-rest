package product.star.account.manager.config;

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
    @Bean
    public AccountDao accountDao() {
        return new InMemoryAccountDao();
    }
}