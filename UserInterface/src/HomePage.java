import entities.User;

import javax.swing.*;
import java.awt.*;

public class HomePage {

    public static JPanel home(int height, int width, User user)
    {
        JPanel p = new JPanel();

        String userWelcomeTitle = "Welcome " + user.getUserName() + "!";

        JLabel helloL = new JLabel(userWelcomeTitle);
        helloL.setFont(new Font("Monospace", Font.PLAIN, 20));


        JPanel titlePanel = new JPanel();
        titlePanel.add(helloL);
        Dimension minimumSize = new Dimension(width, 120);
        titlePanel.setMinimumSize(minimumSize);

        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(1,1,width, height);
        Dimension contentMinimumSize = new Dimension(width, height - 120);
        contentPanel.setMinimumSize(contentMinimumSize);

        JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, titlePanel, contentPanel);

        splitPane.setDividerLocation(150);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        p.add(splitPane);

        return p;
    }

}
