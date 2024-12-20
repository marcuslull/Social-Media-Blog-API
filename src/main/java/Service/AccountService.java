package Service;

import Model.Account;

public interface AccountService {
    Account registerNewUser(Account newUser);
    Account login(Account user);
    Account getAccountById(int userId);
    boolean accountExists(Account account);
}
