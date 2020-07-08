import entities.BorrowedBook;
import entities.User;
import entities.UserLending;
import enums.ResponseStatus;
import jtableModel.UserLendingsModel;
import serviceHost.ServiceCommand;
import services.requests.AllBooksLendingsInformationRequest;
import services.requests.ExtendLendingRequest;
import services.requests.ReturnBookRequest;
import services.responses.AllBooksLendingsInformationResponse;
import services.responses.ExtendLendingResponse;
import services.responses.ReturnBookResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MyBooksPage {

    private JButton returnBookButton;
    private JButton extendBookBorrowButton;
    private ServiceCommand sc;
    private String borrowId;
    private User user;
    private String bookName;
    private String authorName;
    private JScrollPane sp;

    public MyBooksPage(User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
        borrowId = "";
        bookName = "";
        authorName = "";
        sp = new JScrollPane();
    }

    private JTable myBooksTable() {

        AllBooksLendingsInformationRequest request = new AllBooksLendingsInformationRequest(user.getId());
        AllBooksLendingsInformationResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            UserLendingsModel landingsModel = new UserLendingsModel(response.getBorrowedBook());
            JTable lendingsTable = new JTable(convert(landingsModel.getUserLending()), landingsModel.getColumns().toArray()) {
               @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            lendingsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    String borrowId = lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 0).toString();
                    String bookName = lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 1).toString();
                    String authorName = lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 2).toString();
                    userChoseAvailableBook(borrowId, bookName, authorName);
                }
            });
            return lendingsTable;
        }
        return new JTable();
    }

    public JFrame myBooksPanel()
    {
        JFrame f = new JFrame();

        final JTable table = myBooksTable();
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        returnBookButton = new JButton("Return Book");
        returnBookButton.setEnabled(false);

        extendBookBorrowButton = new JButton("Extend My Borrow");
        extendBookBorrowButton.setEnabled(false);

        bottombtnPnl.add(returnBookButton);
        bottombtnPnl.add(extendBookBorrowButton);

        returnBookButton.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                ReturnBookRequest request = new ReturnBookRequest(user.getId(), borrowId);
                ReturnBookResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Returned Book Successfully"); //Display Message
                    //recommendation
                    BorrowedBook borrowedBook = response.getBorrowedBook();
                    //AddRecommendationPage.AddRecommendation(user.getId(), bookName, borrowedBook.getBookID(), authorName);
                    //update list
                    refreshTable();
                    //scrollPane
                }
            }
        });

        extendBookBorrowButton.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                ExtendLendingRequest request = new ExtendLendingRequest(user.getId(), borrowId);
                ExtendLendingResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Extended Successfully"); //Display Message
                    //update list
                }
            }
        });

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        table.getTableHeader().setReorderingAllowed(false);

        f.add(table.getTableHeader(), BorderLayout.NORTH);
        f.add(table, BorderLayout.CENTER);
        f.add(btnPnl, BorderLayout.SOUTH);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(false);
        return f;
    }

    private void refreshTable()
    {
        myBooksTable();
    }

    private void userChoseAvailableBook(String borrowId, String bookName, String authorName) {
        if (borrowId.equals("")) return;

        this.borrowId = borrowId;
        this.bookName = bookName;
        this.authorName = authorName;

        if (returnBookButton != null)
            this.returnBookButton.setEnabled(true);

        if(extendBookBorrowButton != null)
            this.extendBookBorrowButton.setEnabled(true);
    }


    private String [] [] convert(Vector<UserLending> lends)
    {
        String [][] stringM = new String[lends.size()][8];

        for (int i = 0; i < lends.size(); i ++){
            UserLending lend = lends.get(i);
            String [] lendsArray = new String[8];
            lendsArray[0] = lend.getBorrowID();
            lendsArray[1] = lend.getBookName();
            lendsArray[2] = lend.getAuthorName();
            lendsArray[3] = lend.getCategory();
            lendsArray[4] = String.valueOf(lend.isExtended());
            lendsArray[5] = String.valueOf(lend.getStartBorrowRequest());
            lendsArray[6] = String.valueOf(lend.getFinalBorrowDate());
            lendsArray[7] = convertStatus(lend.getStatus());

            stringM[i] = lendsArray;
        }
        return  stringM;
    }

    private String convertStatus(int statusValue)
    {
        switch(statusValue)
        {
            case 1: return "Borrowed";
            case 2: return "Waiting for Approval";
            case 3: return "Approved";
            default: return "Unknown";
        }
    }
}