package Service;

import DAO.MessageDao;
import Model.Message;

import java.util.List;
import java.util.Optional;

public class MessageServiceImpl implements MessageService {

    // dependency injection
    private final MessageDao messageDao;
    private final AccountService accountService;

    public MessageServiceImpl(MessageDao messageDao, AccountService accountService) {
        this.messageDao = messageDao;
        this.accountService = accountService;
    }

    @Override
    public Optional<Message> createNewMessage(Message message) {
        if (message == null || !isValidText(message.getMessage_text())) return Optional.empty();
        if (accountService.getAccountById(message.getPosted_by()).isEmpty()) return Optional.empty();
        return messageDao.createMessage(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    @Override
    public Optional<Message> getMessageById(int messageId) {
        return messageDao.getMessageById(messageId);
    }

    @Override
    public List<Message> getAllUserMessages(int accountId) {
        return messageDao.getMessagesByAccount(accountId);
    }

    @Override
    public Optional<Message> updateMessageText(int messageId, String text) {
        if (!isValidText(text)) return Optional.empty();
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
