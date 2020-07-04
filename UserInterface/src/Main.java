import entities.User;
import serviceHost.PopulateDAta;
import serviceHost.ServiceCommand;

import java.util.Date;

public class Main {
    public static void main(String [] args)
    {
        //init files >> RUN ONCE ONLY!
        //in order prevent duplicate data
        PopulateDAta data = new PopulateDAta();
        data.InitData();


        ServiceCommand service = ServiceCommand.getInstance();
        //AddOrder.AddOrder();

        //UpdateUserInfo.UpdateUserInfoPage(newUser3);

        LoginPage.login();


        //LibraryBooksPage.libraryBooks();
        //MyBooksPage.myBooks();
        //UserMenu.user_menu("");
    }
}