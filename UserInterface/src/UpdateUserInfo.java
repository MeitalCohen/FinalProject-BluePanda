import entities.BooksInOrders;
import entities.Order;
import entities.User;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.CreateOrderRequest;
import services.requests.UpdateUserInfoRequest;
import services.responses.CreateOrderResponse;
import services.responses.UpdateUserInfoResponse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class UpdateUserInfo {

    public static void UpdateUserInfoPage(User user) {

        int Y = -15;
        int dY = 35;

        JFrame f = new JFrame("Update User Details");//creating instance of JFrame

        JLabel userIdLable;
        JLabel userNameLabel;
        JLabel firstNamelbl;
        JLabel lastNamelbl;
        JLabel passwordl;
        JLabel addresslbl;
        JLabel emaillbl;
        JLabel phonelbl;


        userIdLable = new JLabel("User Id");  //Create label Username
        userIdLable.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        userNameLabel = new JLabel("UserName");  //Create label Username
        userNameLabel.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        firstNamelbl = new JLabel("First Name");  //Create label Password
        firstNamelbl.setBounds(30, Y+=dY, 100, 30);

        lastNamelbl = new JLabel("LastName");
        lastNamelbl.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        passwordl = new JLabel("Password");
        passwordl.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        addresslbl = new JLabel("Address");
        addresslbl.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        emaillbl = new JLabel("Email");
        emaillbl.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        phonelbl = new JLabel("Phone");
        phonelbl.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height


        Y = -15;
        // add input fields

        JTextField userIdFeild = new JTextField(); //Create text field for username
        userIdFeild.setBounds(110, Y+=dY, 200, 30);
        userIdFeild.setText(user.getId());
        userIdFeild.setEnabled(false);

        JTextField usernameField = new JTextField(); //Create text field for username
        usernameField.setText(user.getUserName());
        usernameField.setBounds(110, Y+=dY, 200, 30);

        JTextField firstNameField = new JTextField();
        firstNameField.setText(user.getFirstName());
        firstNameField.setBounds(110, Y+=dY, 200, 30);

        JTextField lastNameField = new JTextField();
        lastNameField.setText(user.getLastName());
        lastNameField.setBounds(110, Y+=dY, 200, 30); //x axis, y axis, width, height

        JPasswordField passwordF = new JPasswordField(); //Create text field for password
        passwordF.setBounds(110, Y+=dY, 200, 30);

        JTextField addressField = new JTextField(); //Create text field for password
        addressField.setText(user.getAddress());
        addressField.setBounds(110, Y+=dY, 200, 30);

        JTextField emailField = new JTextField(); //Create text field for password
        emailField.setText(user.getPhone());
        emailField.setBounds(110, Y+=dY, 200, 30);

        JTextField phoneField = new JTextField(); //Create text field for password
        phoneField.setText(user.getPhone());
        phoneField.setBounds(110, Y+=dY, 200, 30);



        JButton updateInfoBtn = new JButton("Update Me!");
        updateInfoBtn.setBounds(142, Y+=1.5*dY, 100, 25);
        updateInfoBtn.addActionListener(new ActionListener() {  //Perform action

            public void actionPerformed(ActionEvent e) {

                if (usernameField.equals("")) //If username is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter valid userName"); //Display dialog box with the message
                } else if (firstNameField.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter first name"); //Display dialog box with the message
                } else if (lastNameField.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Invalid last name"); //Display dialog box with the message
                } else if (passwordF.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Invalid password"); //Display dialog box with the message
                }
                else {

                    user.setAddress(addressField.getText());
                    user.setUserName(usernameField.getText());
                    user.setEmail(emailField.getText());
                    user.setPhone(phoneField.getText());
                    user.setFirstName(firstNameField.getText());
                    user.setLastName(lastNameField.getText());
                    String password = passwordF.getText();
                    user.setPassword(password);

                    UpdateUserInfoRequest request = new UpdateUserInfoRequest(user);

                    ServiceCommand sc = ServiceCommand.getInstance();
                    UpdateUserInfoResponse response = sc.execute(request);
                    if (response.getStatus() != ResponseStatus.OK.errorCode()) {
                        JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
                    } else {
                        JOptionPane.showMessageDialog(null, "Congrats! You just got Updated!"); //Display Message

                        f.dispose();
                    }
                }
            }
        });

        f.add(userIdLable);
        f.add(firstNamelbl);
        f.add(lastNamelbl);
        f.add(userNameLabel);
        f.add(phonelbl);
        f.add(passwordl);
        f.add(emaillbl);
        f.add(addresslbl);


        f.add(userIdFeild);
        f.add(lastNameField);
        f.add(firstNameField);
        f.add(usernameField);
        f.add(passwordF);
        f.add(addressField);
        f.add(emailField);
        f.add(phoneField);


        f.add(updateInfoBtn);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);


    }

}
