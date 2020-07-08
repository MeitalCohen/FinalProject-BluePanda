import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu {

    public static void admin_menu(User user)
    {
        JFrame f=new JFrame("Admin Functions"); //Give dialog box name as admin functions
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //

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
                f.getContentPane().add(HomePage.home(user));
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
                f.getContentPane().add(ManageUsersPage.manageUsers(user));
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
                f.getContentPane().add(manageBooksPage.manageBooksPanel());
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
                f.getContentPane().add(manageBorrowsPage.manageBorrowsPanel());
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

/*
        AllBooksLendingsInformationRequest request = new AllBooksLendingsInformationRequest(user.getId());

        ServiceCommand sc = ServiceCommand.getInstance();
        AllBooksLendingsInformationResponse response = sc.execute(request);
        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {

            UserLendingsModel lendingsModel = new UserLendingsModel(response.getBorrowedBook());
            JTable lendingsTable = new JTable(lendingsModel);
            lendingsTable.setBounds(30, 15, 800, 300);
            lendingsTable.setModel(lendingsModel);
            f.add(lendingsTable);

        }*/

        f.setSize(1100,600);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);
        f.setJMenuBar(menuBar);
        f.getContentPane().add(HomePage.home(user));
        f.setTitle("Home");
        f.setVisible(true);

    }
}