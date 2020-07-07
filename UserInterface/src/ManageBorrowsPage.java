import entities.User;
import entities.UserLending;
import enums.ResponseStatus;
import jtableModel.ManageBorrowsModel;
import jtableModel.UserLendingsModel;
import serviceHost.ServiceCommand;
import services.requests.AllBooksLendingsInformationRequest;
import services.responses.AllBooksLendingsInformationResponse;

import javax.swing.*;
import java.util.Vector;

public class ManageBorrowsPage {

    public static JPanel manageBorrows(User user) {

        AllBooksLendingsInformationRequest request = new AllBooksLendingsInformationRequest(user.getId());
        ServiceCommand sc = ServiceCommand.getInstance();
        AllBooksLendingsInformationResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageBorrowsModel lendingsModel = new ManageBorrowsModel(response.getBorrowedBook());
            JTable lendingsTable = new JTable(convert(lendingsModel.getUserLending()), lendingsModel.getColumns().toArray());
            JScrollPane sp = new JScrollPane(lendingsTable);
            JButton returnBookButton = new JButton("Approve Book");//creating instance of JButton for Login Button
            JPanel p = new JPanel();
            p.add(sp);
            p.add(returnBookButton);
            return p;
        }
        return new JPanel();
    }

    private static String [] [] convert(Vector<UserLending> lends)
    {
        String [][] stringM = new String[lends.size()][8];

        for (int i = 0; i < lends.size(); i ++){
            UserLending lend = lends.get(i);
            String [] lendsArray = new String[8];
            lendsArray[0] = lend.getUserId();
            lendsArray[1] = lend.getUserName();
            lendsArray[2] = lend.getBookId();
            lendsArray[2] = lend.getBookName();
            lendsArray[3] = String.valueOf(lend.isExtended());
            lendsArray[4] = String.valueOf(lend.getStartBorrowRequest());
            lendsArray[5] = String.valueOf(lend.getFinalBorrowDate());
            lendsArray[6] = String.valueOf(lend.getStatus());

            stringM[i] = lendsArray;
        }
        return  stringM;
    }
}
