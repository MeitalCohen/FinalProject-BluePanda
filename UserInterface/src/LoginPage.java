import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.LoginRequest;
import services.responses.LoginResponse;
import services.responses.ResponseBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {

    public static void login() {

        int Y = 100;
        int dY = 35;

        int X = 360;

        JFrame f=new JFrame("Login");//creating instance of JFrame
        JLabel l1,l2;

        l1=new JLabel("Username");  //Create label Username
        l1.setBounds(X, Y+=dY, 100,30); //x axis, y axis, width, height

        l2=new JLabel("Password");  //Create label Password
        l2.setBounds(X,Y+=dY, 100,30);

        Y = 100;
        X += 100;

        JTextField F_user = new JTextField(); //Create text field for username
        F_user.setBounds(X, Y+=dY, 200, 30);

        JPasswordField F_pass=new JPasswordField(); //Create text field for password
        F_pass.setBounds(X, Y+=dY, 200, 30);

        X += 40;

        JButton login_but=new JButton("Login");//creating instance of JButton for Login Button
        login_but.setBounds(X,Y+=1.2*dY,80,25);//Dimensions for button

        X -= 80;

        JButton notRegister_but = new JButton("Don't you have an account? Register here!");
        notRegister_but.setBounds(X,Y+=1.2*dY,275,25);

        login_but.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e){

                String username = F_user.getText(); //Store username entered by the user in the variable "username"
                String password = F_pass.getText(); //Store password entered by the user in the variable "password"

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
                                UserMenu.user_menu(response.getUser());
                                break;
                            case 2:
                                UserMenu.user_menu(response.getUser());
                                break;
                            case 3:
                                AdminMenu.admin_menu(response.getUser());
                                break;
                            default:
                                break;
                        }
                        f.dispose();
                    }
                }
            }
        });

        notRegister_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                RegisterPage.Register();
                f.dispose();
            }
        });

        f.add(F_pass); //add password
        f.add(F_user);  //add user
        f.add(l1);  // add label1 i.e. for username
        f.add(l2); // add label2 i.e. for password

        f.add(login_but);//adding button in JFrame
        f.add(notRegister_but);

        f.setSize(1100,600);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);

    }

}
