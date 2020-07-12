import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class ManageEvents extends JFrame
{
    private String _dataFile;
    private Calendar calendar;
    private static final long serialVersionUID = 1L;

    public JFrame manageEventsFrame()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ManageEvents window = null;
                try {
                    window = new ManageEvents();
                    //window.setVisible(true);
                }
                catch (Exception exp) {
                }
            }
        });
        return this;
    }

    public ManageEvents()
    {
        //setSize(368, 362);

        calendar = new Calendar();
        calendar.setTheme(ThemeType.Light);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(toolBar, BorderLayout.PAGE_START);
        cp.add(calendar, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                exit();
            }
            public void windowOpened(WindowEvent e){
                onWindowOpened();
            }
        });

        // Initialize the date file
        _dataFile = new java.io.File("Schedule.dat").getAbsolutePath();
    }

    private void onWindowOpened() {
        if (new java.io.File(_dataFile).exists())
            calendar.getSchedule().loadFrom(_dataFile, ContentType.Xml);
    }

    private void exit() {
        calendar.getSchedule().saveTo(_dataFile, ContentType.Xml);
    }

}
