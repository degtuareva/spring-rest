package product.star.account.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionDto {

    private static final Logger logger = LoggerFactory.getLogger(TransactionDto.class);

    private long fromId;
    private long toId;
    private long amount;

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        logger.debug("Set fromId to {}", fromId);
        this.fromId = fromId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        logger.debug("Set toId to {}", toId);
        this.toId = toId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        logger.debug("Set amount to {}", amount);
        this.amount = amount;
    }
}
