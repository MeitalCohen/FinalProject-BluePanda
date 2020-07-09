import entities.User;
import enums.ResponseStatus;
import jtableModel.ManageUsersModel;
import serviceHost.ServiceCommand;
import services.requests.GetUsersRequest;
import services.requests.UpdateUsersRequest;
import services.responses.GetUsersResponse;
import services.responses.UpdateUsersResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

public class ManageUsersPage implements IFinishedCommand{
    private User user;
    private ServiceCommand sc;
    private Vector<User> usersToUpdate;
    private IUpdateFrameCommand menuCommand;
    private JFrame frame;

    public ManageUsersPage(IUpdateFrameCommand command, User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
        usersToUpdate = new Vector<>();
        this.menuCommand = command;
    }

    private JTable manageUsersTable() {

        GetUsersRequest request = new GetUsersRequest(user.getId());
        GetUsersResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageUsersModel manageUsersModel = new ManageUsersModel(response.getUsers());
            JTable usersTable = new JTable(convert(manageUsersModel.getUsers()), manageUsersModel.getColumns().toArray()) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    if( col == 0 || col == 5 )
                        return false;
                    return true;
                }
            };

            //usersTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
           /* usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = usersTable.getSelectedRow();
                    //int col = usersTable.getSelectedColumn();
                    User newUser = new User();
                    newUser.setId((String) usersTable.getModel().getValueAt(row, 0));
                    newUser.setUserName((String) usersTable.getModel().getValueAt(row, 1));
                    newUser.setFirstName((String) usersTable.getModel().getValueAt(row, 2));
                    newUser.setLastName((String) usersTable.getModel().getValueAt(row, 3));
                    newUser.setUserStatus((Integer) usersTable.getModel().getValueAt(row, 4));
                    newUser.setGender((Integer) usersTable.getModel().getValueAt(row, 5));
                    newUser.setAddress((String) usersTable.getModel().getValueAt(row, 6));
                    newUser.setEmail((String) usersTable.getModel().getValueAt(row, 7));
                    newUser.setPhone((String) usersTable.getModel().getValueAt(row, 8));
                    userUpdated(newUser);
                }
            });*/

            usersTable.getModel().addTableModelListener (new TableModelListener() {
                public void tableChanged(TableModelEvent event) {
                    int row = usersTable.getSelectedRow();
                    System.out.println("LLLLLL " + row + " " + (String) usersTable.getModel().getValueAt(row, 5));
                    User newUser = new User();
                    newUser.setId((String) usersTable.getModel().getValueAt(row, 0));
                    newUser.setUserName((String) usersTable.getModel().getValueAt(row, 1));
                    newUser.setFirstName((String) usersTable.getModel().getValueAt(row, 2));
                    newUser.setLastName((String) usersTable.getModel().getValueAt(row, 3));
                    newUser.setUserStatus(Integer.parseInt((String) usersTable.getModel().getValueAt(row, 4)));
                    SimpleDateFormat formatter=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    try {
                        newUser.setCreated(formatter.parse((String) usersTable.getModel().getValueAt(row, 5)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newUser.setAddress((String) usersTable.getModel().getValueAt(row, 6));
                    newUser.setEmail((String) usersTable.getModel().getValueAt(row, 7));
                    newUser.setPhone((String) usersTable.getModel().getValueAt(row, 8));
                    userUpdated(newUser);
                }
            });
            return usersTable;
        }
        return new JTable();
    }

    public JFrame manageUsersPanel()
    {
        frame = new JFrame();

        final JTable table = manageUsersTable();
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton updateUsers = new JButton("Save");
        updateUsers.setEnabled(true);

        JButton deactivateUser = new JButton("Deactivate");
        deactivateUser.setEnabled(true);

        bottombtnPnl.add(updateUsers);
        bottombtnPnl.add(deactivateUser);

        updateUsers.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {

                UpdateUsersRequest request = new UpdateUsersRequest(user.getId(), usersToUpdate);
                UpdateUsersResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Updated Users Successfully"); //Display Message
                    //refreshTable();
                    finishedCommand();
                }
            }
        });

        deactivateUser.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        // Force the scrollbars to always be displayed
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        frame.add(table.getTableHeader(), BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnPnl, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        return frame;
    }

    private void userUpdated(User userToUpdate)
    {// TODO: can be map?
        if (userToUpdate == null) return;
        boolean isInserted = false;
        for (User usr: usersToUpdate) {
            if (usr.getId().equalsIgnoreCase(userToUpdate.getId()))
            {
                usersToUpdate.remove(usr);
                usersToUpdate.add(userToUpdate);
                isInserted = true;
            }
        }

        if (!isInserted)
            usersToUpdate.add(userToUpdate);
    }

    /*private void userUpdated(User userToUpdate)
    {   // TODO: can be map?
        if (userToUpdate == null) return;
        boolean isInserted = false;
        Iterator<User> iter = usersToUpdate.iterator();
        User usr;
        while (iter.hasNext())
        {
            usr = iter.next();
            if (usr.getId().equalsIgnoreCase(userToUpdate.getId()))
            {
                usersToUpdate.remove(usr);
                usersToUpdate.add(userToUpdate);
                isInserted = true;
            }
            iter.next();
        }
        if (!isInserted)
            usersToUpdate.add(userToUpdate);
    }*/

    private String [] [] convert(Vector<User> users)
    {
        String [][] stringM = new String[users.size()][9];

        for (int i = 0; i < users.size(); i ++){
            User user = users.get(i);
            String [] usersArray = new String[9];
            usersArray[0] = user.getId();
            usersArray[1] = user.getUserName();
            usersArray[2] = user.getFirstName();
            usersArray[3] = user.getLastName();
            usersArray[4] = String.valueOf(user.getUserStatus());
            usersArray[5] = String.valueOf(user.getCreated());
            usersArray[6] = user.getAddress();
            usersArray[7] = user.getEmail();
            usersArray[8] = user.getPhone();

            stringM[i] = usersArray;
        }
        return  stringM;
    }

    @Override
    public void finishedCommand() {
        manageUsersPanel();
        this.menuCommand.updateFrame(frame);
    }
}
