package Service;

import DAO.AccountDao;
import Model.Account;

import java.util.Objects;
import java.util.Optional;

/**
 * Service object implementation for Accounts. Provides methods to interact with the controller
 * and Account DAO for Accounts related operations.
 * ALL JAVA DOCS GENERATED WITH AI
 */
public class AccountServiceImpl implements AccountService {

    // dependency injection
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Optional<Account> registerNewUser(Account newUser) {

        // validation checks
        if (isValidCandidateUser(newUser) && !accountExists(newUser)) {
            return accountDao.createAccount(newUser);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> login(Account user) {

        // user exists and password is the same
        Optional<Account> account = accountDao.getAccountByUsername(user.getUsername());
        if (account.isPresent() && Objects.equals(account.get().getPassword(), user.getPassword())) {
            return account;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> getAccountById(int userId) {
        return accountDao.getAccountById(userId);
    }

    @Override
    public boolean accountExists(Account user) {
        return accountDao.getAccountByUsername(user.getUsername()).isPresent();
    }

    private boolean isValidCandidateUser(Account newUser) {
        return  newUser != null &&
                !newUser.getUsername().isEmpty() &&
                !newUser.getPassword().isEmpty() &&
                newUser.getPassword().length() >= 4;
    }
}
