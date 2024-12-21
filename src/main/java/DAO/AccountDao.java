package DAO;

import Model.Account;

import java.util.Optional;

public interface AccountDao {
    Optional<Account> createAccount(Account account);
    Optional<Account> getAccountByUsername(String username);
    Optional<Account> getAccountById(int userId);
}
