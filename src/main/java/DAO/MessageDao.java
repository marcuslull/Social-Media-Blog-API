package DAO;

import Model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageDao {
    Optional<Message> createMessage(Message message);
    List<Message> getAllMessages();
    Optional<Message> getMessageById(int id);
    List<Message> getMessagesByAccount(int accountId);
    Optional<Message> updateMessageById(int id, String newMessageText);
    void deleteMessageById(int id);
}
