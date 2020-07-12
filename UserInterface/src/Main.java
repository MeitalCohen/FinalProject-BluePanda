import entities.User;
import serviceHost.PopulateDAta;

import java.util.Date;

public class Main {
    public static void main(String [] args)
    {
        PopulateDAta data = new PopulateDAta();
        data.InitData();

        //LoginPage.login();
        AdminMenu adminMenu = new AdminMenu(new User("316380013", "MeitalC", "Meital", "Cohen", "1234", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true));
        adminMenu.startAdminMenu();
    }
}