package Service;

import Model.Message;

import java.util.List;
import java.util.Optional;

/**
 * Service object for Messages. Provides methods to interact with the controller
 * and Message DAO for Message related operations.
 * ALL JAVA DOCS GENERATED WITH AI
 */
public interface MessageService {

    /**
     * Creates a new message.
     *
     * @param message The Message object to be created.
     * @return An Optional containing the newly created Message object, including the generated message ID,
     *         or an empty Optional if message creation fails.
     */
    Optional<Message> createNewMessage(Message message);

    /**
     * Retrieves all messages.
     *
     * @return A List of all Message objects, or an empty list if no messages exist.
     */
    List<Message> getAllMessages();

    /**
     * Retrieves a message by its ID.
     *
     * @param messageId The ID of the message to retrieve.
     * @return An Optional containing the Message object if found, or an empty Optional if no message with the given ID exists.
     */
    Optional<Message> getMessageById(int messageId);

    /**
     * Retrieves all messages posted by a specific account.
     *
     * @param accountId The ID of the account whose messages are to be retrieved.
     * @return A List of Message objects posted by the specified account, or an empty list if the account has not posted any messages.
     */
    List<Message> getAllUserMessages(int accountId);

    /**
     * Updates the text of a message.
     *
     * @param messageId The ID of the message to update.
     * @param text The new text for the message.
     * @return An Optional containing the updated Message object if successful, or an empty Optional if the update fails or the message does not exist.
     */
    Optional<Message> updateMessageText(int messageId, String text);

    /**
     * Deletes a message by its ID.
     *
     * @param messageId The ID of the message to delete.
     */
    void deleteMessageById(int messageId);
}
