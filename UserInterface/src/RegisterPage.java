import entities.User;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.LoginRequest;
import services.requests.RegisterUserRequest;
import services.responses.LoginResponse;
import services.responses.RegisterUserResponse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RegisterPage {

    public static void Register() {

        int Y = -15;
        int dY = 35;

        JFrame f = new JFrame("Register");//creating instance of JFrame

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
        IDL.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        userNameL = new JLabel("Username");  //Create label Username
        userNameL.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        firstNameL = new JLabel("First name");  //Create label Password
        firstNameL.setBounds(30, Y+=dY, 100, 30);

        lastNameL = new JLabel("Last name");
        lastNameL.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        passwordL = new JLabel("Password");
        passwordL.setBounds(30, Y+=dY, 100, 30);

        genderL = new JLabel("Gender");
        genderL.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        addressL = new JLabel("Aaddress");
        addressL.setBounds(30, Y+=dY, 100, 30);

        emailL = new JLabel("Email");
        emailL.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        phoneL = new JLabel("Phone");
        phoneL.setBounds(30, Y+=dY, 100, 30);

        Y = -15;
        // add input fields

        JTextField IDF = new JTextField(); //Create text field for username
        IDF.setBounds(110, Y+=dY, 200, 30);

        JTextField userNameF = new JTextField(); //Create text field for username
        userNameF.setBounds(110, Y+=dY, 200, 30);

        JTextField firstNameF = new JTextField();
        firstNameF.setBounds(110, Y+=dY, 200, 30);

        JTextField lastNameF = new JTextField();
        lastNameF.setBounds(110, Y+=dY, 200, 30); //x axis, y axis, width, height

        JPasswordField passwordF = new JPasswordField(); //Create text field for password
        passwordF.setBounds(110, Y+=dY, 200, 30);

        JTextField genderF = new JTextField();
        genderF.setBounds(110, Y+=dY, 200, 30); //x axis, y axis, width, height

        JTextField addressF = new JTextField();
        addressF.setBounds(110, Y+=dY, 200, 30);

        JTextField emailF = new JTextField();
        emailF.setBounds(110, Y+=dY, 200, 30); //x axis, y axis, width, height

        JTextField phoneF = new JTextField();
        phoneF.setBounds(110, Y+=dY, 200, 30);

        JButton register_but = new JButton("Register");
        register_but.setBounds(142, Y+=1.5*dY, 100, 25);
        register_but.addActionListener(new ActionListener() {  //Perform action

            public void actionPerformed(ActionEvent e) {

                String id = userNameF.getText();
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
                        JOptionPane.showMessageDialog(null, "Some details are wrong, please check"); //Display Message
                    } else {
                        switch (response.getUser().getUserStatus()) {
                            case 1:
                                UserMenu.user_menu("");
                                break;
                            case 2:
                                UserMenu.user_menu("");
                                break;
                            case 3:
                                AdminMenu.admin_menu();
                                break;
                            default:
                                break;
                        }
                        f.dispose();
                    }
                }
            }
        });

        f.add(IDF);
        f.add(firstNameF);
        f.add(lastNameF);
        f.add(userNameF);
        f.add(passwordF);
        f.add(genderF);
        f.add(addressF);
        f.add(emailF);
        f.add(phoneF);

        f.add(IDL);
        f.add(firstNameL);
        f.add(lastNameL);
        f.add(userNameL);
        f.add(passwordL);
        f.add(genderL);
        f.add(addressL);
        f.add(emailL);
        f.add(phoneL);

        f.add(register_but);//adding button in JFrame

        f.setSize(400, 430);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);

    }

}


