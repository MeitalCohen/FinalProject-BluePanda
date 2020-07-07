import entities.BookStock;
import entities.User;
import entities.UserLending;
import enums.BooksFilter;
import enums.Category;
import enums.ResponseStatus;
import jtableModel.UserLendingsModel;
import serviceHost.ServiceCommand;
import services.requests.AllBooksLendingsInformationRequest;
import services.requests.GetBooksRequest;
import services.responses.AllBooksLendingsInformationResponse;
import services.responses.GetBooksResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class UserMainPage {

    public static JScrollPane userMain(User user) {
        //JFrame f = new JFrame("My Books"); //creating instance of JFrame

        GetBooksRequest request = new GetBooksRequest(BooksFilter.AvailableOnly);

        ServiceCommand sc = ServiceCommand.getInstance();
        GetBooksResponse response = sc.execute(request);
        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {

            JButton borrowBook_btn = new JButton("Borrow Book");
            borrowBook_btn.setBounds(142, 500, 100, 25);
            borrowBook_btn.setEnabled(false);

            UserLendingsModel lendingsModel = new UserLendingsModel(new Vector<>());

            if (response.getBooks() != null) {
                //lendingsModel = new UserLendingsModel(response.getBorrowedBook());
            }
            //JTable lendingsTable = new JTable(lendingsModel);

            String column[]={"Book Name","Author Name","Category","Quantity"};

            JTable lendingsTable = new JTable(convert(response.getBooks()), column);

            lendingsTable.setBounds(30, 15, 800, 300);

            UserLendingsModel finalLendingsModel = lendingsModel;
            lendingsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    // do some actions here, for example
                    // print first column value from selected row
                    String bookId = lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 0).toString();

                    borrowBook_btn.setEnabled(true);
                }
            });

            borrowBook_btn.addActionListener(new ActionListener() {  //Perform action

                                                 public void actionPerformed(ActionEvent e) {


                                                 }
                                             });

            JScrollPane sp = new JScrollPane(lendingsTable);
            sp.add(borrowBook_btn);
            return sp;
        }

        return new JScrollPane();
    }

    private static String [] [] convert(List<BookStock> books)
    {
        String [][] stringM = new String[books.size()][5];

        for (int i = 0; i < books.size(); i ++){
            BookStock book = books.get(i);
            //Category enumInstance = Category.values()[book.getCategory()];
            String [] bookArray = new String[5];
            bookArray[0] = book.getId();
            bookArray[1] = book.getBookName();
            bookArray[2] = book.getAuthorName();
            //bookArray[2] = enumInstance.name();
            bookArray[3] = String.valueOf(book.getCategory());
            bookArray[4] = String.valueOf(book.getQuantity());

            stringM[i] = bookArray;
        }
        return  stringM;
    }


}
