package Controller;

import DAO.AccountDao;
import DAO.AccountDaoImpl;
import DAO.MessageDao;
import DAO.MessageDaoImpl;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.AccountServiceImpl;
import Service.MessageService;
import Service.MessageServiceImpl;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    // poor mans dependency injection
    AccountDao accountDao = new AccountDaoImpl();
    AccountService accountService = new AccountServiceImpl(accountDao);
    MessageDao messageDao = new MessageDaoImpl();
    MessageService messageService = new MessageServiceImpl(messageDao, accountService);

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("register", this::postNewAccount);
        app.post("login", this::postLogin);
        app.post("messages", this::postNewMessage);
        app.get("messages", this::getAllMessages);
        app.get("messages/{message_id}", this::getMessageById);
        app.delete("messages/{message_id}", this::deleteMessageById);
        app.patch("messages/{message_id}", this::patchMessageById);
        app.get("accounts/{account_id}/messages", this::getMessagesByUser);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void postNewAccount(Context context) {
        try {

            Account account = objectMapper.readValue(context.body(), Account.class);
            Account returnedAccount = accountService.registerNewUser(account).orElseThrow();
            context.status(200);
            context.json(returnedAccount);

        } catch (JacksonException | NoSuchElementException exception) {
            System.out.println(exception.getMessage());
            context.status(400);
        }
    }

    private void postLogin(Context context) {
        try {

            Account account = objectMapper.readValue(context.body(), Account.class);
            Account returnedAccount = accountService.login(account).orElseThrow();
            context.status(200);
            context.json(returnedAccount);

        } catch (JacksonException | NoSuchElementException exception) {
            System.out.println(exception.getMessage());
            context.status(401);
        }
    }

    private void postNewMessage(Context context) {
        try {

            Message message = objectMapper.readValue(context.body(), Message.class);
            Message returnedMessage = messageService.createNewMessage(message).orElseThrow();
            context.status(200);
            context.json(returnedMessage);

        } catch (JacksonException | NoSuchElementException exception) {
            System.out.println(exception.getMessage());
            context.status(400);
        }
    }

    private void getAllMessages(Context context) {

        List<Message> allMessages = messageService.getAllMessages();
        context.status(200);
        context.json(allMessages);

    }

    private void getMessageById(Context context) {
        try {

            int messageId = Integer.parseInt(context.pathParam("message_id"));
            Message returnedMessage = messageService.getMessageById(messageId).orElseThrow();
            context.status(200);
            context.json(returnedMessage);

        } catch (NoSuchElementException exception) {
            System.out.println(exception.getMessage());
            context.status(200);
        }
    }

    private void deleteMessageById(Context context) {
        try {

            int messageId = Integer.parseInt(context.pathParam("message_id"));
            Message returnedMessage = messageService.getMessageById(messageId).orElseThrow();
            messageService.deleteMessageById(messageId);
            context.status(200);
            context.json(returnedMessage);

        } catch (NoSuchElementException jacksonException) {
            System.out.println(jacksonException.getMessage());
            context.status(200);
        }
    }

    private void patchMessageById(Context context) {
        try {

            int messageId = Integer.parseInt(context.pathParam("message_id"));
            Message message = objectMapper.readValue(context.body(), Message.class);
            Message returnedMessage = messageService.updateMessageText(messageId, message.getMessage_text()).orElseThrow();
            context.status(200);
            context.json(returnedMessage);

        } catch (JacksonException | NoSuchElementException exception) {
            System.out.println(exception.getMessage());
            context.status(400);
        }
    }

    private void getMessagesByUser(Context context) {
        try {

            // get the id from the path
            int accountId = Integer.parseInt(context.pathParam("account_id"));

            // processing
            List<Message> allMessages = messageService.getAllUserMessages(accountId);

            // Object > JSON > Response body
            String jsonString = objectMapper.writeValueAsString(allMessages);
            context.status(200);
            context.json(jsonString);

        } catch (JacksonException jacksonException) {
            System.out.println(jacksonException.getMessage());
        }
    }
}