import entities.UserLending;
import enums.ResponseStatus;
import jtableModel.UserLendingsModel;
import serviceHost.ServiceCommand;
import services.requests.AllBooksLendingsInformationRequest;
import services.responses.AllBooksLendingsInformationResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;

public class MyBooksPage {

    public static JScrollPane myBooks(String userId) {
        //JFrame f = new JFrame("My Books"); //creating instance of JFrame

        AllBooksLendingsInformationRequest request = new AllBooksLendingsInformationRequest(userId);

        ServiceCommand sc = ServiceCommand.getInstance();
        AllBooksLendingsInformationResponse response = sc.execute(request);
        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
/*
            UserLendingsModel lendingsModel = new UserLendingsModel(response.getBorrowedBook());
            JTable lendingsTable = new JTable(lendingsModel);
            lendingsTable.setBounds(30, 15, 800, 300);
            lendingsTable.setModel(lendingsModel);

            JTable jt=new JTable(lendingsModel.getUserLending(),lendingsModel.getColumns());
            //jt.setBounds(30,40,500,500);
            JScrollPane sp=new JScrollPane(jt);

            return  sp;
        }
        */
            UserLendingsModel lendingsModel = new UserLendingsModel(new Vector<>());

            if (response.getBorrowedBook() != null) {
                lendingsModel = new UserLendingsModel(response.getBorrowedBook());
            }
            JTable lendingsTable = new JTable(lendingsModel);
            lendingsTable.setBounds(30, 15, 800, 300);

            UserLendingsModel finalLendingsModel = lendingsModel;
            lendingsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    // do some actions here, for example
                    // print first column value from selected row
                    UserLending userBorrow = finalLendingsModel.getUserLending().elementAt(lendingsTable.getSelectedRow());
                    //System.out.println(lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 0).toString());
                }
            });
            //lendingsTable.setModel(lendingsModel);

            //JTable jt = new JTable(lendingsModel.getUserLending(), lendingsModel.getColumns());
            //jt.setBounds(30,40,500,500);
            JScrollPane sp = new JScrollPane(lendingsTable);

/*
        String data[][]={ {"101","Amit","670000","1","1","1","1","1"},
                {"102","Jai","780000","1","1","1","1","1"},
                {"101","Sachin","700000","1","1","1","1","1"}};
        String column[]={"borrowID","bookID","isExtended","startBorrowRequest","finalBorrowDate","endBorrowRequest","endBorrowOfficial", "status"};

        JTable jt=new JTable(data,column);
        //jt.setBounds(30,40,500,500);
  /*      JScrollPane sp=new JScrollPane(jt);

       /* f.add(sp);
        f.setTitle("My Books");
        f.setSize(1100,600);
        f.setVisible(true);
        f.setLocationRelativeTo(null);*/

            return sp;
        }

        return null;
    }
}
