package DAO;

import Model.Message;

import java.util.List;

public interface MessageDao {
    Message createMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(int id);
    List<Message> getMessagesByUser(int id);
    boolean updateMessageById(int id, String newMessageText);
    boolean deleteMessageById(int id);
}
