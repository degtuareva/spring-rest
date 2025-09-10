package product.star.account.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Account {

    private static final Logger logger = LoggerFactory.getLogger(Account.class);


    private final long id;
    private long amount;

    public Account(long id, long amount) {
        this.id = id;
        this.amount = amount;
        logger.info("Account created with id {}, amount {}", id, amount);
    }

    public long getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {

        logger.debug("Setting amount {} for account id {}", amount, id);this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
