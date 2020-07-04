import entities.BooksInOrders;
import entities.Order;
import enums.ResponseStatus;
import jtableModel.UserLendingsModel;
import serviceHost.ServiceCommand;
import services.requests.AllBooksLendingsInformationRequest;
import services.requests.CreateOrderRequest;
import services.responses.AllBooksLendingsInformationResponse;
import services.responses.CreateOrderResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AdminMenu {

    public static void admin_menu(String userId)
    {
        JFrame f=new JFrame("Admin Functions"); //Give dialog box name as admin functions
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //

        JMenuBar menuBar = new JMenuBar();

        JMenu menuMenu = new JMenu("Menu");
        JMenuItem menuItemHome = new JMenuItem("Home");
        JMenuItem menuItemMyBooks = new JMenuItem("My Books");
        JMenuItem menuItemLibraryBooks = new JMenuItem("Library Book");
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
                System.exit(0);
            }
        });

        menuItemMyBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemLibraryBooks.addActionListener(new ActionListener() {
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
        JMenuItem menuItemLogout =  new JMenuItem("Logout");
        menuAccount.add(menuItemLogout);

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
        JMenuItem menuItemManageEvents = new JMenuItem("Manage Events");
        menuManage.add(menuItemManageConfigurations);
        menuManage.add(menuItemManageUsers);
        menuManage.add(menuItemManageBooks);
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
                System.exit(0);
            }
        });

        menuItemManageBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuItemManageEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        menuBar.add(menuMenu);
        menuBar.add(menuAccount);
        menuBar.add(menuManage);


        AllBooksLendingsInformationRequest request = new AllBooksLendingsInformationRequest(userId);

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

        }


        f.setTitle("Blue Panda");
        f.setSize(1100,600);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);
        f.setJMenuBar(menuBar);
        f.setVisible(true);

    }
}