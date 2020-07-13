import entities.User;
import serviceHost.PopulateDAta;

import java.util.Date;

public class Main {
    public static void main(String [] args)
    {
        PopulateDAta data = new PopulateDAta();
        data.InitData();

        //LoginPage.login();
        User newUser = new User("316380013", "MeitalC", "Meital", "Cohen", "1234", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
        AdminMenu adminMenu = new AdminMenu(newUser);
        adminMenu.startAdminMenu();
    }
}