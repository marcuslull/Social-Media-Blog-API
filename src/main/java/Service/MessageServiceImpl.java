package Service;

import DAO.MessageDao;
import Model.Message;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    // dependency injection
    private final MessageDao messageDao;
    private final AccountService accountService;

    public MessageServiceImpl(MessageDao messageDao, AccountService accountService) {
        this.messageDao = messageDao;
        this.accountService = accountService;
    }

    @Override
    public Message createNewMessage(Message message) {
        if (message == null || !isValidText(message.getMessage_text())) return null;
        if (accountService.getAccountById(message.getPosted_by()) == null) return null;
        return messageDao.createMessage(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    @Override
    public Message getMessageById(int messageId) {
        return messageDao.getMessageById(messageId);
    }

    @Override
    public List<Message> getAllUserMessages(int accountId) {
        return messageDao.getMessagesByAccount(accountId);
    }

    @Override
    public Message updateMessageText(int messageId, String text) {
        if (!isValidText(text)) return null;
        return messageDao.updateMessageById(messageId, text);
    }

    @Override
    public void deleteMessageById(int messageId) {
        messageDao.deleteMessageById(messageId);
    }

    private boolean isValidText(String text){
        return (!text.isEmpty() && text.length() < 256);
    }
}
