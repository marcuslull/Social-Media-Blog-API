package Service;

import Model.Account;

import java.util.Optional;

/**
 * Service object for Accounts. Provides methods to interact with the controller
 * and Account DAO for Account related operations.
 * ALL JAVA DOCS GENERATED WITH AI
 */
public interface AccountService {

    /**
     * Registers a new user account.
     *
     * @param newUser The Account object representing the new user to register.
     * @return An Optional containing the newly created Account object, including the generated account ID,
     *         or an empty Optional if the registration fails (e.g., username already exists).
     */
    Optional<Account> registerNewUser(Account newUser);

    /**
     * Attempts to log in a user.
     *
     * @param user The Account object containing the username and password to authenticate.
     * @return An Optional containing the Account object if the login is successful, or an empty Optional
     *         if the login fails (e.g., invalid username or password).
     */
    Optional<Account> login(Account user);

    /**
     * Retrieves an account by its ID.
     *
     * @param userId The ID of the account to retrieve.
     * @return An Optional containing the Account object if found, or an empty Optional if no account
     *         with the given ID exists.
     */
    Optional<Account> getAccountById(int userId);

    /**
     * Checks if an account with the given username and password exists in the database.
     *  Primarily used for checking if a user registration can proceed.
     * @param account The Account object containing the username and password to check.
     * @return True if an account with the given credentials exists, false otherwise.
     */
    boolean accountExists(Account account);
}
