import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenu {

    public static void user_menu(String UID)
    {
        JFrame f = new JFrame("User Functions"); //Give dialog box name as User functions
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit user menu on closing the dialog box

        JMenuBar menuBar = new JMenuBar();

        JMenu menuMenu = new JMenu("Menu");
        JMenuItem menuItemHome = new JMenuItem("Home");
        JMenuItem menuItemMyBooks = new JMenuItem("My Books");
        JMenuItem menuItemLendBook = new JMenuItem("Lend Books");
        JMenuItem menuItemBookRecommendations = new JMenuItem("Book Recommendations");
        JMenuItem menuItemEvents = new JMenuItem("Events");
        JMenuItem menuItemQuite = new JMenuItem("Quite");
        menuMenu.add(menuItemHome);
        menuMenu.add(menuItemMyBooks);
        menuMenu.add(menuItemLendBook);
        menuMenu.add(menuItemBookRecommendations);
        menuMenu.add(menuItemEvents);
        menuMenu.add(menuItemQuite);

        menuItemHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemMyBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemLendBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemBookRecommendations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemQuite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        JMenu menuAccount = new JMenu("Account");
        JMenuItem menuItemLogin = new JMenuItem("Login");
        // JMenuItem menuItemLogout =  new JMenuItem("Logout");
        JMenuItem menuItemRegister = new JMenuItem("Register");
        menuAccount.add(menuItemLogin);
        // menuAccount.add(menuItemLogout);
        menuAccount.add(menuItemRegister);

        menuItemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuBar.add(menuMenu);
        menuBar.add(menuAccount);

        f.setTitle("Blue Panda");
        f.setSize(800,500);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);
        f.setJMenuBar(menuBar);
        f.setVisible(true);
    }

}
