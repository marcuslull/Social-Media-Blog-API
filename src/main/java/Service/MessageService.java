package Service;

import Model.Message;

import java.util.List;

public interface MessageService {
    Message createNewMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(int messageId);
    List<Message> getAllUserMessages(int accountId);
    Message updateMessageText(int messageId, String text);
    void deleteMessageById(int messageId);
}
