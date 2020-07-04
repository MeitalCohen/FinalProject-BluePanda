import javax.swing.*;
import java.awt.*;

public class HomePage {

    public static JPanel home(String userId)
    {
        JPanel p = new JPanel();

        JLabel helloL = new JLabel("Welcome!");
        helloL.setFont(new Font("Monospace", Font.PLAIN, 20));

        p.add(UserMainPage.userMain(userId));

        p.add(helloL);

        return p;
    }

}
