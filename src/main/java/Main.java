import Controller.SocialMediaController;
import DAO.AccountDao;
import DAO.AccountDaoImpl;
import DAO.MessageDao;
import DAO.MessageDoaImpl;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.AccountServiceImpl;
import Service.MessageService;
import Service.MessageServiceImpl;
import Util.ConnectionUtil;
import io.javalin.Javalin;

import java.time.Instant;
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
    }
}
