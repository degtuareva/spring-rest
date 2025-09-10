package product.star.account.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionResponse {

    private static final Logger logger = LoggerFactory.getLogger(TransactionResponse.class);
    private final TransactionResult transactionResult;

    public TransactionResponse(TransactionResult transactionResult) {
        this.transactionResult = transactionResult;
        logger.info("TransactionResponse created with result {}", transactionResult);
    }

    public TransactionResult getTransactionResult() {
        return transactionResult;
    }
}
