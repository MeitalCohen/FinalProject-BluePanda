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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ManageUsersPage {
    private User user;
    private ServiceCommand sc;
    private JButton updateUsers;
    private Vector<User> usersToUpdate;

    public ManageUsersPage(User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
        usersToUpdate = new Vector<>();
    }

    private JScrollPane manageUsersTable() {

        GetUsersRequest request = new GetUsersRequest(user.getId());
        GetUsersResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageUsersModel manageUsersModel = new ManageUsersModel(response.getUsers());
            JTable usersTable = new JTable(convert(manageUsersModel.getUsers()), manageUsersModel.getColumns().toArray()) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            usersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    int selectedRow = usersTable.getSelectedRow();
                    User user = manageUsersModel.getUsers().get(selectedRow);
                    userUpdated(user);
                    //String borrowId = usersTable.getValueAt(usersTable.getSelectedRow(), 0).toString();
                    //String bookName = usersTable.getValueAt(usersTable.getSelectedRow(), 1).toString();
                    //String authorName = usersTable.getValueAt(usersTable.getSelectedRow(), 2).toString();
                    //userChoseAvailableBook(borrowId, bookName, authorName);
                }
            });
            JScrollPane sp = new JScrollPane(usersTable);
            return sp;
        }
        return new JScrollPane();
    }

    public JPanel manageUsersPanel()
    {
        JScrollPane scrollPane = manageUsersTable();
        updateUsers = new JButton("Save");//creating instance of JButton for Login Button
        updateUsers.setEnabled(true);
        updateUsers.setBounds(400, 100, 100,30); //x axis, y axis, width, height
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

                    //update list
                    //refreshTable();

                }

            }
        });

        JPanel p = new JPanel();
        p.add(scrollPane);
        p.add(updateUsers);

        return p;
    }

    private void userUpdated(User user)
    {
        if (user == null) return;
        boolean isInserted = false;
        for (User usr: usersToUpdate) {
            if (usr.getId().equalsIgnoreCase(user.getId()))
            {
                usersToUpdate.remove(usr);
                usersToUpdate.add(user);
                isInserted = true;
            }
        }

        if (!isInserted)
            usersToUpdate.add(user);
    }

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

}
