package Service;

import Model.Account;

public interface AccountService {
    Account registerNewUser(Account newUser);
    Account login(Account user);
}
