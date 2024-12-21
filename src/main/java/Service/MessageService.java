package Service;

import Model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<Message> createNewMessage(Message message);
    List<Message> getAllMessages();
    Optional<Message> getMessageById(int messageId);
    List<Message> getAllUserMessages(int accountId);
    Optional<Message> updateMessageText(int messageId, String text);
    void deleteMessageById(int messageId);
}
