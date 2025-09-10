package product.star.account.manager.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.star.account.manager.AccountDao;
import product.star.account.manager.controller.AccountDto;
import product.star.account.manager.controller.TransactionDto;
import product.star.account.manager.controller.TransactionResponse;
import product.star.account.manager.controller.TransactionResult;
import product.star.account.manager.AccountService;

@Service
public class AccountFacade {

    private static final Logger logger = LoggerFactory.getLogger(AccountFacade.class);

    private final AccountDao accountDao;
    private final AccountService accountService;

    @Autowired
    public AccountFacade(AccountDao accountDao, AccountService accountService) {
        this.accountDao = accountDao;
        this.accountService = accountService;
    }

    public AccountDto createAccount(long amount) {
        logger.debug("Facade: creating account with amount {}", amount);
        var account = accountDao.addAccount(amount);
        logger.info("Facade: account created with id {}", account.getId());
        return new AccountDto(account);
    }

    public AccountDto getAccount(long accountId) {
        logger.debug("Facade: fetching account {}", accountId);
        var account = accountDao.getAccount(accountId);
        logger.info("Facade: fetched account {}", accountId);
        return new AccountDto(account);
    }

    public TransactionResponse transfer(TransactionDto transactionDto) {
        logger.debug("Facade: transfer from {} to {} amount {}", transactionDto.getFromId(), transactionDto.getToId(), transactionDto.getAmount());
        accountService.transfer(transactionDto.getFromId(), transactionDto.getToId(), transactionDto.getAmount());
        logger.info("Facade: transfer success");
        return new TransactionResponse(TransactionResult.SUCCESS);
    }
}
