package product.star.account.manager;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class InMemoryPhoneToAccountResolver implements PhoneToAccountResolver {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryPhoneToAccountResolver.class);

    private final Map<String, Account> phoneToAccountMapping;

    public InMemoryPhoneToAccountResolver() {
        this.phoneToAccountMapping = new HashMap<>();
    }

    @Override
    public Optional<Account> findAccountByPhoneNumber(String phoneNumber) {
        logger.debug("Looking for account by phone number: {}", phoneNumber);
        return Optional.ofNullable(phoneToAccountMapping.get(phoneNumber));
    }

    @Override
    public void addMapping(String phoneNumber, Account account) {
        logger.info("Adding mapping phone {} to account {}", phoneNumber, account.getId());
        phoneToAccountMapping.put(phoneNumber, account);
    }

    @Override
    public void removeMapping(String phoneNumber) {
        logger.info("Removing mapping for phone number {}", phoneNumber);
        phoneToAccountMapping.remove(phoneNumber);
    }
}