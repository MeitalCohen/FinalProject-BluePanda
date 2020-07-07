import entities.User;
import entities.UserLending;
import enums.ResponseStatus;
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

      /*  GetUsersRequest request = new GetUsersRequest(user.getId());
        ServiceCommand sc = ServiceCommand.getInstance();
        GetUsersResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            UserLendingsModel lendingsModel = new UserLendingsModel(response.getUsers());
            JTable lendingsTable = new JTable(convert(lendingsModel.getUserLending()), lendingsModel.getColumns().toArray());
            JScrollPane sp = new JScrollPane(lendingsTable);
            return sp;
        }*/
        return new JScrollPane();
    }

    private static String [] [] convert(Vector<UserLending> lends)
    {
        String [][] stringM = new String[lends.size()][7];

        for (int i = 0; i < lends.size(); i ++){
            UserLending lend = lends.get(i);
            String [] lendsArray = new String[7];
            lendsArray[0] = lend.getBookName();
            lendsArray[1] = lend.getAuthorName();
            lendsArray[2] = lend.getCategory();
            lendsArray[3] = String.valueOf(lend.isExtended());
            lendsArray[4] = String.valueOf(lend.getStartBorrowRequest());
            lendsArray[5] = String.valueOf(lend.getFinalBorrowDate());
            lendsArray[6] = String.valueOf(lend.getStatus());

            stringM[i] = lendsArray;
        }
        return  stringM;
    }

}
