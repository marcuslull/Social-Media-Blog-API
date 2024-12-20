package DAO;

import Model.Account;

public interface AccountDao {
    Account createAccount(Account account);
    Account getAccountByUsername(String username);
}
