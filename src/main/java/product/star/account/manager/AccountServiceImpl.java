package product.star.account.manager;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    private final Map<Long, Account> accounts = new HashMap<>();

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(long from, long to, long amount) {
        Account fromAccount = accountDao.getAccount(from);
        Account toAccount = accountDao.getAccount(to);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (fromAccount.getAmount() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        fromAccount.setAmount(fromAccount.getAmount() - amount);
        toAccount.setAmount(toAccount.getAmount() + amount);

        accountDao.setAmount(fromAccount.getId(), fromAccount.getAmount());
        accountDao.setAmount(toAccount.getId(), toAccount.getAmount());
    }

    @Override
    public void transferByPhoneNumber(long from, String phoneNumber, long amount) {
        long toAccountId = resolveAccountIdByPhoneNumber(phoneNumber);
        transfer(from, toAccountId, amount);
    }

    private long resolveAccountIdByPhoneNumber(String phoneNumber) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
