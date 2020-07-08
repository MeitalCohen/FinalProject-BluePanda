import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu {

    public static void admin_menu(User user)
    {
        int width = 1100;
        int height = 600;

        JFrame f=new JFrame("Admin Functions");

        JMenuBar menuBar = new JMenuBar();

        JMenu menuMenu = new JMenu("Menu");
        JMenuItem menuItemHome = new JMenuItem("Home");
        JMenuItem menuItemBorrowedBooks = new JMenuItem("Borrowed Books");
        JMenuItem menuItemLibraryBooks = new JMenuItem("Library Books");
        JMenuItem menuItemEvents = new JMenuItem("Events");
        JMenuItem menuItemQuite = new JMenuItem("Quite");
        menuMenu.add(menuItemHome);
        menuMenu.add(menuItemBorrowedBooks);
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

        menuItemBorrowedBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // clear the current screen
                f.getContentPane().removeAll();
                // get myBooks screen
                f.getContentPane().add(BorrowedBooksPage.borrowedBooks(user));
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
                //f.getContentPane().add(LibraryBooksPage.libraryBooksTable(user));
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

        JMenu menuManage = new JMenu("Manage");
        JMenuItem menuItemManageConfigurations = new JMenuItem("Configurations");
        JMenuItem menuItemManageUsers = new JMenuItem("Manage Users");
        JMenuItem menuItemManageBooks = new JMenuItem("Manage Books");
        JMenuItem menuItemManageBorrows = new JMenuItem("Manage Borrows");
        JMenuItem menuItemManageEvents = new JMenuItem("Manage Events");
        menuManage.add(menuItemManageConfigurations);
        menuManage.add(menuItemManageUsers);
        menuManage.add(menuItemManageBooks);
        menuManage.add(menuItemManageBorrows);
        menuManage.add(menuItemManageEvents);

        menuItemManageConfigurations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemManageUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // clear the current screen
                f.getContentPane().removeAll();
                // get events screen
                ManageUsersPage manageUsersPage = new ManageUsersPage(user);
                f.getContentPane().add(manageUsersPage.manageUsersPanel());
                f.setTitle("Manage Users");
                f.revalidate();
            }
        });

        menuItemManageBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // clear the current screen
                f.getContentPane().removeAll();
                // get events screen
                ManageBooksPage manageBooksPage = new ManageBooksPage(user);
                Component [] cmps =  manageBooksPage.manageBooksPanel().getComponents();
                for (Component cmp: cmps) {
                    f.getContentPane().add(cmp);
                }
                f.setTitle("Manage Books");
                f.revalidate();
            }
        });

        menuItemManageBorrows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // clear the current screen
                f.getContentPane().removeAll();
                // get events screen
                ManageBorrowsPage manageBorrowsPage = new ManageBorrowsPage(user);
                Component [] cmps =  manageBorrowsPage.manageBorrowsPanel().getComponents();
                for (Component cmp: cmps) {
                    f.getContentPane().add(cmp);
                }
                f.setTitle("Manage Borrows");
                f.revalidate();
            }
        });

        menuItemManageEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // clear the current screen
                f.getContentPane().removeAll();
                // get events screen
                f.getContentPane().add(EventsPage.events());
                f.setTitle("Manage Events");
                f.revalidate();
            }
        });

        menuBar.add(menuMenu);
        menuBar.add(menuAccount);
        menuBar.add(menuManage);

        f.setSize(width,height);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);
        f.setJMenuBar(menuBar);
        f.getContentPane().add(HomePage.home(height, width, user));
        f.setTitle("Home");
        f.setVisible(true);

    }
}