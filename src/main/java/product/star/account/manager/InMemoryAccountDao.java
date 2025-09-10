package product.star.account.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryAccountDao implements AccountDao{

    private static final Logger logger = LoggerFactory.getLogger(InMemoryAccountDao.class);

    private long accountId = 1L;
    private final Map<Long, Account> accountIdMap;

    public InMemoryAccountDao() {
        this.accountIdMap = new HashMap<>();
    }

    @Override
    public Account addAccount(long amount) {
        Account account = new Account(accountId, amount);
        accountIdMap.put(accountId++, account);
        logger.info("Added account with id {} and amount {}", account.getId(), amount);
        return account;
    }

    @Override
    public Optional<Account> findAccount(long accountId) {
        logger.debug("Finding account by id {}", accountId);
        return Optional.ofNullable(accountIdMap.get(accountId));
    }

    @Override
    public Account getAccount(long accountId) {
        logger.debug("Getting account by id {}", accountId);
        Account account = findAccount(accountId)
                .orElseThrow(() -> {
                    logger.error("Account not found: {}", accountId);
                    return new IllegalArgumentException("Account not found: " + accountId);
                });
        return account;
    }

    @Override
    public void setAmount(long accountId, long amount) {
        logger.debug("Setting amount {} for account {}", amount, accountId);
        var account = getAccount(accountId);
        account.setAmount(amount);
        logger.info("Updated account {} amount to {}", accountId, amount);
    }
}
