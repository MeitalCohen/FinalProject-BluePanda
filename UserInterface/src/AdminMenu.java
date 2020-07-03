import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu {

    public static void admin_menu()
    {
        JFrame f=new JFrame("Admin Functions"); //Give dialog box name as admin functions
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //

        JMenuBar menuBar = new JMenuBar();

        JMenu menuMenu = new JMenu("Menu");
        JMenuItem menuItemHome = new JMenuItem("Home");
        JMenuItem menuItemMyBooks = new JMenuItem("My Books");
        JMenuItem menuItemLendBook = new JMenuItem("Lend Book");
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

        f.setTitle("Blue Panda");
        f.setSize(800,500);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);
        f.setJMenuBar(menuBar);
        f.setVisible(true);

    }
}