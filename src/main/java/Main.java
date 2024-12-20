import Controller.SocialMediaController;
import DAO.AccountDao;
import DAO.AccountDaoImpl;
import DAO.MessageDao;
import DAO.MessageDoaImpl;
import Model.Account;
import Model.Message;
import Util.ConnectionUtil;
import io.javalin.Javalin;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);


        // TESTING BELOW HERE - REMOVE BEFORE SUBMISSION
        ConnectionUtil.resetTestDatabase();

        AccountDao accountDao = new AccountDaoImpl();
        Account account = accountDao.createAccount(new Account("test", "test"));
        System.out.println("Create account: " + account);

        System.out.println("Get account: " + accountDao.getAccountByUsername("testuser1"));

        MessageDao messageDao = new MessageDoaImpl();
        Message message = messageDao.createMessage(new Message(1, "test message 2", Instant.now().getEpochSecond()));
        System.out.println("Create message: " + message);

        List<Message> messageList = messageDao.getAllMessages();
        System.out.println("Get all: ");
        messageList.forEach(System.out::println);

        System.out.println("Get by ID: " + messageDao.getMessageById(1));

        List<Message> messageList1 = messageDao.getMessagesByAccount(1);
        System.out.println("Get all by account ID: ");
        messageList1.forEach(System.out::println);

        Message message1 = messageDao.updateMessageById(1, "Updated message");
        System.out.println("Updated message: " + message1);
        System.out.println("Verifying update: " + messageDao.getMessageById(1));

        messageDao.deleteMessageById(1);
        List<Message> messageList2 = messageDao.getAllMessages();
        System.out.println("Verifying delete: ");
        messageList2.forEach(System.out::println);


    }
}
