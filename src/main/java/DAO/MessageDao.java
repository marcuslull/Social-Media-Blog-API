package DAO;

import Model.Message;

import java.util.List;

public interface MessageDao {
    Message createMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(int id);
    List<Message> getMessagesByAccount(int accountId);
    Message updateMessageById(int id, String newMessageText);
    void deleteMessageById(int id);
}
