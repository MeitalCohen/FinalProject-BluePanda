import entities.User;

import javax.swing.*;
import java.awt.*;

public class HomePage {

    public static JPanel home(User user)
    {
        JPanel p = new JPanel();

        JLabel helloL = new JLabel("Welcome!");
        helloL.setFont(new Font("Monospace", Font.PLAIN, 20));

        p.add(UserMainPage.userMain(user));

        p.add(helloL);

        return p;
    }

}
