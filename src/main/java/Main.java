import Controller.SocialMediaController;
import DAO.AccountDao;
import DAO.AccountDaoImpl;
import Model.Account;
import Util.ConnectionUtil;
import io.javalin.Javalin;

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

    }
}
