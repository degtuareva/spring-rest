package product.star.account.manager.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:account-manager.properties")
public class PropertiesConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesConfiguration.class);
    public PropertiesConfiguration() {
        logger.info("PropertiesConfiguration initialized");
    }

}
