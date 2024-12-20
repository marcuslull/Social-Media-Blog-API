package DAO;

import Model.Message;

import java.util.List;

public class MessageDoaImpl implements MessageDao{
    @Override
    public Message createMessage(Message message) {
        return null;
    }

    @Override
    public List<Message> getAllMessages() {
        return List.of();
    }

    @Override
    public Message getMessageById(int id) {
        return null;
    }

    @Override
    public List<Message> getMessagesByUser(int id) {
        return List.of();
    }

    @Override
    public boolean updateMessageById(int id, String newMessageText) {
        return false;
    }

    @Override
    public boolean deleteMessageById(int id) {
        return false;
    }
}
