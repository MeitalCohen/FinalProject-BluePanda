import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;
import entities.Event;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.GetEventsRequest;
import services.responses.GetEventsResponse;


public class ManageEventsPage extends JFrame
{
    private ServiceCommand sc;
    private String _dataFile;
    private Calendar calendar;
    private static final long serialVersionUID = 1L;

    public JFrame manageEventsFrame()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ManageEventsPage window = null;
                try {
                    window = new ManageEventsPage();
                    //window.setVisible(true);
                }
                catch (Exception exp) {
                }
            }
        });
        return this;
    }

    public void initPage()
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
                //onWindowOpened();
            }
        });

        // Initialize the date file
        //_dataFile = new java.io.File("Schedule.dat").getAbsolutePath();
        onWindowOpened();
    }

    public ManageEventsPage()
    {
        sc = ServiceCommand.getInstance();

        initPage();
    }

    private void onWindowOpened()
    {
        /*if (new java.io.File(_dataFile).exists())
        calendar.getSchedule().loadFrom(_dataFile, ContentType.Xml);*/
        GetEventsRequest request = new GetEventsRequest();
        GetEventsResponse response = sc.execute(request);
        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            Vector<Event> events = response.getEvents();
            Appointment item;
            DateTime dt;
            for (Event e : events)
            {
                item = new Appointment();
                dt = new DateTime(e.getScheduled());
                item.setStartTime(dt);
                item.setEndTime(dt);
                item.setHeaderText(e.getEventTitle());
                //item.setTag();
                item.setDescriptionText("Disquisition with " + e.getAuthorName());
                calendar.getSchedule().getItems().add(item);
            }
        }
    }

    private void exit() {
        calendar.getSchedule().saveTo(_dataFile, ContentType.Xml);
    }

}
