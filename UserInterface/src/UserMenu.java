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
        JMenuItem menuItemLibraryBooks = new JMenuItem("Library Books");
        JMenuItem menuItemEvents = new JMenuItem("Events");
        JMenuItem menuItemQuite = new JMenuItem("Quite");
        menuMenu.add(menuItemHome);
        menuMenu.add(menuItemMyBooks);
        menuMenu.add(menuItemLibraryBooks);
        menuMenu.add(menuItemEvents);
        menuMenu.add(menuItemQuite);

        menuItemHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // clear the current screen
                f.getContentPane().removeAll();
                // get myBooks screen
                f.getContentPane().add(HomePage.home());
                f.setTitle("Home");
                f.revalidate();
            }
        });

        menuItemMyBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // clear the current screen
                f.getContentPane().removeAll();
                // get myBooks screen
                f.getContentPane().add(MyBooksPage.myBooks());
                f.setTitle("My Books");
                f.revalidate();
            }
        });

        menuItemLibraryBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // clear the current screen
                f.getContentPane().removeAll();
                // get libraryBooks screen
                f.getContentPane().add(LibraryBooksPage.libraryBooks());
                f.setTitle("Library");
                f.revalidate();
            }
        });

        menuItemEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // clear the current screen
                f.getContentPane().removeAll();
                // get events screen
                f.getContentPane().add(EventsPage.events());
                f.setTitle("Events");
                f.revalidate();
            }
        });

        menuItemQuite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        JMenu menuAccount = new JMenu("Account");
        JMenuItem menuItemLogout =  new JMenuItem("Logout");
        menuAccount.add(menuItemLogout);

        menuItemLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginPage.login();
                f.dispose();
            }
        });

        menuBar.add(menuMenu);
        menuBar.add(menuAccount);

        f.getContentPane().add(HomePage.home());
        f.setTitle("Home");
        f.setSize(1100,600);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);
        f.setJMenuBar(menuBar);
        f.setVisible(true);
    }

}
