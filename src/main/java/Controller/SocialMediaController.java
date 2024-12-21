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

            // JSON > Object
            Account account = objectMapper.readValue(context.body(), Account.class);
            if (account == null) { // invalid body
                context.status(400);
                return;
            }

            // processing
            Account returnedAccount = accountService.registerNewUser(account);
            if (returnedAccount == null) { // invalid user account
                context.status(400);
                return;
            }

            // Object > JSON > Response body
            String jsonString = objectMapper.writeValueAsString(returnedAccount);
            context.status(200);
            context.json(jsonString);

        } catch (JacksonException jacksonException) {
            System.out.println(jacksonException.getMessage());
        }
    }

    private void postLogin(Context context) {
        try {

            // JSON > Object
            Account account = objectMapper.readValue(context.body(), Account.class);
            if (account == null) { // invalid body
                context.status(401);
                return;
            }

            // processing
            Account returnedAccount = accountService.login(account);
            if (returnedAccount == null) { // invalid user account
                context.status(401);
                return;
            }

            // Object > JSON > Response body
            String jsonString = objectMapper.writeValueAsString(returnedAccount);
            context.status(200);
            context.json(jsonString);

        } catch (JacksonException jacksonException) {
            System.out.println(jacksonException.getMessage());
        }
    }

    private void postNewMessage(Context context) {
        try {

            // JSON > Object
            Message message = objectMapper.readValue(context.body(), Message.class);
            if (message == null) { // invalid body
                context.status(400);
                return;
            }

            // processing
            Message returnedMessage = messageService.createNewMessage(message);
            if (returnedMessage == null) { // invalid message
                context.status(400);
                return;
            }

            // Object > JSON > Response body
            String jsonString = objectMapper.writeValueAsString(returnedMessage);
            context.status(200);
            context.json(jsonString);

        } catch (JacksonException jacksonException) {
            System.out.println(jacksonException.getMessage());
        }
    }

    private void getAllMessages(Context context) {
        try {

            // processing
            List<Message> allMessages = messageService.getAllMessages();

            // Object > JSON > Response body
            String jsonString = objectMapper.writeValueAsString(allMessages);
            context.status(200);
            context.json(jsonString);

        } catch (JacksonException jacksonException) {
            System.out.println(jacksonException.getMessage());
        }
    }

    private void getMessageById(Context context) {
        try {

            // get the id from the path
            int messageId = Integer.parseInt(context.pathParam("message_id"));

            // process
            Message returnedMessage = messageService.getMessageById(messageId);
            if (returnedMessage == null) {
                context.status(200); // per instructions
                return;
            }

            // Object > JSON > Response body
            String jsonString = objectMapper.writeValueAsString(returnedMessage);
            context.status(200);
            context.json(jsonString);

        } catch (JacksonException jacksonException) {
            System.out.println(jacksonException.getMessage());
        }
    }

    private void deleteMessageById(Context context) {
        try {

            // get the id from the path
            int messageId = Integer.parseInt(context.pathParam("message_id"));

            // for some reason the requirements call for a deleted object to be returned
            Message returnedMessage = messageService.getMessageById(messageId);

            if (returnedMessage == null) {
                context.status(200);
                return;
            }

            // process
            messageService.deleteMessageById(messageId);

            // Object > JSON > Response body
            String jsonString = objectMapper.writeValueAsString(returnedMessage);
            context.status(200);
            context.json(jsonString);

        } catch (JacksonException jacksonException) {
            System.out.println(jacksonException.getMessage());
        }
    }

    private void patchMessageById(Context context) {
        try {

            // get the id from the path
            int messageId = Integer.parseInt(context.pathParam("message_id"));

            // JSON > Object
            Message message = objectMapper.readValue(context.body(), Message.class);
            if (message == null) { // invalid body
                context.status(400);
                return;
            }

            // process
            Message returnedMessage = messageService.updateMessageText(messageId, message.getMessage_text());
            if (returnedMessage == null) {
                context.status(400); // per instructions
                return;
            }

            // Object > JSON > Response body
            String jsonString = objectMapper.writeValueAsString(returnedMessage);
            context.status(200);
            context.json(jsonString);

        } catch (JacksonException jacksonException) {
            System.out.println(jacksonException.getMessage());
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