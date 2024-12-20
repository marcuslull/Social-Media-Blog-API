package Service;

import DAO.AccountDao;
import Model.Account;

import java.util.Objects;

public class AccountServiceImpl implements AccountService {

    // dependency injection
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account registerNewUser(Account newUser) {

        // validation checks
        if (isValidCandidateUser(newUser) && !accountExists(newUser)) {
            return accountDao.createAccount(newUser);
        }
        return null;
    }

    @Override
    public Account login(Account user) {

        // user exists and password is the same
        Account account = accountDao.getAccountByUsername(user.getUsername());
        if (accountExists(user) && Objects.equals(account.getPassword(), user.getPassword())) {
            return account;
        }
        return null;
    }

    @Override
    public Account getAccountById(int userId) {
        return accountDao.getAccountById(userId);
    }

    @Override
    public boolean accountExists(Account user) {
        return accountDao.getAccountByUsername(user.getUsername()) != null;
    }

    private boolean isValidCandidateUser(Account newUser) {
        return  newUser != null &&
                !newUser.getUsername().isEmpty() &&
                !newUser.getPassword().isEmpty() &&
                newUser.getPassword().length() >= 4;
    }
}
