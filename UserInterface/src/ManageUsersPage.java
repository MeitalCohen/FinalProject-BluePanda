import entities.User;
import entities.UserLending;
import enums.ResponseStatus;
import jtableModel.ManageUsersModel;
import jtableModel.UserLendingsModel;
import serviceHost.ServiceCommand;
import services.requests.AllBooksLendingsInformationRequest;
import services.requests.GetUsersRequest;
import services.responses.AllBooksLendingsInformationResponse;
import services.responses.GetUsersResponse;

import javax.swing.*;
import java.util.Vector;

public class ManageUsersPage {

    public static JScrollPane manageUsers(User user) {

        GetUsersRequest request = new GetUsersRequest(user.getId());
        ServiceCommand sc = ServiceCommand.getInstance();
        GetUsersResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageUsersModel manageUsersModel = new ManageUsersModel(response.getUsers());
            JTable usersTable = new JTable(convert(manageUsersModel.getUsers()), manageUsersModel.getColumns().toArray());
            JScrollPane sp = new JScrollPane(usersTable);
            return sp;
        }
        return new JScrollPane();
    }

    private static String [] [] convert(Vector<User> users)
    {
        String [][] stringM = new String[users.size()][10];

        for (int i = 0; i < users.size(); i ++){
            User user = users.get(i);
            String [] usersArray = new String[10];
            usersArray[0] = user.getId();
            usersArray[1] = user.getUserName();
            usersArray[2] = user.getFirstName();
            usersArray[3] = user.getLastName();
            usersArray[4] = String.valueOf(user.getUserStatus());
            usersArray[5] = String.valueOf(user.getCreated());
            usersArray[6] = String.valueOf(user.getGender());
            usersArray[7] = user.getAddress();
            usersArray[8] = user.getEmail();
            usersArray[9] = user.getPhone();

            stringM[i] = usersArray;
        }
        return  stringM;
    }

}
