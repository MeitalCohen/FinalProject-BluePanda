import entities.User;
import serviceHost.PopulateDAta;

import java.util.Date;

public class Main {
    public static void main(String [] args)
    {
        PopulateDAta data = new PopulateDAta();
        data.InitData();

        LoginPage.login();
    }
}