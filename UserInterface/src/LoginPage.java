import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.LoginRequest;
import services.responses.LoginResponse;
import services.responses.ResponseBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

public class LoginPage {

    public static void login() {

        int Y = 200;
        int dY = 35;

        int X = 15;

        JFrame loginFrame=new JFrame("Login");//creating instance of JFrame
        loginFrame.getContentPane().setBackground(Color.white);

        JLabel usernameLbl=new JLabel("Username");  //Create label Username
        usernameLbl.setBounds(X, Y+=dY, 100,30); //x axis, y axis, width, height

        JLabel passwordLbl =new JLabel("Password");  //Create label Password
        passwordLbl.setBounds(X,Y+=dY, 100,30);

        Y = 200;
        X += 90;

        JTextField usernameField = new JTextField(); //Create text field for username
        usernameField.setBounds(X, Y+=dY, 200, 30);

        JPasswordField passwordField =new JPasswordField(); //Create text field for password
        passwordField.setBounds(X, Y+=dY, 200, 30);

        X += 40;

        JButton loginBtn =new JButton("Login");//creating instance of JButton for Login Button
        loginBtn.setBounds(X,Y+=1.2*dY,80,25);//Dimensions for button

        X -= 80;


        JLabel registrationLbl  =new JLabel("Don't have an account yet?");  //Create label Password
        registrationLbl.setBounds(X,Y+=1.2*dY,275,25);

        JButton registrationBtn = new JButton("Click Here!");
        registrationBtn.setBounds(X += 155,Y,100,25);
        registrationBtn.setHorizontalAlignment(SwingConstants.LEFT);
        registrationBtn.setBorderPainted(false);
        registrationBtn.setOpaque(false);
        registrationBtn.setBackground(Color.WHITE);
        registrationBtn.setToolTipText("I Love BluePanda");
        registrationBtn.getModel().addChangeListener(evt -> {
            ButtonModel model = (ButtonModel) evt.getSource();
            Font btnFont = registrationBtn.getFont();
            Map attributes = btnFont.getAttributes();

            if (model.isRollover()) {
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            } else {
                attributes.put(TextAttribute.UNDERLINE, null);
            }
            btnFont = btnFont.deriveFont(attributes);
            registrationBtn.setFont(btnFont);
        });



        loginBtn.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e){

                String username = usernameField.getText(); //Store username entered by the user in the variable "username"
                String password = passwordField.getText(); //Store password entered by the user in the variable "password"

                if(username.equals("")) //If username is null
                {
                    JOptionPane.showMessageDialog(null,"Please enter username"); //Display dialog box with the message
                }
                else if(password.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null,"Please enter password"); //Display dialog box with the message
                }
                else {
                    LoginRequest request = new LoginRequest(username,password);
                    ServiceCommand sc = ServiceCommand.getInstance();
                    LoginResponse response = sc.execute(request);
                    if(response.getStatus() != ResponseStatus.OK.errorCode())
                    {
                        JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                    }
                    else
                    {
                        switch (response.getUser().getUserStatus())
                        {
                            case 1:
                                UserMenu um = new UserMenu();
                                um.user_menu(response.getUser());
                                break;
                            case 2:
                                UserMenu um2 = new UserMenu();
                                um2.user_menu(response.getUser());
                                break;
                            case 3:
                                AdminMenu.admin_menu(response.getUser());
                                break;
                            default:
                                break;
                        }
                        loginFrame.dispose();
                    }
                }
            }
        });

        registrationBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                RegisterPage.Register();
                loginFrame.dispose();
            }
        });

        loginFrame.add(passwordField); //add password
        loginFrame.add(usernameField);  //add user
        loginFrame.add(usernameLbl);  // add label1 i.e. for username
        loginFrame.add(passwordLbl); // add label2 i.e. for password

        loginFrame.add(loginBtn);//adding button in JFrame

        loginFrame.add(registrationLbl);
        loginFrame.add(registrationBtn);

        loginFrame.setSize(360,500);
        loginFrame.setLayout(null);//using no layout managers
        loginFrame.setVisible(true);//making the frame visible
        loginFrame.setLocationRelativeTo(null);

    }

}
