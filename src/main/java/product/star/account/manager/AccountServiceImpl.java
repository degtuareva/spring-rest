package product.star.account.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(long from, long to, long amount) {
        logger.debug("Start transfer from {} to {} with amount {}", from, to, amount);

        Account fromAccount = accountDao.getAccount(from);
        Account toAccount = accountDao.getAccount(to);

        if (fromAccount == null || toAccount == null) {
            logger.error("Account not found: from={} or to={}", from, to);
            throw new IllegalArgumentException("Account not found");
        }
        if (fromAccount.getAmount() < amount) {
            logger.warn("Insufficient funds in account {}", from);
            throw new IllegalArgumentException("Insufficient funds");
        }

        fromAccount.setAmount(fromAccount.getAmount() - amount);
        toAccount.setAmount(toAccount.getAmount() + amount);

        accountDao.setAmount(fromAccount.getId(), fromAccount.getAmount());
        accountDao.setAmount(toAccount.getId(), toAccount.getAmount());

        logger.info("Transfer successful: from {} to {} amount {}", from, to, amount);
    }

    @Override
    public void transferByPhoneNumber(long from, String phoneNumber, long amount) {
        logger.debug("Start transferByPhoneNumber from {} to phone {} amount {}", from, phoneNumber, amount);
        long toAccountId = resolveAccountIdByPhoneNumber(phoneNumber);
        transfer(from, toAccountId, amount);
        logger.info("transferByPhoneNumber successful from {} to phone {} amount {}", from, phoneNumber, amount);
    }

    private long resolveAccountIdByPhoneNumber(String phoneNumber) {
        logger.debug("Resolving account ID by phone number: {}", phoneNumber);
        throw new UnsupportedOperationException("Not implemented");
    }
}
