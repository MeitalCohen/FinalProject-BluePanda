import entities.BookStock;
import entities.User;
import enums.BooksFilter;
import enums.ResponseStatus;
import jtableModel.ManageBooksModel;
import serviceHost.ServiceCommand;
import services.requests.GetBooksRequest;
import services.responses.GetBooksResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class UserMenu {

    public void user_menu(User user)
    {
        int width = 1100;
        int height = 600;

        JFrame f = new JFrame("User Functions"); //Give dialog box name as User functions

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
                f.getContentPane().add(HomePage.home(height, width, user));
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
                MyBooksPage myBooksPage = new MyBooksPage(user);
                Component [] cmps =  myBooksPage.myBooksPanel().getComponents();
                for (Component cmp: cmps) {
                    f.getContentPane().add(cmp);
                }
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
                LibraryBooksPage libraryBooksPage = new LibraryBooksPage(user);
                Component [] cmps =  libraryBooksPage.libraryBooksPanel().getComponents();
                for (Component cmp: cmps) {
                    f.getContentPane().add(cmp);
                }
                f.setTitle("Library Book Stock");
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
        JMenuItem menuItemEdit =  new JMenuItem("Edit");
        JMenuItem menuItemLogout =  new JMenuItem("Logout");
        menuAccount.add(menuItemEdit);
        menuAccount.add(menuItemLogout);

        menuItemEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UpdateUserInfo.UpdateUserInfoPage(user);
                f.dispose();
            }
        });

        menuItemLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginPage.login();
                f.dispose();
            }
        });

        menuBar.add(menuMenu);
        menuBar.add(menuAccount);

        f.setSize(width,height);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        //f.setLayout(new GridLayout(2,2));
        f.setLocationRelativeTo(null);
        f.setJMenuBar(menuBar);
        f.getContentPane().add(HomePage.home(height, width, user));
        f.setTitle("Home");
        f.setResizable(false);
        //f.pack();
        f.setVisible(true);
    }
}