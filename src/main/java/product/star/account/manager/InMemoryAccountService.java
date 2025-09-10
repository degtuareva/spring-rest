package product.star.account.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryAccountService implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryAccountService.class);

    private final PhoneToAccountResolver phoneToAccountResolver;
    private final AccountDao accountDao;
    private final BlocklistResolver blocklistResolver;

    public InMemoryAccountService(PhoneToAccountResolver phoneToAccountResolver, AccountDao accountDao, BlocklistResolver blocklistResolver) {
        this.phoneToAccountResolver = phoneToAccountResolver;
        this.accountDao = accountDao;
        this.blocklistResolver = blocklistResolver;
    }

    @Override
    public void transfer(long fromId, long toId, long amount) {
        logger.debug("Transfer started from {} to {} amount {}", fromId, toId, amount);
        requireNotBlocked(fromId, toId);
        var accountFrom = accountDao.getAccount(fromId);
        var accountTo = accountDao.getAccount(toId);
        if (accountFrom.getAmount() < amount) {
            logger.warn("Not enough money on account {}. Available: {}, requested: {}", fromId, accountFrom.getAmount(), amount);
            throw new IllegalStateException("Not enough money on account: " + fromId);
        }
        accountDao.setAmount(fromId, accountFrom.getAmount() - amount);
        accountDao.setAmount(toId, accountTo.getAmount() + amount);
        logger.info("Transfer completed: from {} to {} amount {}", fromId, toId, amount);
    }

    void requireNotBlocked(long... accountIds) {
        for (long accountId : accountIds) {
            if (blocklistResolver.isBlocklisted(accountId)) {
                logger.error("Account {} is blocked", accountId);
                throw new IllegalStateException("Account is blocked: " + accountId);
            }
        }
    }

    @Override
    public void transferByPhoneNumber(long fromId, String phoneNumber, long amount) {
        logger.debug("Transfer by phone number started from {} to {} amount {}", fromId, phoneNumber, amount);
        var to = phoneToAccountResolver.findAccountByPhoneNumber(phoneNumber)
                .orElseThrow(() -> {
                    logger.error("Account not found by phone: {}", phoneNumber);
                    return new IllegalArgumentException("Account not found by phone: " + phoneNumber);
                });
        transfer(fromId, to.getId(), amount);
        logger.info("Transfer by phone number completed from {} to {} amount {}", fromId, phoneNumber, amount);
    }
}

