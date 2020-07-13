import entities.Event;
import entities.User;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.ScheduleEventRequest;
import services.responses.ScheduleEventResponse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEvent
{
    private User user;
    private Date date;
    private IFinishedCommand finishedCommand;

    public AddEvent(IFinishedCommand finishedCommand, User user, Date date)
    {
        this.date = date;
        this.user = user;
        this.finishedCommand = finishedCommand;
    }

    public JFrame addEvent()
    {
        int Y = 10;
        int dY = 45;

        JFrame frame = new JFrame("Add Event");//creating instance of JFrame

        JLabel eventTitleLabel;
        JLabel scheduledLabel;
        JLabel authorNameLabel;

        eventTitleLabel = new JLabel("Event Title");  //Create label Username
        eventTitleLabel.setBounds(35, Y+=dY, 100, 30); //x axis, y axis, width, height

        authorNameLabel = new JLabel("Author Name");  //Create label Username
        authorNameLabel.setBounds(35, Y+=dY, 100, 30); //x axis, y axis, width, height

        scheduledLabel = new JLabel("Date");  //Create label Username
        scheduledLabel.setBounds(35, Y+=dY, 100, 30); //x axis, y axis, width, height

        Y = 10;
        // add input fields

        JTextField eventTitleField = new JTextField(); //Create text field for username
        eventTitleField.setBounds(115, Y+=dY, 200, 30);

        JTextField authorNameField = new JTextField(); //Create text field for username
        authorNameField.setBounds(115, Y+=dY, 200, 30);

        // date
        JTextField dateField = new JTextField(); //Create text field for username
        dateField.setBounds(115, Y+=dY, 200, 30);
        dateField.setText(date.toString());

        JButton add_but = new JButton("Add");
        add_but.setBounds(115, Y+= 2*dY, 100, 25);
        add_but.addActionListener(new ActionListener() {  //Perform action

            public void actionPerformed(ActionEvent e) {

                String title = eventTitleField.getText();
                String author = authorNameField.getText();

                if (title.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter title"); //Display dialog box with the message
                } else if (author.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter author name"); //Display dialog box with the message
                } else {

                    Event newEvent = new Event();

                    newEvent.setEventTitle(title);
                    newEvent.setAuthorName(author);

                    String id = user.getId();
                    newEvent.setLibrarianID(id);

                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                        newEvent.setScheduled(formatter.parse(dateField.getText()));

                        ScheduleEventRequest request = new ScheduleEventRequest(newEvent);
                        ScheduleEventResponse response = ServiceCommand.getInstance().execute(request);

                        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
                            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
                        } else {
                            JOptionPane.showMessageDialog(null, "Added Event Successfully");
                            //refresh();
                            finishedCommand.finishedCommand();
                            frame.dispose();
                        }
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Date");
                        //ex.printStackTrace();
                    }
                }
            }
        });

        frame.add(eventTitleLabel);
        frame.add(scheduledLabel);
        frame.add(authorNameLabel);

        frame.add(eventTitleField);
        frame.add(authorNameField);
        frame.add(dateField);

        frame.add(add_but);//adding button in JFrame

        frame.setSize(360,350);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setLocationRelativeTo(null);

        return frame;
    }
}
