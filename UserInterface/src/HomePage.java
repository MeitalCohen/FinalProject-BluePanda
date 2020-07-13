import entities.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePage {

    public static JFrame home(int height, int width, User user)
    {
        JFrame homeFrame = new JFrame("Login");//creating instance of JFrame
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\BluePanda\\Images\\black-panda.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(300, 300,
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        JLabel LogoLabel = new JLabel(imageIcon);
        LogoLabel.setBounds(390, 0, 300, 300); //x axis, y axis, width, height

        String userWelcomeTitle = "<html><h1>Welcome, " + user.getUserName() + "</h1></html>";
        JLabel helloL = new JLabel(userWelcomeTitle);
        helloL.setHorizontalAlignment(SwingConstants.CENTER);

        String niceReadLTitle = "<html><br><h2>Have A Nice Read!</h2></html>";
        JLabel niceReadL = new JLabel(niceReadLTitle);
        niceReadL.setBounds(460, 200, 300, 200);

        homeFrame.add(LogoLabel);
        homeFrame.add(niceReadL);
        homeFrame.add(helloL);

        return homeFrame;
    }

}
