import serviceHost.PopulateDAta;
import serviceHost.ServiceCommand;

public class Main {
    public static void main(String [] args)
    {
        //init files >> RUN ONCE ONLY!
        //in order prevent duplicate data
        PopulateDAta data = new PopulateDAta();
        data.CreateUser();

        ServiceCommand service = ServiceCommand.getInstance();
        //LoginPage.login();
        //LibraryBooksPage.libraryBooks();
        //MyBooksPage.myBooks();
        UserMenu.user_menu("");
    }
}