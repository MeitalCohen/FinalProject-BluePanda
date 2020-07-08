import entities.User;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.LoginRequest;
import services.requests.RegisterUserRequest;
import services.responses.LoginResponse;
import services.responses.RegisterUserResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Date;
import java.util.Map;

public class RegisterPage {

    public static void Register() {

        int Y = 50;
        int dY = 35;

        int X = 25;

        JFrame frame = new JFrame("Register");//creating instance of JFrame
        frame.getContentPane().setBackground(Color.white);

        JLabel IDL;
        JLabel userNameL;
        JLabel firstNameL;
        JLabel lastNameL;
        JLabel passwordL;
        JLabel genderL;
        JLabel addressL;
        JLabel emailL;
        JLabel phoneL;

        IDL = new JLabel("ID");  //Create label Username
        IDL.setBounds(X, Y+=dY, 100, 30); //x axis, y axis, width, height

        userNameL = new JLabel("Username");  //Create label Username
        userNameL.setBounds(X, Y+=dY, 100, 30); //x axis, y axis, width, height

        firstNameL = new JLabel("First name");  //Create label Password
        firstNameL.setBounds(X, Y+=dY, 100, 30);

        lastNameL = new JLabel("Last name");
        lastNameL.setBounds(X, Y+=dY, 100, 30); //x axis, y axis, width, height

        passwordL = new JLabel("Password");
        passwordL.setBounds(X, Y+=dY, 100, 30);

        genderL = new JLabel("Gender");
        genderL.setBounds(X, Y+=dY, 100, 30); //x axis, y axis, width, height

        addressL = new JLabel("Aaddress");
        addressL.setBounds(X, Y+=dY, 100, 30);

        emailL = new JLabel("Email");
        emailL.setBounds(X, Y+=dY, 100, 30); //x axis, y axis, width, height

        phoneL = new JLabel("Phone");
        phoneL.setBounds(X, Y+=dY, 100, 30);

        Y = 50;
        X += 100;
        // add input fields

        JTextField IDF = new JTextField(); //Create text field for username
        IDF.setBounds(X, Y+=dY, 200, 30);

        JTextField userNameF = new JTextField(); //Create text field for username
        userNameF.setBounds(X, Y+=dY, 200, 30);

        JTextField firstNameF = new JTextField();
        firstNameF.setBounds(X, Y+=dY, 200, 30);

        JTextField lastNameF = new JTextField();
        lastNameF.setBounds(X, Y+=dY, 200, 30); //x axis, y axis, width, height

        JPasswordField passwordF = new JPasswordField(); //Create text field for password
        passwordF.setBounds(X, Y+=dY, 200, 30);

        JTextField genderF = new JTextField();
        genderF.setBounds(X, Y+=dY, 200, 30); //x axis, y axis, width, height

        JTextField addressF = new JTextField();
        addressF.setBounds(X, Y+=dY, 200, 30);

        JTextField emailF = new JTextField();
        emailF.setBounds(X, Y+=dY, 200, 30); //x axis, y axis, width, height

        JTextField phoneF = new JTextField();
        phoneF.setBounds(X, Y+=dY, 200, 30);

        X += 30;

        JButton register_but = new JButton("Register");
        register_but.setBounds(X,Y+=1.2*dY,100,25);//Dimensions for button

        JLabel loginlbl  =new JLabel("Already have an account?!");  //Create label Password
        loginlbl.setBounds(X-= 80,Y+=dY,275,25);

        JButton loginBtn = new JButton("Click Here!");
        loginBtn.setBounds(X += 155,Y,100,25);
        loginBtn.setHorizontalAlignment(SwingConstants.LEFT);
        loginBtn.setBorderPainted(false);
        loginBtn.setOpaque(false);
        loginBtn.setBackground(Color.WHITE);
        loginBtn.setToolTipText("I Love BluePanda");
        loginBtn.getModel().addChangeListener(evt -> {
            ButtonModel model = (ButtonModel) evt.getSource();
            Font btnFont = loginBtn.getFont();
            Map attributes = btnFont.getAttributes();

            if (model.isRollover()) {
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            } else {
                attributes.put(TextAttribute.UNDERLINE, null);
            }
            btnFont = btnFont.deriveFont(attributes);
            loginBtn.setFont(btnFont);
        });

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                LoginPage.login();
            }
        });


        //register_but.setBounds(X, Y+=1.5*dY, 100, 25);
        register_but.addActionListener(new ActionListener() {  //Perform action

            public void actionPerformed(ActionEvent e) {

                String id = IDF.getText();
                String userName = userNameF.getText(); //Store username entered by the user in the variable "username"
                String firstName = firstNameF.getText();
                String lastName = lastNameF.getText();
                String password = passwordF.getText(); //Store password entered by the user in the variable "password"
                String gender =  genderF.getText();
                String address = addressF.getText();
                String email = emailF.getText();
                String phone = phoneF.getText();

                if (id.equals("")) //If username is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter id"); //Display dialog box with the message
                } else if (userName.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter userName"); //Display dialog box with the message
                } else if (password.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter password"); //Display dialog box with the message
                } else if (firstName.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter firstName"); //Display dialog box with the message
                } else if (lastName.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter lastName"); //Display dialog box with the message
                } else if (gender.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter gender"); //Display dialog box with the message
                } else if (address.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter address"); //Display dialog box with the message
                } else if (email.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter email"); //Display dialog box with the message
                } else if (phone.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter phone"); //Display dialog box with the message
                }
                else {
                    User user = new User(id, userName, firstName, lastName, password, 1, new Date(System.currentTimeMillis()), Integer.parseInt(genderF.getText()), address, email, phone);
                    RegisterUserRequest request = new RegisterUserRequest(user);
                    ServiceCommand sc = ServiceCommand.getInstance();
                    RegisterUserResponse response = sc.execute(request);
                    if (response.getStatus() != ResponseStatus.OK.errorCode()) {
                        JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
                    } else {
                        switch (response.getUser().getUserStatus()) {
                            case 1:
                                UserMenu um = new UserMenu();
                                um.user_menu(response.getUser());
                                break;
                            case 2:
                                LibrarianMenu librarianMenu = new LibrarianMenu(response.getUser());
                                librarianMenu.startLibrarianMenu();
                                break;
                            case 3:
                                //AdminMenu.admin_menu(response.getUser());
                                AdminMenu adminMenu = new AdminMenu(response.getUser());
                                adminMenu.startAdminMenu();
                                break;
                            default:
                                break;
                        }
                        frame.dispose();
                    }
                }
            }
        });

        frame.add(IDF);
        frame.add(firstNameF);
        frame.add(lastNameF);
        frame.add(userNameF);
        frame.add(passwordF);
        frame.add(genderF);
        frame.add(addressF);
        frame.add(emailF);
        frame.add(phoneF);

        frame.add(IDL);
        frame.add(firstNameL);
        frame.add(lastNameL);
        frame.add(userNameL);
        frame.add(passwordL);
        frame.add(genderL);
        frame.add(addressL);
        frame.add(emailL);
        frame.add(phoneL);

        frame.add(register_but);//adding button in JFrame
        frame.add(loginlbl);//adding button in JFrame
        frame.add(loginBtn);//adding button in JFrame

        frame.setSize(370,600);
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setLocationRelativeTo(null);

    }

}


