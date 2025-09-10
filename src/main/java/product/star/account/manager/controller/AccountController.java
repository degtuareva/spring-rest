package product.star.account.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import product.star.account.manager.facade.AccountFacade;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountFacade accountFacade;

    @Autowired
    public AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @PostMapping
    public AccountDto createAccount(@RequestParam long amount) {
        logger.info("Creating account with amount {}", amount);
        AccountDto result = accountFacade.createAccount(amount);
        logger.debug("Created account: {}", result.getAccountId());
        return result;
    }

    @GetMapping("/{accountId}")
    public AccountDto getAccount(@PathVariable long accountId) {
        logger.info("Fetching account by id {}", accountId);
        AccountDto result = accountFacade.getAccount(accountId);
        logger.debug("Fetched account details: {}", result);
        return result;
    }

    @PostMapping("/transfers")
    public TransactionResponse transfer(@RequestBody TransactionDto transactionDto) {
        logger.info("Transfer request: from {} to {} amount {}", transactionDto.getFromId(), transactionDto.getToId(), transactionDto.getAmount());
        TransactionResponse response = accountFacade.transfer(transactionDto);
        logger.debug("Transfer response: {}", response.getTransactionResult());
        return response;
    }
}