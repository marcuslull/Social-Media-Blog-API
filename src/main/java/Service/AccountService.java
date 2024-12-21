package Service;

import Model.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> registerNewUser(Account newUser);
    Optional<Account> login(Account user);
    Optional<Account> getAccountById(int userId);
    boolean accountExists(Account account);
}
