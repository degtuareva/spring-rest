package product.star.account.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class InMemoryBlocklistResolver implements BlocklistResolver {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryBlocklistResolver.class);

    private final Set<Long> blockedAccounts;

    public InMemoryBlocklistResolver(Set<Long> blockedAccounts) {
        this.blockedAccounts = blockedAccounts;
    }

    @Override
    public boolean isBlocklisted(long accountId) {
        boolean blocked = blockedAccounts.contains(accountId);
        logger.debug("Checking if account {} is blocklisted: {}", accountId, blocked);
        return blocked;
    }
}