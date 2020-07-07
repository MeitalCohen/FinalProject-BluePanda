import entities.User;
import entities.UserLending;
import enums.ResponseStatus;
import jtableModel.ManageBorrowsModel;
import jtableModel.UserLendingsModel;
import serviceHost.ServiceCommand;
import services.requests.AllBooksLendingsInformationRequest;
import services.requests.AllUserAwaitingForApprovalBorrowingRequest;
import services.responses.AllBooksLendingsInformationResponse;
import services.responses.AllUserAwaitingForApprovalBorrowingResponse;

import javax.swing.*;
import java.util.Vector;

public class BorrowedBooksPage {

    public static JScrollPane borrowedBooks(User user) {

      /*  AllUserAwaitingForApprovalBorrowingRequest request = new AllUserAwaitingForApprovalBorrowingRequest(user.getId());
        ServiceCommand sc = ServiceCommand.getInstance();
        AllUserAwaitingForApprovalBorrowingResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageBorrowsModel manageBooksModel = new ManageBorrowsModel(response.getUserLending());
            JTable manageBooksTable = new JTable(convert(manageBooksModel.getBorrowedBooks()), manageBooksModel.getColumns().toArray());
            JScrollPane sp = new JScrollPane(manageBooksTable);
            return sp;
        }*/
        return new JScrollPane();
    }

    private static String [] [] convert(Vector<UserLending> lends)
    {
        String [][] stringM = new String[lends.size()][8];
        //private final static String[] columnNames = {"User ID", "User Name", "Book ID", "Book Name", "Is Extended", "Start Borrow", "Expiration Date", "Status"};

        for (int i = 0; i < lends.size(); i ++){
            UserLending lend = lends.get(i);
            String [] lendsArray = new String[8];
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
