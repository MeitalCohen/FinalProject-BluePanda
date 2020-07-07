import entities.User;
import entities.UserLending;

import javax.swing.*;
import java.util.Vector;

public class ManageBorrowsPage {

    public static JScrollPane manageBorrows(User user) {

       /* AllBooksLendingsInformationRequest request = new AllBooksLendingsInformationRequest(user.getId());
        ServiceCommand sc = ServiceCommand.getInstance();
        AllBooksLendingsInformationResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            UserLendingsModel lendingsModel = new UserLendingsModel(response.getBorrowedBook());
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
