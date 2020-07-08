import entities.User;
import entities.UserLending;
import enums.ResponseStatus;
import jtableModel.ManageBorrowsModel;
import serviceHost.ServiceCommand;
import services.requests.AllUserAwaitingForApprovalBorrowingRequest;
import services.requests.ApproveBookReturnRequest;
import services.responses.AllUserAwaitingForApprovalBorrowingResponse;
import services.responses.ApproveBookReturnResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ManageBorrowsPage {

    private User user;
    private ServiceCommand sc;
    private JButton approveReturn;
    private String borrowId;

    public ManageBorrowsPage(User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
        this.borrowId = "";
    }

    private JTable manageBorrowsTable() {

        AllUserAwaitingForApprovalBorrowingRequest request = new AllUserAwaitingForApprovalBorrowingRequest(user.getId());
        AllUserAwaitingForApprovalBorrowingResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageBorrowsModel lendingsModel = new ManageBorrowsModel(response.getUserLending());
            JTable lendingsTable = new JTable(convert(lendingsModel.getUserLending()), lendingsModel.getColumns().toArray()) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            lendingsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    String borrowId = lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 0).toString();
                    userChoseAvailableBook(borrowId);
                }
            });

            return lendingsTable;
        }
        return new JTable();
    }

    public JFrame manageBorrowsPanel()
    {
        JFrame f = new JFrame();

        final JTable table = manageBorrowsTable();
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        approveReturn = new JButton("Approve Book");
        approveReturn.setEnabled(false);

        bottombtnPnl.add(approveReturn);

        approveReturn.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                ApproveBookReturnRequest request = new ApproveBookReturnRequest(user.getId(), borrowId);
                ApproveBookReturnResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Approve Borrow Successfully"); //Display Message
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

    private void userChoseAvailableBook(String borrowId) {
        if (borrowId.equals("")) return;

        this.borrowId = borrowId;

        if (approveReturn != null)
            this.approveReturn.setEnabled(true);
    }

    private String [] [] convert(Vector<UserLending> lends)
    {
        String [][] stringM = new String[lends.size()][9];

        for (int i = 0; i < lends.size(); i ++){
            UserLending lend = lends.get(i);
            String [] lendsArray = new String[9];
            lendsArray[0] = lend.getBorrowID();
            lendsArray[1] = lend.getUserId();
            lendsArray[2] = lend.getUserName();
            lendsArray[3] = lend.getBookId();
            lendsArray[4] = lend.getBookName();
            lendsArray[5] = lend.isExtended() ? "True" : "False";
            lendsArray[6] = String.valueOf(lend.getStartBorrowRequest());
            lendsArray[7] = String.valueOf(lend.getFinalBorrowDate());
            lendsArray[8] = convertStatus(lend.getStatus());

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
