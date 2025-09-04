package product.star.account.manager.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import product.star.account.manager.AccountDao;
import product.star.account.manager.AccountService;
import product.star.account.manager.BlocklistResolver;
import product.star.account.manager.InMemoryAccountDao;
import product.star.account.manager.InMemoryAccountService;
import product.star.account.manager.InMemoryBlocklistResolver;
import product.star.account.manager.InMemoryPhoneToAccountResolver;
import product.star.account.manager.PhoneToAccountResolver;
import product.star.contact.manager.ContactDao;
import product.star.contact.manager.ContactDaoImpl;

import javax.sql.DataSource;
import java.util.Set;

@Configuration
@Import(PropertiesConfiguration.class)
public class AccountManagerConfig {

    @Value("#{'${blocked.accounts}'.split(',')}")
    private Set<Long> blockedAccounts;

    @Bean
    public AccountDao accountDao() {
        return new InMemoryAccountDao();
    }

    @Bean
    public PhoneToAccountResolver phoneToAccountResolver() {
        return new InMemoryPhoneToAccountResolver();
    }

    @Bean
    public BlocklistResolver blocklistResolver() {
        return new InMemoryBlocklistResolver(
                blockedAccounts
        );
    }

    @Bean
    public AccountService accountService() {
        return new InMemoryAccountService(
                phoneToAccountResolver(),
                accountDao(),
                blocklistResolver()
        );
    }

    // Добавьте здесь объявление SessionFactory, например:
    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("product.star.contact.manager"); // пакет с entity Contact
        // Здесь можно добавить настройки Hibernate properties, если нужно
        return sessionFactory;
    }

    @Bean
    @Autowired
    public ContactDao contactDao(SessionFactory sessionFactory) {
        return new ContactDaoImpl(sessionFactory);
    }
}
