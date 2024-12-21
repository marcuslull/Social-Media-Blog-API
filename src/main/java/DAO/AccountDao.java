package DAO;

import Model.Account;

import java.util.Optional;

/**
 * Data Access Object for Accounts.  Provides methods to interact with the database
 * for account related operations.
 * ALL JAVA DOCS GENERATED WITH AI
 */
public interface AccountDao {

    /**
     * Creates a new account in the database.
     *
     * @param account The Account object to be created.
     * @return An Optional containing the newly created Account object, including the generated account ID,
     *         or an empty Optional if the account creation fails.
     */
    Optional<Account> createAccount(Account account);

    /**
     * Retrieves an account from the database based on the provided username.
     *
     * @param username The username of the account to retrieve.
     * @return An Optional containing the Account object if found, or an empty Optional if no account
     *         with the given username exists.
     */
    Optional<Account> getAccountByUsername(String username);

    /**
     * Retrieves an account from the database based on the provided account ID.
     *
     * @param userId The ID of the account to retrieve.
     * @return An Optional containing the Account object if found, or an empty Optional if no account
     *         with the given ID exists.
     */
    Optional<Account> getAccountById(int userId);
}
