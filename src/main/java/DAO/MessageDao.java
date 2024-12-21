package DAO;

import Model.Message;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object for Messages.  Provides methods to interact with the database
 * for message related operations.
 * ALL JAVA DOCS GENERATED WITH AI
 */
public interface MessageDao {

    /**
     * Creates a new message in the database.
     *
     * @param message The Message object to be created.
     * @return An Optional containing the newly created Message object, including the generated message ID,
     *         or an empty Optional if the message creation fails.
     */
    Optional<Message> createMessage(Message message);

    /**
     * Retrieves all messages from the database.
     *
     * @return A List of all Message objects in the database.  Returns an empty list if the database
     *         contains no messages or if an error occurs during retrieval.
     */
    List<Message> getAllMessages();

    /**
     * Retrieves a message from the database based on its ID.
     *
     * @param id The ID of the message to retrieve.
     * @return An Optional containing the Message object if found, or an empty Optional if no message
     *         with the given ID exists.
     */
    Optional<Message> getMessageById(int id);

    /**
     * Retrieves all messages posted by a specific account from the database.
     *
     * @param accountId The ID of the account whose messages are to be retrieved.
     * @return A List of Message objects posted by the specified account. Returns an empty list if the account
     *         has not posted any messages or if an error occurs during retrieval.
     */
    List<Message> getMessagesByAccount(int accountId);

    /**
     * Updates the text of a message in the database.
     *
     * @param id The ID of the message to update.
     * @param newMessageText The new text for the message.
     * @return An Optional containing the updated Message object if successful, or an empty Optional if
     *         the update fails or if no message with the given ID exists.
     */
    Optional<Message> updateMessageById(int id, String newMessageText);

    /**
     * Deletes a message from the database based on its ID.
     *
     * @param id The ID of the message to delete.
     */
    void deleteMessageById(int id);
}
