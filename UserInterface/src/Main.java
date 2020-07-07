import entities.User;
import serviceHost.PopulateDAta;
import serviceHost.ServiceCommand;

import javax.swing.*;
import java.util.Date;

public class Main {
    public static void main(String [] args)
    {
        //init files >> RUN ONCE ONLY!
        //in order prevent duplicate data

        //AddRecommendationPage.AddRecommendation("1","book", "author", "author2");
        //PopulateDAta data = new PopulateDAta();
        //data.InitData();


        //ServiceCommand service = ServiceCommand.getInstance();
        //AddOrder.AddOrder();

        //UpdateUserInfo.UpdateUserInfoPage(newUser3);

        LoginPage.login();


        //LibraryBooksPage.libraryBooks();
        //MyBooksPage.myBooks("55");
        //UserMenu.user_menu("");
    }
}