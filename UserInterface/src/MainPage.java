import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame
{
    private JMenuBar menuBar;

    private JMenu menuMenu;
    private JMenu menuAccount;
    private JMenu menuManager;

    private JMenuItem menuItemHome;
    private JMenuItem menuItemMyBooks;
    private JMenuItem menuItemLendBook;
    private JMenuItem menuItemBookRecommendations;
    private JMenuItem menuItemEvents;
    private JMenuItem menuItemQuite;

    private JMenuItem menuItemLogin;
    private JMenuItem menuItemRegister;
    private JMenuItem menuItemLogout;

    private JMenuItem menuItemManageConfigurations;
    private JMenuItem menuItemManageUsers;
    private JMenuItem menuItemManageBooks;
    private JMenuItem menuItemManageEvents;

    public MainPage()
    {
        menuBar = new JMenuBar();
        menuMenu = new JMenu("Menu");
        menuItemHome = new JMenuItem("Home");
        menuItemMyBooks = new JMenuItem("My Books");
        menuItemLendBook = new JMenuItem("Lend Book");
        menuItemBookRecommendations = new JMenuItem("Book Recommendations");
        menuItemEvents = new JMenuItem("Events");
        menuItemQuite = new JMenuItem("Quite");
        menuMenu.add(menuItemHome);
        menuMenu.add(menuItemMyBooks);
        menuMenu.add(menuItemLendBook);
        menuMenu.add(menuItemBookRecommendations);
        menuMenu.add(menuItemEvents);
        menuMenu.add(menuItemQuite);

        menuAccount = new JMenu("Account");
        menuItemLogin = new JMenuItem("Login");
        menuItemLogout =  new JMenuItem("Logout");
        menuItemRegister = new JMenuItem("Register");
        menuAccount.add(menuItemLogin);
        menuAccount.add(menuItemLogout);
        menuAccount.add(menuItemRegister);

        menuItemManageUsers = new JMenu("Manager");
        menuItemManageConfigurations = new JMenuItem("Configurations");
        menuItemManageBooks = new JMenuItem("Manage Users");
        menuItemManageBooks = new JMenuItem("Manage Books");
        menuItemManageEvents = new JMenuItem("Manage Events");
        menuAccount.add(menuItemManageConfigurations);
        menuAccount.add(menuItemManageUsers);
        menuAccount.add(menuItemManageBooks);
        menuAccount.add(menuItemManageEvents);

        menuItemQuite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuBar.add(menuMenu);
        menuBar.add(menuAccount);
        //menuBar.add(menuManager);

        setTitle("This is my title");
        setSize(800,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setJMenuBar(menuBar);
    }
}